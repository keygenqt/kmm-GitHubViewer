//
//  FollowerService.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 17.10.2021.
//

import Alamofire
import Foundation

class FollowerNetwork: APIHandler {
    @discardableResult
    func getListFollowers() async throws -> [FollowerModel] {
        return try await withCheckedThrowingContinuation { continuation in
            AF.request("\(ConstantsApp.API_URL)user/followers", method: .get, parameters: [
                "page": 1,
                "per_page": 5,
            ], headers: [
                "Authorization": "token \(AppKeyValue.getAuth().accessToken ?? "")",
                "Accept": "application/vnd.github.v3+json",
            ])
            .responseDecodable(queue: DispatchQueue(label: "FollowerNetwork")) { (response: DataResponse<[FollowerModel], AFError>) in
                sleep(3) // slow internet
                switch response.result {
                case let .success(decodedResponse):
                    continuation.resume(returning: decodedResponse)
                case let .failure(error):
                    if error.responseCode == 404 {
                        continuation.resume(throwing: NetworkError.notFound)
                    } else {
                        continuation.resume(throwing: NetworkError.unexpected(code: error.responseCode ?? -1))
                    }
                }
            }
        }
    }
}

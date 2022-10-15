//
//  ReposService.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 17.10.2021.
//

import Alamofire
import Foundation

// @todo
var todo = 0

class ReposNetwork: APIHandler {
    @discardableResult
    func getListRepo(page: Int) async throws -> [RepoModel] {
        return try await withCheckedThrowingContinuation { continuation in

            // @todo
            if page == 4 {
                todo += 1
            }

            // @todo
            AF.request("\(ConstantsApp.API_URL)user/repos\(page == 4 && todo < 3 ? "1" : "")", method: .get, parameters: [
                "page": page,
                "per_page": ConstantsApp.PAGE_LIMIT,
                "type": "owner",
                "sort": "created",
                "direction": "asc",
            ], headers: [
                "Authorization": "token \(AppKeyValue.getAuth().accessToken ?? "")",
                "Accept": "application/vnd.github.v3+json",
            ])
            .responseDecodable(queue: DispatchQueue(label: "ReposNetwork")) { (response: DataResponse<[RepoModel], AFError>) in
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

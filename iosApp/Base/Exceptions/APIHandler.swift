//
//  APIHandler.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 17.10.2021.
//

import Alamofire
import Foundation

class APIHandler {
    var statusCode = Int.zero
    var headers: HTTPHeaders = [
        "Authorization": "token \(AppKeyValue.getAuth().accessToken ?? "")",
        "Accept": "application/vnd.github.v3+json",
    ]

    func getUrl(_ path: String) -> String {
        return "\(ConstantsApp.API_URL)\(path)"
    }
}

extension DataRequest {
    func handleResponse<T: Decodable>(
        label: String,
        continuation: CheckedContinuation<T, Error>
    ) {
        handleResponse(label: label, continuation: continuation) { (_: DataResponse<T, AFError>) in
        }
    }

    func handleResponse<T: Decodable>(
        label: String,
        continuation: CheckedContinuation<T, Error>,
        completionHandler: @escaping (DataResponse<T, AFError>) -> Void
    ) {
        responseDecodable(queue: DispatchQueue(label: label)) { (response: DataResponse<T, AFError>) in
            // @todo emulate slow query
            sleep(2)
            completionHandler(response)
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

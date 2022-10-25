//
//  AuthNetwork.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 13.10.2022.
//

import Alamofire
import Foundation

class AuthNetwork: APIHandler {
    @discardableResult
    func oauthCode(code: String) async throws -> AuthModel {
        return try await withCheckedThrowingContinuation { continuation in
            AF.request(ConstantsKMM.CONST.AUTH_URL, method: .post, parameters: [
                "code": code,
                "client_secret": EnvironmentSecret.clientSecret,
                "client_id": EnvironmentSecret.clientId,
            ])
            .response { response in
                sleep(3) // slow internet

                switch response.result {
                case .success:
                    if let data = response.data, let utf8Text = String(data: data, encoding: .utf8) {
                        continuation.resume(returning: AuthModel(utf8Text))
                    } else {
                        continuation.resume(throwing: NetworkError.unexpected(code: -1))
                    }
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

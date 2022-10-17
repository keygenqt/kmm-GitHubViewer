//
//  UserNetwork.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 17.10.2022.
//

import Alamofire
import Foundation

class UserNetwork: APIHandler {
    @discardableResult
    func getUser() async throws -> UserModel {
        return try await withCheckedThrowingContinuation { continuation in
            AF.request(getUrl("user"), method: .get, headers: headers).handleResponse(
                label: "getUser",
                continuation: continuation
            )
        }
    }
}

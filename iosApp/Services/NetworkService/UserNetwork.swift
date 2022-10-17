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

    @discardableResult
    func updateUser(
        name: String,
        blog: String,
        twitterUsername: String,
        company: String,
        location: String,
        bio: String
    ) async throws -> UserModel {
        return try await withCheckedThrowingContinuation { continuation in
            AF.request(getUrl("user"), method: .patch, parameters: [
                "name": name,
                "blog": blog,
                "twitter_username": twitterUsername,
                "company": company,
                "location": location,
                "bio": bio,
            ], encoding: JSONEncoding.default, headers: headers).handleResponse(
                label: "updateUser",
                continuation: continuation
            )
        }
    }
}

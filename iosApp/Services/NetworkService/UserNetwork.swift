//
//  UserNetwork.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 17.10.2022.
//

import Foundation
import shared

class UserNetwork {
    func getUser() async throws -> UserModel {
        return try await withCheckedThrowingContinuation { continuation in
            ConstantsKMM.CLIENT.get.user { model, error in
                if let model = model {
                    continuation.resume(returning: model)
                } else {
                    continuation.resume(throwing: error ?? NetworkError.notFound)
                }
            }
        }
    }
    
    func updateUser(
        body: UserRequest
    ) async throws -> UserModel {
        return try await withCheckedThrowingContinuation { continuation in
            ConstantsKMM.CLIENT.patch.updateUser(body: body) { model, error in
                if let model = model {
                    continuation.resume(returning: model)
                } else {
                    continuation.resume(throwing: error ?? NetworkError.notFound)
                }
            }
        }
    }
}

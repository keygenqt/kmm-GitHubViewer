//
//  AuthNetwork.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 13.10.2022.
//

import Foundation
import shared

class AuthNetwork {
    func oauth(code: String) async throws -> SecurityModel {
        return try await withCheckedThrowingContinuation { continuation in
            ConstantsKMM.CLIENT.post.oauth(code: code) { model, error in
                if let model = model {
                    continuation.resume(returning: model)
                } else {
                    continuation.resume(throwing: error ?? NetworkError.notFound)
                }
            }
        }
    }
}

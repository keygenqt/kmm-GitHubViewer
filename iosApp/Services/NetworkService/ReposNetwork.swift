//
//  ReposService.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 17.10.2021.
//

import Foundation
import shared

class ReposNetwork {
    func getListRepo(page: Int, orderASC: Bool) async throws -> [RepoModel] {
        return try await withCheckedThrowingContinuation { continuation in
            ConstantsKMM.CLIENT.get.repos(page: Int32(page), isSortASC: orderASC) { models, error in
                if let models = models {
                    continuation.resume(returning: models)
                } else {
                    continuation.resume(throwing: ResponseError.error(error?.localizedDescription))
                }
            }
        }
    }
    
    func getRepo(_ url: String) async throws -> RepoModel {
        return try await withCheckedThrowingContinuation { continuation in
            ConstantsKMM.CLIENT.get.repo(url: url) { model, error in
                if let model = model {
                    continuation.resume(returning: model)
                } else {
                    continuation.resume(throwing: ResponseError.error(error?.localizedDescription))
                }
            }
        }
    }
    
    func updateRepo(
        url: String,
        body: RepoRequest
    ) async throws -> RepoModel {
        return try await withCheckedThrowingContinuation { continuation in
            ConstantsKMM.CLIENT.patch.updateRepo(url: url, body: body) { model, error in
                if let model = model {
                    continuation.resume(returning: model)
                } else {
                    continuation.resume(throwing: ResponseError.error(error?.localizedDescription))
                }
            }
        }
    }
}

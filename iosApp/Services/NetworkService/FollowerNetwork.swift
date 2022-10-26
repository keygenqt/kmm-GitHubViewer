//
//  FollowerService.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 17.10.2021.
//

import Foundation
import shared

class FollowerNetwork {
    func getListFollowers(page: Int) async throws -> [FollowerModel] {
        return try await withCheckedThrowingContinuation { continuation in
            ConstantsKMM.CLIENT.get.followers(page: Int32(page)) { models, error in
                if let models = models {
                    continuation.resume(returning: models)
                } else {
                    continuation.resume(throwing: ResponseError.error(error?.localizedDescription))
                }
            }
        }
    }
}

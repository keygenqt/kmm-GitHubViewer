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
    func getListFollowers(page: Int) async throws -> [FollowerModel] {
        return try await withCheckedThrowingContinuation { continuation in
            AF.request(getUrl("user/followers"), method: .get, parameters: [
                "page": page,
                "per_page": ConstantsApp.PAGE_LIMIT,
            ], headers: headers).handleResponse(
                label: "getListFollowers",
                continuation: continuation
            )
        }
    }
}

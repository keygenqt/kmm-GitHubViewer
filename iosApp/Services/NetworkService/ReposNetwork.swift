//
//  ReposService.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 17.10.2021.
//

import Alamofire
import Foundation

class ReposNetwork: APIHandler {
    @discardableResult
    func getListRepo(page: Int) async throws -> [RepoModel] {
        return try await withCheckedThrowingContinuation { continuation in
            AF.request(getUrl("user/repos"), method: .get, parameters: [
                "page": page,
                "per_page": ConstantsApp.PAGE_LIMIT,
                "type": "owner",
                "sort": "created",
                "direction": "asc",
            ], headers: headers).handleResponse(
                label: "getListRepo",
                continuation: continuation
            )
        }
    }
}

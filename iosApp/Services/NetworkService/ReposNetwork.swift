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
    func getListRepo(page: Int, orderASC: Bool) async throws -> [RepoModel] {
        return try await withCheckedThrowingContinuation { continuation in
            AF.request(getUrl("user/repos"), method: .get, parameters: [
                "page": page,
                "per_page": ConstantsKMM.CONST.PAGE_LIMIT,
                "type": "owner",
                "sort": "created",
                "direction": orderASC ? "asc" : "desc",
            ], headers: headers).handleResponse(
                label: "getListRepo",
                continuation: continuation
            )
        }
    }

    @discardableResult
    func getRepo(_ url: String) async throws -> RepoModel {
        return try await withCheckedThrowingContinuation { continuation in
            AF.request(url, method: .get, headers: headers).handleResponse(
                label: "getRepo",
                continuation: continuation
            )
        }
    }

    @discardableResult
    func updateRepo(
        url: String,
        name: String,
        desc: String,
        isPrivate: Bool
    ) async throws -> RepoModel {
        return try await withCheckedThrowingContinuation { continuation in
            AF.request(url, method: .patch, parameters: [
                "name": name,
                "description": desc,
                "isPrivate": isPrivate,
            ], encoding: JSONEncoding.default, headers: headers).handleResponse(
                label: "updateRepo",
                continuation: continuation
            )
        }
    }
}

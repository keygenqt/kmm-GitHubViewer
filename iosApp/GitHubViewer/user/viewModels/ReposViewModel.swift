//
//  ViewModel.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 17.10.2021.
//

import Combine
import Foundation

class ReposViewModel: ViewModelList<RepoModel> {
    var serviceNetwork = ReposNetwork()
    var serviceData = ReposData()

    override func getPageRealm() -> [RepoModel] {
        return serviceData.getList().toRepoModels()
    }

    override func getPageNetwork(page: Int) async throws -> [RepoModel] {
        return try await serviceNetwork.getListRepo(page: page)
    }

    override func saveList(response: [RepoModel]) {
        serviceData.clear()
        serviceData.saveList(response.toRepoRealms())
    }
}

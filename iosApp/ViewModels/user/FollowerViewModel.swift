//
//  ViewModel.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 17.10.2021.
//

import Combine
import Foundation
import shared

class FollowerViewModel: ViewModelList<FollowerModel> {
    var serviceNetwork = FollowerNetwork()
    var serviceData = FollowerData()

    override func getPageRealm() -> [FollowerModel] {
        return serviceData.getList().toModels()
    }

    override func getPageNetwork(page: Int) async throws -> [FollowerModel] {
        return try await serviceNetwork.getListFollowers(page: page)
    }

    override func saveList(response: [FollowerModel]) {
        serviceData.clear()
        serviceData.saveList(response.toRealms())
    }
}

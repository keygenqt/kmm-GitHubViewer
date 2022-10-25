//
//  ViewModel.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 17.10.2021.
//

import Combine
import Foundation
import shared

class ReposViewModel: ViewModelList<RepoModel> {
    var serviceNetwork = ReposNetwork()
//    var serviceData = ReposData()

    @Published var orderASC: Bool = ConstantsKMM.STORAGE.isRepoOrder

    func toggleOrder() {
        orderASC = !orderASC
        ConstantsKMM.STORAGE.isRepoOrder = orderASC
        Task { await super.reload() }
    }

    override func getPageRealm() -> [RepoModel] {
        return []
//        return serviceData.getList().toRepoModels()
    }

    override func getPageNetwork(page: Int) async throws -> [RepoModel] {
        return try await serviceNetwork.getListRepo(page: page, orderASC: orderASC)
    }

    override func saveList(response: [RepoModel]) {
//        serviceData.clear()
//        serviceData.save(response.toRealms())
    }
    

}

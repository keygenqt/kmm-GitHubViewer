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
    var serviceData = ReposData()

    @Published var orderASC: Bool = ConstantsKMM.STORAGE.isRepoOrder

    func toggleOrder() {
        orderASC = !orderASC
        ConstantsKMM.STORAGE.isRepoOrder = orderASC
        Task { await super.reload() }
    }

    override func getPageRealm() -> [RepoModel] {
        return serviceData.getList().toRepoModels()
    }

    override func getPageNetwork(page: Int) async throws -> [RepoModel] {
        let response = try await reposAsync(page: page, orderASC: orderASC)
        print(response)
        return try await serviceNetwork.getListRepo(page: page, orderASC: orderASC)
    }

    override func saveList(response: [RepoModel]) {
        serviceData.clear()
        serviceData.save(response.toRealms())
    }
    
    func reposAsync(page: Int, orderASC: Bool) async throws -> [shared.RepoModel] {
        return try await withCheckedThrowingContinuation { continuation in
            ConstantsKMM.CLIENT.get.repos(page: Int32(page), isSortASC: orderASC) { models, error in
                if let models = models {
                    continuation.resume(returning: models)
                } else {
                    continuation.resume(throwing: error ?? NetworkError.notFound)
                }
            }
        }
    }
}

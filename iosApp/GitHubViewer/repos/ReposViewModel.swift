//
//  ViewModel.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 17.10.2021.
//

import Combine
import Foundation

class ReposViewModel: ObservableObject, Identifiable {
    @Published var isShowProgressView = true
    @Published var error: NetworkError?
    @Published var models: [RepoModel] = []

    var serviceNetwork = ReposNetwork()
    var serviceData = ReposData()

    init() {
        let list = serviceData.getList()
        if list.isEmpty {
            Task {
                await self.refresh()
            }
        } else {
            models = list.toRepoModels()
            isShowProgressView = false
        }
    }

    func refresh() async {
        error = nil
        do {
            let response = try await serviceNetwork.getListRepo()
            DispatchQueue.global(qos: .background).async {
                Timer.scheduledTimer(withTimeInterval: 0.2, repeats: false) { _ in
                    self.serviceData.clear()
                    self.serviceData.saveList(response.toRepoRealms())
                    DispatchQueue.main.async {
                        self.models = response
                        self.isShowProgressView = false
                    }
                }
                RunLoop.current.run()
            }
        } catch let networkError as NetworkError {
            self.error = networkError
        } catch {
            print("Unexpected error: \(error).")
        }
    }
}

//
//  ViewModel.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 17.10.2021.
//

import Combine
import Foundation

class FollowerViewModel: ObservableObject, Identifiable {
    @Published var isShowProgressView = true
    @Published var error: NetworkError?
    @Published var models: [FollowerModel] = []

    var serviceNetwork = FollowerNetwork()
    var serviceData = FollowerData()

    init() {
        let list = serviceData.getList()
        if list.isEmpty {
            Task {
                await self.refresh()
            }
        } else {
            models = list.toFollowerModels()
            isShowProgressView = false
        }
    }

    func refresh() async {
        error = nil
        do {
            let response = try await serviceNetwork.getListFollowers()
            DispatchQueue.global(qos: .background).async {
                Timer.scheduledTimer(withTimeInterval: 0.2, repeats: false) { _ in
                    self.serviceData.clear()
                    self.serviceData.saveList(response.toFollowerRealms())
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

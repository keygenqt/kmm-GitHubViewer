//
//  ViewModel.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 17.10.2021.
//

import Combine
import Foundation

class ReposViewModel: ObservableObject, Identifiable {
    @Published var isLoading = true
    @Published var isEnd = false
    @Published var error: NetworkError?
    @Published var models: [RepoModel] = []

    var page = 1

    var serviceNetwork = ReposNetwork()
    var serviceData = ReposData()

    init() {
        Task {
            await self.load()
        }

//        print(AppKeyValue.getAuth().toString())
//        preferences.clearCache()
//
//        let list = serviceData.getList()
//        if list.isEmpty {
//            Task {
//                await self.refresh()
//            }
//        } else {
//            models = list.toRepoModels()
//            isShowProgressView = false
//        }
    }

    func startLoad() {
        DispatchQueue.main.async {
            self.error = nil
            self.isLoading = true
        }
    }

    func endLoad(response: [RepoModel] = [], error: NetworkError? = nil) {
        DispatchQueue.main.async {
            self.error = nil
            self.isLoading = false
            if error == nil {
                if self.page == 1 {
                    self.models.removeAll()
                }
                self.models.append(contentsOf: response)
                self.page = (self.models.count + ConstantsApp.PAGE_LIMIT) / ConstantsApp.PAGE_LIMIT
                self.isEnd = response.count < ConstantsApp.PAGE_LIMIT
            } else {
                self.error = error
            }
        }
    }

    func clearError() {
        error = nil
    }

    func reload() async {
        page = 1
        await load()
    }

    func load() async {
        if page != 1 {
            startLoad()
        }
        do {
            let response = try await serviceNetwork.getListRepo(page: page)
            endLoad(response: response)
        } catch let networkError as NetworkError {
            endLoad(error: networkError)
        } catch {
            print("Unexpected error: \(error).")
        }
    }

//    func refresh() async {
//        do {
//            error = nil
//            let response = try await serviceNetwork.getListRepo(page: page)
//
//            DispatchQueue.global(qos: .background).async {
//                Timer.scheduledTimer(withTimeInterval: 0.2, repeats: false) { _ in
//                    self.serviceData.clear()
//                    self.serviceData.saveList(response.toRepoRealms())
//                    DispatchQueue.main.async {
//                        self.models = response
//                        self.isShowProgressView = false
//                    }
//                }
//                RunLoop.current.run()
//            }
//        } catch let networkError as NetworkError {
//            self.error = networkError
//        } catch {
//            print("Unexpected error: \(error).")
//        }
//    }
}

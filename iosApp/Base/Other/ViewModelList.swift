//
//  ViewModelList.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 16.10.2022.
//

import Combine
import Foundation

class ViewModelList<T>: ObservableObject, Identifiable {
    @Published var isLoadingPage = true
    @Published var loading = true
    @Published var isEnd = false
    @Published var error: NetworkError?
    @Published var models: [T] = []

    var page = 1

    func getPageRealm() -> [T] {
        return []
    }

    func getPageNetwork(page _: Int) async throws -> [T] {
        return []
    }

    func saveList(response _: [T]) {}

    init() {
        models = getPageRealm()
        Task { await self.load() }
    }

    func endLoad(
        response: [T] = [],
        error: NetworkError? = nil
    ) {
        DispatchQueue.main.async {
            self.error = nil
            self.loading = false
            self.isLoadingPage = false

            if error == nil {
                if self.page == 1 {
                    self.models.removeAll()
                    self.saveList(response: response)
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
            DispatchQueue.main.async {
                self.error = nil
                self.isLoadingPage = true
                self.loading = true
            }
        }
        DispatchQueue.main.async {
            self.loading = true
        }
        do {
            let response = try await getPageNetwork(page: page)
            endLoad(response: response)
        } catch let networkError as NetworkError {
            endLoad(error: networkError)
        } catch {
            print("Unexpected error: \(error).")
        }
    }
}

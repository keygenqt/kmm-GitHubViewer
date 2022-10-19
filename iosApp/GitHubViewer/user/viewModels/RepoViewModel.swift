//
//  RepoViewModel.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 22.01.2022.
//

import Foundation

class RepoViewModel: ObservableObject, Identifiable {
    var serviceNetwork = ReposNetwork()
    var serviceData = ReposData()

    @Published var loading: Bool = true
    @Published var error: NetworkError?
    @Published var model: RepoModel?

    func updateUI(
        response: RepoModel? = nil,
        error: NetworkError? = nil
    ) {
        DispatchQueue.main.async {
            if response == nil {
                self.error = error
            } else {
                self.model = response
                self.serviceData.save(response!.toRealm())
            }
            self.loading = response == nil && error == nil
        }
    }

    func readDb(_ url: String) {
        DispatchQueue.main.async {
            self.model = self.serviceData.getModel(url)?.toModel()
        }
    }

    func retry(_ url: String) {
        DispatchQueue.main.async {
            self.error = nil
            self.model = nil
        }
        Task { await load(url) }
    }

    func load(_ url: String) async {
        updateUI()
        do {
            let response = try await serviceNetwork.getRepo(url)
            updateUI(response: response)
        } catch let networkError as NetworkError {
            updateUI(error: networkError)
        } catch {
            print("Unexpected error: \(error).")
        }
    }
}

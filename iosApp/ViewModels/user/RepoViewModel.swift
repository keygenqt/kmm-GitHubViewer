//
//  RepoViewModel.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 22.01.2022.
//

import Foundation
import shared

class RepoViewModel: ObservableObject, Identifiable {
    var serviceNetwork = ReposNetwork()
    var serviceData = ReposData()

    @Published var loading: Bool = true
    @Published var error: ResponseError?
    @Published var model: RepoModel?

    func updateUI(
        response: RepoModel? = nil,
        error: ResponseError? = nil
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
        load(url)
    }

    func load(_ url: String) {
        Task { await loadAsync(url) }
    }

    func loadAsync(_ url: String) async {
        updateUI()
        do {
            let response = try await serviceNetwork.getRepo(url)
            updateUI(response: response)
        } catch let error as ResponseError {
            updateUI(error: error)
        } catch {
            print("Unexpected error: \(error).")
        }
    }
}

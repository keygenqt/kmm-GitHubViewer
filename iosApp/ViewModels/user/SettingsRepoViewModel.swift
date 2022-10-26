//
//  SettingsRepoViewModel.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 18.10.2022.
//

import Foundation
import shared

class SettingsRepoViewModel: ObservableObject, Identifiable {
    var serviceNetwork = ReposNetwork()
//    var serviceData = ReposData()

    @Published var loading: Bool = false
    @Published var error: ResponseError?

    func updateUI(
        response: RepoModel? = nil,
        error: ResponseError? = nil
    ) {
        DispatchQueue.main.async {
            if response == nil {
                self.error = error
            } else {
//                self.serviceData.save(response!.toRealm())
            }
            self.loading = false
        }
    }

    func retry() {
        DispatchQueue.main.async {
            self.error = nil
        }
    }

    func update(
        url: String,
        name: String,
        desc: String,
        isPrivate: Bool
    ) {
        DispatchQueue.main.async {
            self.loading = true
        }
        Task {
            do {
                let response = try await serviceNetwork.updateRepo(url: url, body: RepoRequest(
                    name: name,
                    description: desc,
                    isPrivate: isPrivate
                ))
                updateUI(response: response)
            } catch let error as ResponseError {
                updateUI(error: error)
            } catch {
                print("Unexpected error: \(error).")
            }
        }
    }
}

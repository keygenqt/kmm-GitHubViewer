//
//  SettingsViewModel.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 16.10.2022.
//

import Foundation

class SettingsViewModel: ObservableObject, Identifiable {
    var serviceNetwork = UserNetwork()
    var serviceData = UserData()

    @Published var loading: Bool = false
    @Published var error: NetworkError?

    func updateUI(
        response: UserModel? = nil,
        error: NetworkError? = nil
    ) {
        DispatchQueue.main.async {
            if response == nil {
                self.error = error
            } else {
                self.serviceData.clear()
                self.serviceData.saveUser(response!.toRealm())
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
        name: String,
        blog: String,
        twitterUsername: String,
        company: String,
        location: String,
        bio: String
    ) {
        DispatchQueue.main.async {
            self.loading = true
        }
        Task {
            do {
                let response = try await serviceNetwork.updateUser(
                    name: name,
                    blog: blog,
                    twitterUsername: twitterUsername,
                    company: company,
                    location: location,
                    bio: bio
                )
                updateUI(response: response)
            } catch let networkError as NetworkError {
                updateUI(error: networkError)
            } catch {
                print("Unexpected error: \(error).")
            }
        }
    }
}

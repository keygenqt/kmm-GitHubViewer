//
//  ProfileViewModel.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 16.10.2022.
//

import Foundation

class ProfileViewModel: ObservableObject, Identifiable {
    var serviceNetwork = UserNetwork()
    var serviceData = UserData()

    @Published var error: NetworkError?
    @Published var model: UserModel?

    init() {
        model = serviceData.getUser()?.toModel()
        Task { await load() }
    }

    func update(
        response: UserModel? = nil,
        error: NetworkError? = nil
    ) {
        DispatchQueue.main.async {
            if response == nil {
                self.error = error
                self.model = nil
            } else {
                self.model = response
                self.serviceData.clear()
                self.serviceData.saveUser(response!.toRealm())
            }
        }
    }

    func retry() {
        update()
        Task { await load() }
    }

    func load() async {
        update()
        do {
            let response = try await serviceNetwork.getUser()
            update(response: response)
        } catch let networkError as NetworkError {
            update(error: networkError)
        } catch {
            print("Unexpected error: \(error).")
        }
    }
}

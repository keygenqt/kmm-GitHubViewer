//
//  ProfileViewModel.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 16.10.2022.
//

import Foundation
import shared

class ProfileViewModel: ObservableObject, Identifiable {
    var serviceNetwork = UserNetwork()
//    var serviceData = UserData()

    @Published var error: ResponseError?
    @Published var model: UserModel?

    init() {
//        model = serviceData.getUser()?.toModel()
    }

    func updateUI(
        response: UserModel? = nil,
        error: ResponseError? = nil
    ) {
        DispatchQueue.main.async {
            if response == nil {
                self.error = error
            } else {
                self.model = response
//                self.serviceData.clear()
//                self.serviceData.saveUser(response!.toRealm())
            }
        }
    }

    func readDb() {
//        DispatchQueue.main.async {
//            self.model = self.serviceData.getUser()?.toModel()
//        }
    }

    func retry() {
        DispatchQueue.main.async {
            self.error = nil
            self.model = nil
        }
        Task { await load() }
    }

    func load() async {
        updateUI()
        do {
            let response = try await serviceNetwork.getUser()
            updateUI(response: response)
        } catch let error as ResponseError {
            updateUI(error: error)
        } catch {
            print("Unexpected error: \(error).")
        }
    }
}

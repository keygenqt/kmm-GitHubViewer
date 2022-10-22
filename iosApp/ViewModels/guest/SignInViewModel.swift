//
//  SignInViewModel.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 13.01.2022.
//

import Combine
import Foundation

class SignInViewModel: ObservableObject, Identifiable {
    @Published var isShowProgressView = false
    @Published var error: NetworkError?

    var serviceNetwork = AuthNetwork()

    func authUser(url: URL, action: (() -> Void)?) {
        if url.path == "/oauth" {
            let code = url.valueOf("code")
            if code != nil {
                isShowProgressView = true
                Task {
                    await authUser(code: code!, action: action)
                }
            }
        } else {
            // @todo error
        }
    }

    func authUser(code: String, action: (() -> Void)?) async {
        DispatchQueue.main.async {
            self.error = nil
        }
        do {
            let response = try await serviceNetwork.oauthCode(code: code)
            AppKeyValue.setAuth(response)
            DispatchQueue.main.async {
                action?()
            }
        } catch let networkError as NetworkError {
            self.error = networkError
        } catch {
            print("Unexpected error: \(error).")
        }
    }

    func clear() {
        isShowProgressView = false
        error = nil
    }
}

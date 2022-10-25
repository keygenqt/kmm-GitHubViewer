//
//  SignInViewModel.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 13.01.2022.
//

import Combine
import Foundation
import shared

class SignInViewModel: ObservableObject, Identifiable {
    @Published var isShowProgressView = false
    @Published var error: NetworkError?

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
    
    func oauthAsync(code: String) async throws -> SecurityModel {
        return try await withCheckedThrowingContinuation { continuation in
            ConstantsKMM.CLIENT.post.oauth(code: code) { model, error in
                if let model = model {
                    continuation.resume(returning: model)
                } else {
                    continuation.resume(throwing: error ?? NetworkError.notFound)
                }
            }
        }
    }

    func authUser(code: String, action: (() -> Void)?) async {
        DispatchQueue.main.async {
            self.error = nil
        }
        do {
            let response = try await oauthAsync(code: code)
            ConstantsKMM.STORAGE.authToken = response.accessToken
            ConstantsKMM.CLIENT.setToken(token: ConstantsKMM.STORAGE.authToken)
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

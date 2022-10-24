//
//  WelcomeViewModel.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 13.01.2022.
//

import Combine
import Foundation
import shared

class WelcomeViewModel: ObservableObject, Identifiable {
    
    @Published var list: [RocketModel] = []
    @Published var error: NetworkError?
    
    var serviceNetwork = RocketNetwork()
    
    func getRocketsAsync()  async throws -> [RocketModel] {
        return try await withCheckedThrowingContinuation { continuation in
            serviceNetwork.getRockets { models, error in
                if let models = models {
                    continuation.resume(returning: models)
                } else {
                    continuation.resume(throwing: error ?? NetworkError.notFound)
                }
            }
        }
    }
    
    func getRockets() async {
        do {
            let response = try await getRocketsAsync()
            updateUI(response: response)
        } catch let networkError as NetworkError {
            let error = networkError
            updateUI(error: error)
        } catch {
            print("Unexpected error: \(error).")
        }
    }
    
    func updateUI(
        response: [RocketModel]? = nil,
        error: NetworkError? = nil
    ) {
        DispatchQueue.main.async {
            if let response = response {
                self.list = response
            } else {
                self.error = error
            }
        }
    }
}

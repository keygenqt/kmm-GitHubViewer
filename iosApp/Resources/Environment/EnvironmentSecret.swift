//
//  Environment.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 06.02.2022.
//

import Foundation

public enum EnvironmentSecret {
    enum Keys {
        static let clientId = "github_client_id"
        static let clientSecret = "github_client_secret"
    }

    /// Get plist
    private static let infoDictionary: [String: Any] = {
        guard let dict = Bundle.main.infoDictionary else {
            fatalError("plist file not found")
        }
        return dict
    }()

    /// Get clientId
    static let clientId: String = {
        guard let value = EnvironmentSecret.infoDictionary[Keys.clientId] as? String else {
            fatalError("Client Id not set in  plist")
        }
        return value
    }()

    /// Get clientSecret
    static let clientSecret: String = {
        guard let value = EnvironmentSecret.infoDictionary[Keys.clientSecret] as? String else {
            fatalError("Client Secret not set in  plist")
        }
        return value
    }()
}

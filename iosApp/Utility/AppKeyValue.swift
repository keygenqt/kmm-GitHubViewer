//
//  AppKeyValue.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 12.10.2022.
//

import Foundation

struct AppKeyValue {
    static let preferences = UserDefaults.standard

    static let keyOnboarding = "keyOnboarding"
    static let keyUserAuth = "keyUserAuth"

    static func isPassOnboarding() -> Bool {
        return preferences.bool(forKey: keyOnboarding)
    }

    static func setPassOnboarding(_ state: Bool) {
        preferences.set(state, forKey: keyOnboarding)
    }

    static func isAuth() -> Bool {
        return preferences.string(forKey: keyUserAuth) != nil
    }

    static func setAuth(_ model: AuthModel) {
        preferences.set(model.toString(), forKey: keyUserAuth)
    }

    static func getAuth() -> AuthModel {
        return AuthModel(preferences.string(forKey: keyUserAuth) ?? "")
    }
}

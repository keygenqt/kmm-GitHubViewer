//
//  AppKeyValue.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 12.10.2022.
//

import Foundation

struct AppKeyValue {
    static let preferences = UserDefaults.standard

    static let keyAuth = "keyAuth"
    static let keyOnboarding = "keyOnboarding"

    static func isAuth() -> Bool {
        return UserDefaults.standard.bool(forKey: keyAuth)
    }

    static func setAuth(_ state: Bool) {
        preferences.set(state, forKey: keyAuth)
    }

    static func isOnboarding() -> Bool {
        return UserDefaults.standard.bool(forKey: keyOnboarding)
    }

    static func setOnboarding(_ state: Bool) {
        preferences.set(state, forKey: keyOnboarding)
    }
}

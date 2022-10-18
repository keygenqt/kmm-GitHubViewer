//
//  AppKeyValue.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 12.10.2022.
//

import Foundation

struct AppKeyValue {
    static let storage = UserDefaults.standard

    static let keyRepoOrder = "keyRepoOrder"
    static let keyOnboarding = "keyOnboarding"
    static let keyUserAuth = "keyUserAuth"

    static func isPassOnboarding() -> Bool {
        return storage.bool(forKey: keyOnboarding)
    }

    static func setPassOnboarding(_ state: Bool) {
        storage.set(state, forKey: keyOnboarding)
    }

    static func isRepoOrderASC() -> Bool {
        return storage.bool(forKey: keyRepoOrder)
    }

    static func setRepoOrderASC(_ state: Bool) {
        storage.set(state, forKey: keyRepoOrder)
    }

    static func isAuth() -> Bool {
        return storage.string(forKey: keyUserAuth) != nil
    }

    static func setAuth(_ model: AuthModel) {
        storage.set(model.toString(), forKey: keyUserAuth)
    }

    static func getAuth() -> AuthModel {
        return AuthModel(storage.string(forKey: keyUserAuth) ?? "")
    }

    static func clear() {
        let dictionary = storage.dictionaryRepresentation()
        dictionary.keys.forEach { key in
            if key != keyOnboarding {
                storage.removeObject(forKey: key)
            }
        }
    }
}

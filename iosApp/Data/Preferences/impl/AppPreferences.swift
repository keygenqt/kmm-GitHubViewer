//
//  AppPreferences.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 19.01.2022.
//

import Foundation

protocol IAppPreferences {
    var test: Int { get }
    func clearCache()
}

protocol AppPreferences: IAppPreferences {
    func enableOnboarding()
    func disableOnboarding()
}

extension AppPreferences {
//    enum Kind {
//        case negative, zero, positive
//    }

    func clearCache() {
        print("clearCache AppPreferences")
    }

    func enableOnboarding() {
        print(test)
    }

    func disableOnboarding() {
        print(test)
    }
}

protocol AppPreferences2: IAppPreferences {
    func enableOnboarding()
    func disableOnboarding()
}

extension AppPreferences2 {
    func clearCache() {
        print("clearCache AppPreferences2")
    }
}

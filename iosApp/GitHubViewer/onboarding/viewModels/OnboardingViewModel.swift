//
//  OnboardingViewModel.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 13.01.2022.
//

import Combine
import Foundation

class OnboardingViewModel: ObservableObject, Identifiable {
    let currentLevelKey = "currentLevel"
    let preferences = UserDefaults.standard

    func disableOnboarding() {
        preferences.set(true, forKey: currentLevelKey)
    }
}

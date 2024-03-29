//
//  OnboardingViewModel.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 13.01.2022.
//

import Combine
import Foundation

class OnboardingViewModel: ObservableObject, Identifiable {
    func disableOnboarding() {
        ConstantsKMM.STORAGE.isOnboardingDone = true
    }
}

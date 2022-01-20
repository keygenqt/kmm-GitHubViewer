//
//  AppRouter.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 20.01.2022.
//

import Foundation
import SwiftUI

enum OnboardingRoute {
    case onboarding
    case welcome
}

struct OnboardingRouter: Routing {
    func view(for route: OnboardingRoute) -> some View {
        switch route {
        case .onboarding:
            OnboardingScreen(router: self)
        case .welcome:
            WelcomeScreen(router: self)
        }
    }
}

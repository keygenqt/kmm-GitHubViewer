//
//  RouteOnboarding.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 22.01.2022.
//

import SwiftUI

enum RouteOnboarding {
    case onboarding
}

class RouterOnboarding: ObservableObject {
    @Published var route: RouteOnboarding? = .onboarding

    func isActive(for route: RouteOnboarding) -> Binding<Bool> {
        return Binding(get: { self.route != route }, set: { self.route = $0 ? nil : route })
    }
}

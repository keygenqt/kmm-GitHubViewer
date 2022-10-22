//
//  NavGraphGuest.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 13.01.2022.
//

import SwiftUI

struct NavGraph: View {
    // graph
    @StateObject var graph = GraphObservable()
    // routers
    @StateObject var routerOnboarding = RouterOnboarding()
    @StateObject var routerGuest = RouterGuest()
    @StateObject var routerUser = RouterUser()

    var body: some View {
        switch graph.route {
        case .onboarding:
            NavigationView {
                OnboardingScreen()
            }
            .environmentObject(graph)
            .environmentObject(routerOnboarding)
            .accentColor(.orange)
        case .guest:
            NavigationView {
                WelcomeScreen()
            }
            .environmentObject(graph)
            .environmentObject(routerGuest)
            .accentColor(.orange)
        case .user:
            UserTabsScreen()
                .environmentObject(graph)
                .environmentObject(routerUser)
                .accentColor(.orange)
        }
    }
}

//
//  WelcomeScreen.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 13.01.2022.
//

import SwiftUI

struct WelcomeScreen<Router: Routing>: View where Router.Route == OnboardingRoute {
    let router: Router
    @State var activeNavigation: OnboardingRoute?
    ///
    @ObservedObject var viewModel = WelcomeViewModel()

    var body: some View {
        VStack {
            Button("Go to onboarding") {
                self.activeNavigation = .onboarding
            }

            NavigationLink(
                destination: router.view(for: .onboarding),
                tag: .onboarding,
                selection: $activeNavigation,
                label: {
                    EmptyView()
                }
            )
        }
    }
}

// struct WelcomeScreen_Previews: PreviewProvider {
//    static var previews: some View {
//       // WelcomeScreen()
//    }
// }

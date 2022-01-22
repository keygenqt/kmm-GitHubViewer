//
//  WelcomeScreen.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 13.01.2022.
//

import SwiftUI

struct WelcomeScreen: View {
    // model
    @ObservedObject var viewModel = WelcomeViewModel()
    // graph
    @EnvironmentObject var router: RouterGuest

    var body: some View {
        VStack {
            NavigationLink(
                destination: SignInScreen(),
                isActive: router.isActive(for: .welcome)
            ) {
                Text("To signIn")
            }
            .isDetailLink(false)
            .navigationBarTitle("Welcome")
        }
    }
}

struct WelcomeScreen_Previews: PreviewProvider {
    static var previews: some View {
        WelcomeScreen()
    }
}

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
    @EnvironmentObject var graph: GraphObservable
    // router
    @EnvironmentObject var router: RouterGuest

    var body: some View {
        ZStack {
            VStack {
                Spacer()
            }
            .frame(
                minWidth: 0,
                maxWidth: .infinity,
                minHeight: 0,
                maxHeight: .infinity,
                alignment: .topLeading
            )
            .background(Image("bg_welcome").resizable().aspectRatio(contentMode: .fill))
            .edgesIgnoringSafeArea(.top)
            .edgesIgnoringSafeArea(.bottom)

            VStack {
                TextTitleMedium(text: L10nWelcome.subtitle)

                Spacer()

                VStack {
                    LottieView(
                        name: "welcome"
                    ).frame(width: 320, height: 320)
                }.padding(.bottom, 70)

                Spacer()

                NavigationLink(
                    destination: SignInScreen(),
                    isActive: router.isActive(for: .welcome)
                ) {
                    HStack {
                        Spacer()
                        Text(L10nWelcome.btnSignIn)
                            .font(.headline)
                            .foregroundColor(.white)
                        Spacer()
                    }
                }
                .isDetailLink(false)
                .buttonStyle(BottomPrimaryStyle())

                HStack {
                    Spacer()
                    TextBodySmall(text: L10nWelcome.version("0.0.1"))
                }
            }
            .navigationBarTitle(L10nWelcome.title)
            .navigationBarTitleDisplayMode(.large)
            .padding(16)
        }
    }
}

struct WelcomeScreen_Previews: PreviewProvider {
    static var previews: some View {
        WelcomeScreen()
    }
}

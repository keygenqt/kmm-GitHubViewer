//
//  WelcomeScreen.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 13.01.2022.
//

import SwiftUI

struct FormButton2: ButtonStyle {
    func makeBody(configuration: Configuration) -> some View {
        configuration.label
            .padding()
            .foregroundColor(Color.onPrimary)
            .background(configuration.isPressed ? Color.outline : Color.primary)
            .clipShape(Capsule())
            .scaleEffect(configuration.isPressed ? 0.99 : 1)
            .animation(.easeOut(duration: 0.2), value: configuration.isPressed)
    }
}

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

                Button(action: {
                    router.route = .signIn
                }, label: {
                    HStack {
                        Spacer()
                        Text(L10nWelcome.btnSignIn)
                            .font(.headline)
                            .foregroundColor(.white)
                        Spacer()
                    }
                })
                .buttonStyle(FormButton2())
                .listRowInsets(.init())
                .listRowBackground(Color.clear)

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

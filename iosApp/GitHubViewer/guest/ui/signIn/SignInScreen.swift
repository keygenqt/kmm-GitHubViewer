//
//  SignInScreen.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 13.01.2022.
//

import SwiftUI

struct SignInScreen: View {
    // model
    @ObservedObject var viewModel = SignInViewModel()
    // graph
    @EnvironmentObject var graph: GraphObservable
    // router
    @EnvironmentObject var router: RouterGuest
    // form states
    @State private var error: String?
    // form value
    @State private var nicknameValue: String = "12"

    // page values
    @Environment(\.openURL) var openURL

    var body: some View {
        AppForm(error: $error) {
            Section {
                AppTextField(
                    label: L10nSignIn.formNickname,
                    value: $nicknameValue,
                    validates: [validateIsBlank, validateIsLong]
                ) { error in
                    self.error = error
                }
            }
            Section {
                Button(L10nSignIn.formButtonSubmit) {
                    openURL(URL(string: "https://www.apple.com")!)
                }
                .buttonStyle(BottomPrimaryStyle())
                .listRowInsets(.init())
                .listRowBackground(Color.clear)
            }
            .listRowBackground(Color.clear)
        }
    }
}

struct SignInScreen_Previews: PreviewProvider {
    static var previews: some View {
        SignInScreen()
    }
}

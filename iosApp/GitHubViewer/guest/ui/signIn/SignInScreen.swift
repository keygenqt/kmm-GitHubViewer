//
//  SignInScreen.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 13.01.2022.
//

import AlertToast
import SwiftUI

struct SignInScreen: View {
    // model
    @ObservedObject var viewModel = SignInViewModel()
    // graph
    @EnvironmentObject var graph: GraphObservable
    // router
    @EnvironmentObject var router: RouterGuest
    // form states
    @State private var username: String = ""
    @State private var isError = false
    @State private var usernameError: String?
    // page values
    @Environment(\.openURL) var openURL
    @State private var showToast = false

    var body: some View {
        Form {
            Section {
                ZStack {
                    TextField(text: $username, prompt: Text(L10nSignIn.formNickname)) {
                        Text(L10nSignIn.formNickname)
                    }
                    .keyboardType(.asciiCapable)
                    .onChange(of: username, perform: { value in
                        usernameError = nil
                        if value.matches("[0-9]") {
                            usernameError = "Nickname contains numbers"
                        } else if value.count > 10 {
                            usernameError = "Nickname too long"
                        }
                    })
                    .padding(.trailing, usernameError != nil ? 30 : 0)

                    HStack {
                        Spacer()
                        if usernameError != nil {
                            Image(systemName: "exclamationmark.circle.fill")
                                .foregroundColor(.error)
                                .onTapGesture(count: 1) {
                                    showToast = true
                                }
                        }
                    }
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
        .navigationBarTitle(L10nSignIn.title)
        .navigationBarTitleDisplayMode(.inline)
        .toast(isPresenting: $showToast) {
            AlertToast(
                displayMode: .banner(.pop),
                type: .systemImage("exclamationmark.circle.fill", .error),
                title: usernameError ?? "Error validation",
                style: .style(
                    backgroundColor: .errorContainer,
                    titleColor: .onErrorContainer,
                    subTitleColor: .onErrorContainer,
                    titleFont: Font.custom(PoppinsName(.Medium), size: 16),
                    subTitleFont: Font.custom(PoppinsName(.Medium), size: 12)
                )
            )
        }
    }
}

struct SignInScreen_Previews: PreviewProvider {
    static var previews: some View {
        SignInScreen()
    }
}

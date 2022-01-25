//
//  SignInScreen.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 13.01.2022.
//

import AlertToast
import SwiftUI

public extension View {
    @inlinable func validate(of: String, error: @escaping (_ error: String?) -> Void) -> some View {
        onChange(of: of, perform: { value in
            error(nil)
            if value.contains("0") {
                error("Nickname contains numbers")
            } else if value.count > 10 {
                error("Nickname too long")
            }
        })
    }
}

struct AppTextField: View {
    var clickError: (_ error: String?) -> Void

    @State private var username: String = ""
    @State private var usernameError: String?

    var body: some View {
        ZStack {
            TextField(text: $username, prompt: Text(L10nSignIn.formNickname)) {
                Text(L10nSignIn.formNickname)
            }
            .validate(of: username, error: { value in
                usernameError = value
            })
            .keyboardType(.asciiCapable)
            .padding(.trailing, usernameError != nil ? 30 : 0)

            HStack {
                Spacer()
                if usernameError != nil {
                    Image(systemName: "exclamationmark.circle.fill")
                        .foregroundColor(.error)
                        .onTapGesture(count: 1) {
                            clickError(usernameError)
                        }
                }
            }
        }
    }
}

struct SignInScreen: View {
    // model
    @ObservedObject var viewModel = SignInViewModel()
    // graph
    @EnvironmentObject var graph: GraphObservable
    // router
    @EnvironmentObject var router: RouterGuest
    // form states
    @State private var username: String = ""
    @State private var usernameError: String?
    @State private var isShowError: Bool = false
    // page values
    @Environment(\.openURL) var openURL

    var body: some View {
        Form {
            Section {
                AppTextField(clickError: { error in
                    usernameError = error
                    if error != nil {
                        isShowError = true
                    }
                })
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
        .toast(isPresenting: $isShowError) {
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

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
    @State private var fields: [IField] = [
        NicknameField(),
    ]

    // page values
    @Environment(\.openURL) var openURL

    var body: some View {
        if viewModel.isShowProgressView {
            VStack {
                if viewModel.error != nil {
                    ErrorView(error: viewModel.error) {
                        viewModel.clear()
                    }
                } else {
                    ProgressView()
                        .progressViewStyle(CircularProgressViewStyle(tint: .orange))
                }
            }
            .navigationBarBackButtonHidden(true)
            .navigationBarTitle(L10nSignIn.title)
            .navigationBarTitleDisplayMode(.inline)
        } else {
            AppForm(error: $error) {
                Section {
                    ForEach(0 ... fields.count - 1, id: \.self) {
                        AppTextField(field: $fields[$0]) { error in
                            self.error = error
                        }
                    }
                }
                Section {
                    Button(L10nSignIn.formButtonSubmit) {
                        openURL(AppHelper.getOauthLink(fields[0].value))
                    }
                    .buttonStyle(BottomPrimaryStyle())
                    .disabled(fields.isNotValid())
                    .listRowInsets(.init())
                    .listRowBackground(Color.clear)
                }
                .listRowBackground(Color.clear)
            }.onOpenURL { url in
                viewModel.authUser(url: url) {
                    graph.route = .user
                    router.route = .welcome
                }
            }
        }
    }
}

struct SignInScreen_Previews: PreviewProvider {
    static var previews: some View {
        SignInScreen()
    }
}

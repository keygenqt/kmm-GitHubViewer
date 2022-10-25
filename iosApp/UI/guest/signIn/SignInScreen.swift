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
    // environment
    @Environment(\.openURL) var openURL
    // form states
    @State private var error: String?
    // form value
#if DEBUG
    @State private var fieldNickname: IFieldText = NicknameField(value: ConstantsKMM.CONST.DEBUG_CREDENTIAL_LOGIN)
#else
    @State private var fieldNickname: IFieldText = NicknameField()
#endif
    
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
                    AppFieldText(field: $fieldNickname) { error in
                        self.error = error
                    }
                }
                Section {
                    Button(L10nSignIn.formButtonSubmit) {
                        openURL(AppHelper.getOauthLink(fieldNickname.value))
                    }
                    .buttonStyle(BottomPrimaryStyle())
                    .disabled(!fieldNickname.isValid)
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
            .navigationBarTitle(L10nSignIn.title)
            .navigationBarTitleDisplayMode(.inline)
        }
    }
}

struct SignInScreen_Previews: PreviewProvider {
    static var previews: some View {
        SignInScreen()
    }
}

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
    // page values
    @State private var username: String = ""

    var body: some View {
        Form {
            Section {
                TextField(text: $username, prompt: Text(L10nSignIn.formNickname)) {
                    Text(L10nSignIn.formNickname)
                }
                .keyboardType(.asciiCapable)
            }
            Section {
                Button(action: {
                    graph.route = .user
                    router.route = .welcome
                }, label: {
                    HStack {
                        Spacer()
                        Text(L10nSignIn.formButtonSubmit)
                            .font(.headline)
                            .foregroundColor(.white)
                        Spacer()
                    }
                })
                .buttonStyle(BottomPrimaryStyle())
                .listRowInsets(.init())
                .listRowBackground(Color.clear)
            }
            .listRowBackground(Color.clear)
        }
        .navigationBarTitle(L10nSignIn.title)
        .navigationBarTitleDisplayMode(.inline)
    }
}

struct SignInScreen_Previews: PreviewProvider {
    static var previews: some View {
        SignInScreen()
    }
}

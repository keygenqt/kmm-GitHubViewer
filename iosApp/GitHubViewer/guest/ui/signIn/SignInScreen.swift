//
//  SignInScreen.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 13.01.2022.
//

import SwiftUI

struct FormButton: ButtonStyle {
    func makeBody(configuration: Configuration) -> some View {
        configuration.label
            .padding()
            .foregroundColor(.white)
            .background(configuration.isPressed ? Color.gray : Color.black)
            .clipShape(Capsule())
            .scaleEffect(configuration.isPressed ? 0.99 : 1)
            .animation(.easeOut(duration: 0.2), value: configuration.isPressed)
    }
}

struct SignInScreen: View {
    // model
    @ObservedObject var viewModel = SignInViewModel()
    // graph
    @EnvironmentObject var graph: GraphObservable
    // router
    @EnvironmentObject var router: RouterGuest
    // page states
    @State private var username: String = ""

    var body: some View {
        Form {
            Section {
                TextField(text: $username, prompt: Text("Nickname")) {
                    Text("Nickname")
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
                        Text("Save")
                            .font(.headline)
                            .foregroundColor(.white)
                        Spacer()
                    }
                })
                .buttonStyle(FormButton())
                .listRowInsets(.init())
                .listRowBackground(Color.clear)
            }
            .listRowBackground(Color.clear)
        }
        .navigationBarTitle("SignIn")
        .navigationBarTitleDisplayMode(.inline)
    }
}

struct SignInScreen_Previews: PreviewProvider {
    static var previews: some View {
        SignInScreen()
    }
}

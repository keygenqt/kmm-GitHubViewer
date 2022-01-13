//
//  SignInScreen.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 13.01.2022.
//

import SwiftUI

struct SignInScreen: View {
    @ObservedObject var viewModel = SignInViewModel()

    var body: some View {
        NavigationView {
            VStack {
                ProgressView()
                    .progressViewStyle(CircularProgressViewStyle(tint: .orange))
            }
            .navigationTitle("SignInScreen")
        }
    }
}

struct SignInScreen_Previews: PreviewProvider {
    static var previews: some View {
        SignInScreen()
    }
}

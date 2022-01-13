//
//  WelcomeScreen.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 13.01.2022.
//

import SwiftUI

struct WelcomeScreen: View {
    @ObservedObject var viewModel = WelcomeViewModel()

    var body: some View {
        NavigationView {
            VStack {
                ProgressView()
                    .progressViewStyle(CircularProgressViewStyle(tint: .orange))
            }
            .navigationTitle("WelcomeScreen")
        }
    }
}

struct WelcomeScreen_Previews: PreviewProvider {
    static var previews: some View {
        WelcomeScreen()
    }
}

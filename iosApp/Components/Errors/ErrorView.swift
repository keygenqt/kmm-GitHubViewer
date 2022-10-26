//
//  ErrorView.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 17.10.2021.
//

import SwiftUI

struct ErrorView: View {
    @State var error: ResponseError?
    var action: (() -> Void)?

    var body: some View {
        if let error = error {
            Group {
                VStack {
                    Text(error.description)
                        .bold()
                    HStack {
                        Spacer()
                        Button("Dismiss") {
                            action?()
                            self.error = nil
                        }.padding(.top, 5)
                    }
                }
                .padding()
                .background(Color.red)
                .foregroundColor(.white)
                .cornerRadius(10)
            }
            .padding()
        }
    }
}

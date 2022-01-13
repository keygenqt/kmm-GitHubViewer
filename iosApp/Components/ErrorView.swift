//
//  ErrorView.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 17.10.2021.
//

import SwiftUI

struct ErrorView: View {
    @State var error: NetworkError?

    var body: some View {
        if let error = error {
            Group {
                VStack {
                    Text(error.description)
                        .bold()
                    HStack {
                        Button("Dismiss") {
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

struct ErrorView_Previews: PreviewProvider {
    static var previews: some View {
        ErrorView(error: NetworkError.notFound)
    }
}

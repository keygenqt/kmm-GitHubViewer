//
//  ErrorListItemView.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 15.10.2022.
//

import SwiftUI

struct ErrorListItemView: View {
    var error: ResponseError?
    var action: (() -> Void)?

    var body: some View {
        if let error = error {
            VStack {
                Text(error.description)
                    .font(.system(size: 16))
                HStack {
                    Button("Reload") {
                        action?()
                    }
                    .buttonStyle(ButtonOutlineStyle(size: .small))
                }
            }
            .padding()
            .background(Color.red)
            .foregroundColor(.white)
            .cornerRadius(10)
        }
    }
}

//struct ErrorListItemView_Previews: PreviewProvider {
//    static var previews: some View {
//        ErrorListItemView(error: NetworkError.notFound)
//    }
//}

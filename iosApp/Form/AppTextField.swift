//
//  AppTextField.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 03.02.2022.
//

import Foundation
import SwiftUI

struct AppTextField: View {
    var label: String
    @Binding var value: String
    var validates: [(String, String) -> String?] = []
    var actionError: (_ error: String?) -> Void = { _ in }

    @State private var error: String?

    var body: some View {
        ZStack {
            TextField(text: $value, prompt: Text(label)) {
                Text(label)
            }
            .onChange(of: value, perform: { text in
                for validate in validates {
                    error = validate(label, text)
                    if error != nil {
                        break
                    }
                }
            })
            .keyboardType(.asciiCapable)
            .padding(.trailing, error != nil ? 30 : 0)
            HStack {
                Spacer()
                if error != nil {
                    Image(systemName: "exclamationmark.circle.fill")
                        .foregroundColor(.error)
                        .onTapGesture(count: 1) {
                            actionError(error)
                        }
                }
            }
        }
    }
}

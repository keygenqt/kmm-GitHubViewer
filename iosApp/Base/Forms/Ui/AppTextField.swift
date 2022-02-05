//
//  AppTextField.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 03.02.2022.
//

import Foundation
import SwiftUI

struct AppTextField: View {
    @Binding var field: IField
    var actionError: (_ error: String?) -> Void = { _ in }

    @State private var error: String?

    var body: some View {
        ZStack {
            TextField(text: $field.value, prompt: Text(field.label)) {
                Text(field.label)
            }
            .onChange(of: field.value, perform: { text in
                for validate in field.validates {
                    error = validate(field.label, text)
                    if error != nil {
                        break
                    }
                }
                if error != nil {
                    field.isValid = false
                } else {
                    field.isValid = true
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

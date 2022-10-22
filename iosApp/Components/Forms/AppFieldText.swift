//
//  AppFieldText.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 19.10.2022.
//

import SwiftUI

struct AppFieldText: View {
    @Binding var field: IFieldText

    var initValidate: Bool = false
    var actionError: (String?) -> Void = { _ in }

    @State private var error: String?

    var body: some View {
        let validateField: (String) -> Void = { text in
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
        }
        ZStack {
            TextField(text: $field.value, prompt: Text(field.label), axis: field.lineLimit == 1 ... 1 ? .horizontal : .vertical) {
                Text(field.label)
            }
            .lineLimit(field.lineLimit)
            .onChange(of: field.value, perform: { text in
                validateField(text)
            })
            .onAppear {
                if !field.value.isEmpty || initValidate {
                    validateField(field.value)
                }
            }
            .keyboardType(.asciiCapable)
            .padding(.trailing, error != nil ? 30 : 0)
            HStack {
                Spacer()
                VStack {
                    if error != nil {
                        Image(systemName: "exclamationmark.circle.fill")
                            .foregroundColor(.error)
                            .onTapGesture(count: 1) {
                                actionError(error)
                            }
                    }
                    Spacer()
                }.padding(.top, 8)
            }
        }
    }
}

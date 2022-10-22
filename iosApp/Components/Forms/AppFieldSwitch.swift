//
//  AppFieldSwitch.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 19.10.2022.
//

import SwiftUI

struct AppFieldSwitch: View {
    @Binding var field: IFieldSwitch
    @State private var isOn: Bool

    init(field: Binding<IFieldSwitch>) {
        _field = field
        _isOn = State(initialValue: field.wrappedValue.value)
    }

    var body: some View {
        Toggle(field.label, isOn: $isOn).onChange(of: isOn, perform: { value in
            field.value = value
        })
    }
}

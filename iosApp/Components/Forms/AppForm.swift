//
//  AppForm.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 03.02.2022.
//

import AlertToast
import Foundation
import SwiftUI

struct AppForm<Content: View>: View {
    var content: () -> Content

    @Binding var error: String?

    init(error: Binding<String?>, @ViewBuilder content: @escaping () -> Content) {
        self.content = content
        _error = error
    }

    var body: some View {
        Form(content: content)
            .toast(isPresenting: Binding(get: { self.error != nil }, set: { self.error = $0 ? "" : nil })) {
                AlertToast(
                    displayMode: .banner(.pop),
                    type: .systemImage("exclamationmark.circle.fill", .error),
                    title: error ?? "Error validation",
                    style: .style(
                        backgroundColor: .errorContainer,
                        titleColor: .onErrorContainer,
                        subTitleColor: .onErrorContainer,
                        titleFont: Font.custom(PoppinsName(.Medium), size: 16),
                        subTitleFont: Font.custom(PoppinsName(.Medium), size: 12)
                    )
                )
            }
    }
}

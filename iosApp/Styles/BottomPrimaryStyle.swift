//
//  BottomPrimary.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 22.01.2022.
//

import SwiftUI

struct BottomPrimaryStyle: ButtonStyle {
    @Environment(\.isEnabled) var isEnabled

    func makeBody(configuration: Configuration) -> some View {
        configuration.label
            .padding(.vertical, 11)
            .padding(.horizontal, 20)
            .frame(maxWidth: .infinity)
            .font(Font.custom(PoppinsName(.Medium), size: 20))
            .foregroundColor(Color.onPrimary)
            .background(isEnabled ? (configuration.isPressed ? Color.outline : Color.primary) : Color.gray)
            .clipShape(Capsule())
            .scaleEffect(configuration.isPressed ? 0.99 : 1)
            .animation(.easeOut(duration: 0.2), value: configuration.isPressed)
    }
}

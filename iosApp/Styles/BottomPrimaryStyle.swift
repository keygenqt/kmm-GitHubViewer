//
//  BottomPrimary.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 22.01.2022.
//

import SwiftUI

struct BottomPrimaryStyle: ButtonStyle {
    func makeBody(configuration: Configuration) -> some View {
        configuration.label
            .padding(.vertical, 11)
            .padding(.horizontal, 20)
            .frame(maxWidth: .infinity)
            .font(Font.custom(PoppinsName(.Medium), size: 20))
            .foregroundColor(Color.onPrimary)
            .background(configuration.isPressed ? Color.outline : Color.primary)
            .clipShape(Capsule())
            .scaleEffect(configuration.isPressed ? 0.99 : 1)
            .animation(.easeOut(duration: 0.2), value: configuration.isPressed)
    }
}

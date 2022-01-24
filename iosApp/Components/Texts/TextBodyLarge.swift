//
//  TextBodyLarge.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 15.01.2022.
//

import SwiftUI

struct TextBodyLarge: View {
    var text: String
    var maxLines = Int.max
    var color = Color.onBackground
    var alignment: TextAlignment = .leading

    var body: some View {
        Text(text)
            .font(Font.custom(PoppinsName(.Regular), size: 16))
            .foregroundColor(color)
            .lineSpacing(0.5)
            .lineLimit(maxLines)
            .multilineTextAlignment(alignment)
    }
}

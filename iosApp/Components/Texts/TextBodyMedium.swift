//
//  TextBodyMedium.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 16.01.2022.
//

import SwiftUI

struct TextBodyMedium: View {
    var text: String
    var maxLines = Int.max
    var color = Color.onBackground
    var alignment: TextAlignment = .leading

    var body: some View {
        Text(text.getAttributedString(
            font: FontFamily.Poppins.regular,
            color: color,
            size: 14
        ))
        .fontWeight(.regular)
        .lineSpacing(0.25)
        .lineLimit(maxLines)
        .multilineTextAlignment(alignment)
    }
}

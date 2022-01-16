//
//  TextTitleMedium.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 16.01.2022.
//

import SwiftUI

struct TextTitleMedium: View {
    var text: String
    var maxLines = Int.max
    var color = Color.black
    var alignment: TextAlignment = .leading

    var body: some View {
        Text(text.getAttributedString(
            font: FontFamily.Poppins.medium,
            color: color,
            size: 16
        ))
        .fontWeight(.medium)
        .lineSpacing(0.1)
        .lineLimit(maxLines)
        .multilineTextAlignment(alignment)
    }
}

//
//  TextHeadlineSmall.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 16.01.2022.
//

import SwiftUI

struct TextHeadlineSmall: View {
    var text: String
    var maxLines = Int.max
    var color = Color.black
    var alignment: TextAlignment = .leading

    var body: some View {
        Text(text.getAttributedString(
            font: FontFamily.Poppins.regular,
            color: color,
            size: 24
        ))
        .fontWeight(.regular)
        .lineSpacing(0.5)
        .lineLimit(maxLines)
        .multilineTextAlignment(alignment)
    }
}

//
//  TextDisplaySmall.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 16.01.2022.
//

import SwiftUI

struct TextDisplaySmall: View {
    var text: String
    var maxLines = Int.max
    var color = Color.black
    var alignment: TextAlignment = .leading

    var body: some View {
        Text(text.getAttributedString(
            font: FontFamily.Poppins.regular,
            color: color,
            size: 36
        ))
        .fontWeight(.regular)
        .lineSpacing(0)
        .lineLimit(maxLines)
        .multilineTextAlignment(alignment)
    }
}

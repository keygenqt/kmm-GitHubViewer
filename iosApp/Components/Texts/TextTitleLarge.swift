//
//  TextTitleLarge.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 16.01.2022.
//

import SwiftUI

struct TextTitleLarge: View {
    var text: String
    var maxLines = Int.max
    var color = Color.onBackground
    var alignment: TextAlignment = .leading

    var body: some View {
        Text(text)
            .font(Font.custom(PoppinsName(.Regular), size: 22))
            .foregroundColor(color)
            .fontWeight(.regular)
            .lineSpacing(0)
            .lineLimit(maxLines)
            .multilineTextAlignment(alignment)
    }
}

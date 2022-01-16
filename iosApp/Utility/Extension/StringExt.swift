//
//  AppText.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 15.01.2022.
//

import SwiftUI

/**
 * Genearte AttributedString for texts app
 */
extension String {
    func getAttributedString(
        font: FontConvertible,
        color: Color,
        size: CGFloat
    ) -> AttributedString {
        var attributedString = AttributedString(self)
        attributedString.foregroundColor = color
        attributedString.font = font.font(size: size)
        return attributedString
    }
}

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
    func matches(_ regex: String) -> Bool {
        return range(of: regex, options: .regularExpression, range: nil, locale: nil) != nil
    }
}

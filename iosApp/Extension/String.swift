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

    func toUrl() -> URL {
        return URL(string: self)!
    }

    func capitalizedSentence() -> String {
        let firstLetter = prefix(1).capitalized
        let remainingLetters = dropFirst().lowercased()
        return firstLetter + remainingLetters
    }

    func toTimestamp() -> Double {
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "yyyy-MM-dd'T'HH:mm:ssZ"
        let dateString = dateFormatter.date(from: self)
        return dateString!.timeIntervalSince1970
    }
}

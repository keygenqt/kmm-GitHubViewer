//
//  AppText.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 15.01.2022.
//

import SwiftUI
import shared

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
    
    func toFormatDateShort() -> String {
        return LongKt.dateFormat(StringKt.toTimestamp(self), format: ConstantsKMM.CONST.FORMAT_DATE_SHORT)
    }
}

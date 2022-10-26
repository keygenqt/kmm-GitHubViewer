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
        return LongKt.dateFormat(StringKt.toTimestamp(self), format: ConstantsKMM.CONST.DATE_FORMAT.SHORT)
    }
    
    func getIconLanguage() -> String {
        var icon = ""
        switch self.lowercased() {
        case ConstantsKMM.CONST.LANGUAGE.BASH:
            icon = "b.circle"
        case ConstantsKMM.CONST.LANGUAGE.C:
            icon = "c.circle"
        case ConstantsKMM.CONST.LANGUAGE.CPLUSPLUS:
            icon = "c.circle"
        case ConstantsKMM.CONST.LANGUAGE.DART:
            icon = "d.circle"
        case ConstantsKMM.CONST.LANGUAGE.ELIXIR:
            icon = "e.circle"
        case ConstantsKMM.CONST.LANGUAGE.ERLANG:
            icon = "e.circle"
        case ConstantsKMM.CONST.LANGUAGE.GROOVY:
            icon = "g.circle"
        case ConstantsKMM.CONST.LANGUAGE.HASKELL:
            icon = "h.circle"
        case ConstantsKMM.CONST.LANGUAGE.JAVA:
            icon = "j.circle"
        case ConstantsKMM.CONST.LANGUAGE.JAVASCRIPT:
            icon = "j.circle"
        case ConstantsKMM.CONST.LANGUAGE.KOTLIN:
            icon = "k.circle"
        case ConstantsKMM.CONST.LANGUAGE.PHP:
            icon = "p.circle"
        case ConstantsKMM.CONST.LANGUAGE.PYTHON:
            icon = "p.circle"
        case ConstantsKMM.CONST.LANGUAGE.RUBY:
            icon = "r.circle"
        case ConstantsKMM.CONST.LANGUAGE.RUST:
            icon = "r.circle"
        case ConstantsKMM.CONST.LANGUAGE.SCALA:
            icon = "s.circle"
        default:
            icon = "n.circle"
        }
        return icon
    }

    func getColorLanguage() -> Color {
        var icon: Color = .bashColor
        switch self.lowercased() {
        case ConstantsKMM.CONST.LANGUAGE.BASH:
            icon = .bashColor
        case ConstantsKMM.CONST.LANGUAGE.C:
            icon = .cColor
        case ConstantsKMM.CONST.LANGUAGE.CPLUSPLUS:
            icon = .cplusColor
        case ConstantsKMM.CONST.LANGUAGE.DART:
            icon = .dartColor
        case ConstantsKMM.CONST.LANGUAGE.ELIXIR:
            icon = .elixirColor
        case ConstantsKMM.CONST.LANGUAGE.ERLANG:
            icon = .erlangColor
        case ConstantsKMM.CONST.LANGUAGE.GROOVY:
            icon = .groovyColor
        case ConstantsKMM.CONST.LANGUAGE.HASKELL:
            icon = .haskellColor
        case ConstantsKMM.CONST.LANGUAGE.JAVA:
            icon = .javaColor
        case ConstantsKMM.CONST.LANGUAGE.JAVASCRIPT:
            icon = .jsColor
        case ConstantsKMM.CONST.LANGUAGE.KOTLIN:
            icon = .kotlinColor
        case ConstantsKMM.CONST.LANGUAGE.PHP:
            icon = .phpColor
        case ConstantsKMM.CONST.LANGUAGE.PYTHON:
            icon = .pythonColor
        case ConstantsKMM.CONST.LANGUAGE.RUBY:
            icon = .rubyColor
        case ConstantsKMM.CONST.LANGUAGE.RUST:
            icon = .rustColor
        case ConstantsKMM.CONST.LANGUAGE.SCALA:
            icon = .scalaColor
        default:
            icon = .bashColor
        }
        return icon
    }
}

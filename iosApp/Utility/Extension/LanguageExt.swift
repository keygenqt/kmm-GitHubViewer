//
//  LanguageExt.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 17.10.2021.
//

import Foundation

// swiftlint:disable cyclomatic_complexity
extension ConstantsLanguage {
    static func getIcon(language: String?) -> String {
        var icon = ""
        switch language?.lowercased() {
        case ConstantsLanguage.LANGUAGE_BASH:
            icon = "b.circle"
        case ConstantsLanguage.LANGUAGE_C:
            icon = "c.circle"
        case ConstantsLanguage.LANGUAGE_CPLUSPLUS:
            icon = "c.circle"
        case ConstantsLanguage.LANGUAGE_DART:
            icon = "d.circle"
        case ConstantsLanguage.LANGUAGE_ELIXIR:
            icon = "e.circle"
        case ConstantsLanguage.LANGUAGE_ERLANG:
            icon = "e.circle"
        case ConstantsLanguage.LANGUAGE_GROOVY:
            icon = "g.circle"
        case ConstantsLanguage.LANGUAGE_HASKELL:
            icon = "h.circle"
        case ConstantsLanguage.LANGUAGE_JAVA:
            icon = "j.circle"
        case ConstantsLanguage.LANGUAGE_JAVASCRIPT:
            icon = "j.circle"
        case ConstantsLanguage.LANGUAGE_KOTLIN:
            icon = "k.circle"
        case ConstantsLanguage.LANGUAGE_PHP:
            icon = "p.circle"
        case ConstantsLanguage.LANGUAGE_PYTHON:
            icon = "p.circle"
        case ConstantsLanguage.LANGUAGE_RUBY:
            icon = "r.circle"
        case ConstantsLanguage.LANGUAGE_RUST:
            icon = "r.circle"
        case ConstantsLanguage.LANGUAGE_SCALA:
            icon = "s.circle"
        default:
            icon = "n.circle"
        }
        return icon
    }
}

//
//  LanguageExt.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 17.10.2021.
//

import Foundation
import SwiftUI

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

    static func getColor(language: String?) -> Color {
        var icon: Color = .bashColor
        switch language?.lowercased() {
        case ConstantsLanguage.LANGUAGE_BASH:
            icon = .bashColor
        case ConstantsLanguage.LANGUAGE_C:
            icon = .cColor
        case ConstantsLanguage.LANGUAGE_CPLUSPLUS:
            icon = .cplusColor
        case ConstantsLanguage.LANGUAGE_DART:
            icon = .dartColor
        case ConstantsLanguage.LANGUAGE_ELIXIR:
            icon = .elixirColor
        case ConstantsLanguage.LANGUAGE_ERLANG:
            icon = .erlangColor
        case ConstantsLanguage.LANGUAGE_GROOVY:
            icon = .groovyColor
        case ConstantsLanguage.LANGUAGE_HASKELL:
            icon = .haskellColor
        case ConstantsLanguage.LANGUAGE_JAVA:
            icon = .javaColor
        case ConstantsLanguage.LANGUAGE_JAVASCRIPT:
            icon = .jsColor
        case ConstantsLanguage.LANGUAGE_KOTLIN:
            icon = .kotlinColor
        case ConstantsLanguage.LANGUAGE_PHP:
            icon = .phpColor
        case ConstantsLanguage.LANGUAGE_PYTHON:
            icon = .pythonColor
        case ConstantsLanguage.LANGUAGE_RUBY:
            icon = .rubyColor
        case ConstantsLanguage.LANGUAGE_RUST:
            icon = .rustColor
        case ConstantsLanguage.LANGUAGE_SCALA:
            icon = .scalaColor
        default:
            icon = .bashColor
        }
        return icon
    }
}

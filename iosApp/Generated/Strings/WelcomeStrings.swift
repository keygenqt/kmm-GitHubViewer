// swiftlint:disable all
// Generated using SwiftGen — https://github.com/SwiftGen/SwiftGen

import Foundation

// swiftlint:disable superfluous_disable_command file_length implicit_return prefer_self_in_static_references

// MARK: - Strings

// swiftlint:disable explicit_type_interface function_parameter_count identifier_name line_length
// swiftlint:disable nesting type_body_length type_name vertical_whitespace_opening_braces
internal enum L10nWelcome {
  /// Get started
  internal static let btnSignIn = L10nWelcome.tr("Welcome", "btn_sign_in", fallback: "Get started")
  /// The app is written using the latest stack on ios and android. Keywords: KMM, MVVM, Jetpack Compose, SwiftUI
  internal static let subtitle = L10nWelcome.tr("Welcome", "subtitle", fallback: "The app is written using the latest stack on ios and android. Keywords: KMM, MVVM, Jetpack Compose, SwiftUI")
  /// Welcome.strings
  ///   GitHubViewer
  /// 
  ///   Created by Виталий Зарубин on 15.01.2022.
  internal static let title = L10nWelcome.tr("Welcome", "title", fallback: "Welcome")
  /// Version: %s
  internal static func version(_ p1: UnsafePointer<CChar>) -> String {
    return L10nWelcome.tr("Welcome", "version", p1, fallback: "Version: %s")
  }
}
// swiftlint:enable explicit_type_interface function_parameter_count identifier_name line_length
// swiftlint:enable nesting type_body_length type_name vertical_whitespace_opening_braces

// MARK: - Implementation Details

extension L10nWelcome {
  private static func tr(_ table: String, _ key: String, _ args: CVarArg..., fallback value: String) -> String {
    let format = BundleToken.bundle.localizedString(forKey: key, value: value, table: table)
    return String(format: format, locale: Locale.current, arguments: args)
  }
}

// swiftlint:disable convenience_type
private final class BundleToken {
  static let bundle: Bundle = {
    #if SWIFT_PACKAGE
    return Bundle.module
    #else
    return Bundle(for: BundleToken.self)
    #endif
  }()
}
// swiftlint:enable convenience_type

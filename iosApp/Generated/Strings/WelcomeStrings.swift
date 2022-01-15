// swiftlint:disable all
// Generated using SwiftGen — https://github.com/SwiftGen/SwiftGen

import Foundation

// swiftlint:disable superfluous_disable_command file_length implicit_return

// MARK: - Strings

// swiftlint:disable explicit_type_interface function_parameter_count identifier_name line_length
// swiftlint:disable nesting type_body_length type_name vertical_whitespace_opening_braces
internal enum L10nWelcome {
  /// Get started
  internal static let btnSignIn = L10nWelcome.tr("Welcome", "btn_sign_in")
  /// The app is written using the latest stack on ios and android. Keywords: KMM, MVVM, Jetpack Compose, SwiftUI
  internal static let subtitle = L10nWelcome.tr("Welcome", "subtitle")
  /// Welcome to
  /// GitHub Viewer!
  internal static let title = L10nWelcome.tr("Welcome", "title")
  /// Version: %s
  internal static func version(_ p1: UnsafePointer<CChar>) -> String {
    return L10nWelcome.tr("Welcome", "version", p1)
  }
}
// swiftlint:enable explicit_type_interface function_parameter_count identifier_name line_length
// swiftlint:enable nesting type_body_length type_name vertical_whitespace_opening_braces

// MARK: - Implementation Details

extension L10nWelcome {
  private static func tr(_ table: String, _ key: String, _ args: CVarArg...) -> String {
    let format = BundleToken.bundle.localizedString(forKey: key, value: nil, table: table)
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

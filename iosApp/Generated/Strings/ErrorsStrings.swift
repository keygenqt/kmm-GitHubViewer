// swiftlint:disable all
// Generated using SwiftGen — https://github.com/SwiftGen/SwiftGen

import Foundation

// swiftlint:disable superfluous_disable_command file_length implicit_return prefer_self_in_static_references

// MARK: - Strings

// swiftlint:disable explicit_type_interface function_parameter_count identifier_name line_length
// swiftlint:disable nesting type_body_length type_name vertical_whitespace_opening_braces
internal enum L10nErrors {
  /// Errors.strings
  ///   GitHubViewer
  /// 
  ///   Created by Виталий Зарубин on 15.01.2022.
  internal static let error401 = L10nErrors.tr("Errors", "error_401", fallback: "Token expired")
  /// Something wrong
  internal static let error404 = L10nErrors.tr("Errors", "error_404", fallback: "Something wrong")
  /// Bad credentials
  internal static let errorBadCredentials = L10nErrors.tr("Errors", "error_bad_credentials", fallback: "Bad credentials")
  /// Something wrong
  internal static let errorExceptionUnknown = L10nErrors.tr("Errors", "error_exception_unknown", fallback: "Something wrong")
  /// Something wrong
  internal static let errorJsonParse = L10nErrors.tr("Errors", "error_json_parse", fallback: "Something wrong")
  /// Something wrong
  internal static let errorSomethingWrong = L10nErrors.tr("Errors", "error_something_wrong", fallback: "Something wrong")
  /// Something wrong
  internal static let errorSuccessError = L10nErrors.tr("Errors", "error_success_error", fallback: "Something wrong")
}
// swiftlint:enable explicit_type_interface function_parameter_count identifier_name line_length
// swiftlint:enable nesting type_body_length type_name vertical_whitespace_opening_braces

// MARK: - Implementation Details

extension L10nErrors {
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

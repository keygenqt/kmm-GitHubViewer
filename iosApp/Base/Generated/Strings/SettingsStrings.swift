// swiftlint:disable all
// Generated using SwiftGen — https://github.com/SwiftGen/SwiftGen

import Foundation

// swiftlint:disable superfluous_disable_command file_length implicit_return

// MARK: - Strings

// swiftlint:disable explicit_type_interface function_parameter_count identifier_name line_length
// swiftlint:disable nesting type_body_length type_name vertical_whitespace_opening_braces
internal enum L10nSettings {
  /// Bio
  internal static let formBio = L10nSettings.tr("Settings", "form_bio")
  /// Blog URL
  internal static let formBlog = L10nSettings.tr("Settings", "form_blog")
  /// Submit
  internal static let formButtonSubmit = L10nSettings.tr("Settings", "form_button_submit")
  /// Company
  internal static let formCompany = L10nSettings.tr("Settings", "form_company")
  /// Location
  internal static let formLocation = L10nSettings.tr("Settings", "form_location")
  /// Name
  internal static let formName = L10nSettings.tr("Settings", "form_name")
  /// Update user data successfully
  internal static let formSuccessfully = L10nSettings.tr("Settings", "form_successfully")
  /// Twitter nickname
  internal static let formTwitterUsername = L10nSettings.tr("Settings", "form_twitter_username")
  /// Settings
  internal static let title = L10nSettings.tr("Settings", "title")
}
// swiftlint:enable explicit_type_interface function_parameter_count identifier_name line_length
// swiftlint:enable nesting type_body_length type_name vertical_whitespace_opening_braces

// MARK: - Implementation Details

extension L10nSettings {
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

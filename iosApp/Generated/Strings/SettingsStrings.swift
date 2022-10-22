// swiftlint:disable all
// Generated using SwiftGen — https://github.com/SwiftGen/SwiftGen

import Foundation

// swiftlint:disable superfluous_disable_command file_length implicit_return prefer_self_in_static_references

// MARK: - Strings

// swiftlint:disable explicit_type_interface function_parameter_count identifier_name line_length
// swiftlint:disable nesting type_body_length type_name vertical_whitespace_opening_braces
internal enum L10nSettings {
  /// Blog URL
  internal static let formBlog = L10nSettings.tr("Settings", "form_blog", fallback: "Blog URL")
  /// Submit
  internal static let formButtonSubmit = L10nSettings.tr("Settings", "form_button_submit", fallback: "Submit")
  /// Company
  internal static let formCompany = L10nSettings.tr("Settings", "form_company", fallback: "Company")
  /// About
  internal static let formGroupAbout = L10nSettings.tr("Settings", "form_group_about", fallback: "About")
  /// Bio
  internal static let formGroupBio = L10nSettings.tr("Settings", "form_group_bio", fallback: "Bio")
  /// Links
  internal static let formGroupLinks = L10nSettings.tr("Settings", "form_group_links", fallback: "Links")
  /// Location
  internal static let formLocation = L10nSettings.tr("Settings", "form_location", fallback: "Location")
  /// Name
  internal static let formName = L10nSettings.tr("Settings", "form_name", fallback: "Name")
  /// Update user data successfully
  internal static let formSuccessfully = L10nSettings.tr("Settings", "form_successfully", fallback: "Update user data successfully")
  /// Twitter nickname
  internal static let formTwitterUsername = L10nSettings.tr("Settings", "form_twitter_username", fallback: "Twitter nickname")
  /// Settings.strings
  ///   GitHubViewer
  /// 
  ///   Created by Виталий Зарубин on 15.01.2022.
  internal static let title = L10nSettings.tr("Settings", "title", fallback: "Settings")
}
// swiftlint:enable explicit_type_interface function_parameter_count identifier_name line_length
// swiftlint:enable nesting type_body_length type_name vertical_whitespace_opening_braces

// MARK: - Implementation Details

extension L10nSettings {
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

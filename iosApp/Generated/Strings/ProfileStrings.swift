// swiftlint:disable all
// Generated using SwiftGen — https://github.com/SwiftGen/SwiftGen

import Foundation

// swiftlint:disable superfluous_disable_command file_length implicit_return

// MARK: - Strings

// swiftlint:disable explicit_type_interface function_parameter_count identifier_name line_length
// swiftlint:disable nesting type_body_length type_name vertical_whitespace_opening_braces
internal enum L10nProfile {
  /// Confirm
  internal static let dialogLogoutConfirm = L10nProfile.tr("Profile", "dialog_logout_confirm")
  /// Dismiss
  internal static let dialogLogoutDismiss = L10nProfile.tr("Profile", "dialog_logout_dismiss")
  /// You will be redirected to the login page. Is this what you want?
  internal static let dialogLogoutText = L10nProfile.tr("Profile", "dialog_logout_text")
  /// Exit the application
  internal static let dialogLogoutTitle = L10nProfile.tr("Profile", "dialog_logout_title")
  /// Bio
  internal static let labelBio = L10nProfile.tr("Profile", "label_bio")
  /// Blog
  internal static let labelBlog = L10nProfile.tr("Profile", "label_blog")
  /// Company
  internal static let labelCompany = L10nProfile.tr("Profile", "label_company")
  /// Date created profile
  internal static let labelCreatedAt = L10nProfile.tr("Profile", "label_created_at")
  /// Email
  internal static let labelEmail = L10nProfile.tr("Profile", "label_email")
  /// Followers
  internal static let labelItemCountFollowers = L10nProfile.tr("Profile", "label_item_count_followers")
  /// Following
  internal static let labelItemCountFollowing = L10nProfile.tr("Profile", "label_item_count_following")
  /// Pub Repos
  internal static let labelItemCountPublicRepos = L10nProfile.tr("Profile", "label_item_count_public_repos")
  /// Location
  internal static let labelLocation = L10nProfile.tr("Profile", "label_location")
  /// Profile
  internal static let title = L10nProfile.tr("Profile", "title")
}
// swiftlint:enable explicit_type_interface function_parameter_count identifier_name line_length
// swiftlint:enable nesting type_body_length type_name vertical_whitespace_opening_braces

// MARK: - Implementation Details

extension L10nProfile {
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

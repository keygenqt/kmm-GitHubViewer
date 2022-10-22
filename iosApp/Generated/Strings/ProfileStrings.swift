// swiftlint:disable all
// Generated using SwiftGen — https://github.com/SwiftGen/SwiftGen

import Foundation

// swiftlint:disable superfluous_disable_command file_length implicit_return prefer_self_in_static_references

// MARK: - Strings

// swiftlint:disable explicit_type_interface function_parameter_count identifier_name line_length
// swiftlint:disable nesting type_body_length type_name vertical_whitespace_opening_braces
internal enum L10nProfile {
  /// Confirm
  internal static let dialogLogoutConfirm = L10nProfile.tr("Profile", "dialog_logout_confirm", fallback: "Confirm")
  /// Dismiss
  internal static let dialogLogoutDismiss = L10nProfile.tr("Profile", "dialog_logout_dismiss", fallback: "Dismiss")
  /// You will be redirected to the login page.
  /// Is this what you want?
  internal static let dialogLogoutText = L10nProfile.tr("Profile", "dialog_logout_text", fallback: "You will be redirected to the login page.\nIs this what you want?")
  /// Exit the application
  internal static let dialogLogoutTitle = L10nProfile.tr("Profile", "dialog_logout_title", fallback: "Exit the application")
  /// Bio
  internal static let labelBio = L10nProfile.tr("Profile", "label_bio", fallback: "Bio")
  /// Blog
  internal static let labelBlog = L10nProfile.tr("Profile", "label_blog", fallback: "Blog")
  /// Date created profile
  internal static let labelCreatedAt = L10nProfile.tr("Profile", "label_created_at", fallback: "Date created profile")
  /// Followers
  internal static let labelItemCountFollowers = L10nProfile.tr("Profile", "label_item_count_followers", fallback: "Followers")
  /// Following
  internal static let labelItemCountFollowing = L10nProfile.tr("Profile", "label_item_count_following", fallback: "Following")
  /// Pub Repos
  internal static let labelItemCountPublicRepos = L10nProfile.tr("Profile", "label_item_count_public_repos", fallback: "Pub Repos")
  /// Location
  internal static let labelLocation = L10nProfile.tr("Profile", "label_location", fallback: "Location")
  /// Profile.strings
  ///   GitHubViewer
  /// 
  ///   Created by Виталий Зарубин on 15.01.2022.
  internal static let title = L10nProfile.tr("Profile", "title", fallback: "Profile")
}
// swiftlint:enable explicit_type_interface function_parameter_count identifier_name line_length
// swiftlint:enable nesting type_body_length type_name vertical_whitespace_opening_braces

// MARK: - Implementation Details

extension L10nProfile {
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

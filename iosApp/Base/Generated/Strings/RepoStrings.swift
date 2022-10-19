// swiftlint:disable all
// Generated using SwiftGen — https://github.com/SwiftGen/SwiftGen

import Foundation

// swiftlint:disable superfluous_disable_command file_length implicit_return

// MARK: - Strings

// swiftlint:disable explicit_type_interface function_parameter_count identifier_name line_length
// swiftlint:disable nesting type_body_length type_name vertical_whitespace_opening_braces
internal enum L10nRepo {
  /// Copy of a repository
  internal static let iconForksText = L10nRepo.tr("Repo", "icon_forks_text")
  /// Forks
  internal static let iconForksTitle = L10nRepo.tr("Repo", "icon_forks_title")
  /// Positive ratings
  internal static let iconStarText = L10nRepo.tr("Repo", "icon_star_text")
  /// Star
  internal static let iconStarTitle = L10nRepo.tr("Repo", "icon_star_title")
  /// Created
  internal static let labelCreatedAt = L10nRepo.tr("Repo", "label_created_at")
  /// Description
  internal static let labelDescription = L10nRepo.tr("Repo", "label_description")
  /// Full name
  internal static let labelFullName = L10nRepo.tr("Repo", "label_full_name")
  /// License
  internal static let labelLicense = L10nRepo.tr("Repo", "label_license")
  /// Owner
  internal static let labelOwner = L10nRepo.tr("Repo", "label_owner")
  /// Size
  internal static let labelSize = L10nRepo.tr("Repo", "label_size")
  /// Updated
  internal static let labelUpdatedAt = L10nRepo.tr("Repo", "label_updated_at")
  /// Is visibility
  internal static let labelVisibility = L10nRepo.tr("Repo", "label_visibility")
  /// Issue
  internal static let openIssue = L10nRepo.tr("Repo", "open_issue")
  /// Watchers
  internal static let openWatchers = L10nRepo.tr("Repo", "open_watchers")
  /// Repo
  internal static let title = L10nRepo.tr("Repo", "title")
}
// swiftlint:enable explicit_type_interface function_parameter_count identifier_name line_length
// swiftlint:enable nesting type_body_length type_name vertical_whitespace_opening_braces

// MARK: - Implementation Details

extension L10nRepo {
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

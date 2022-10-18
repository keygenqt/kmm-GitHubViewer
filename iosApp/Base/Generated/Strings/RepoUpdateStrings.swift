// swiftlint:disable all
// Generated using SwiftGen — https://github.com/SwiftGen/SwiftGen

import Foundation

// swiftlint:disable superfluous_disable_command file_length implicit_return

// MARK: - Strings

// swiftlint:disable explicit_type_interface function_parameter_count identifier_name line_length
// swiftlint:disable nesting type_body_length type_name vertical_whitespace_opening_braces
internal enum L10nRepoUpdate {
  /// Save
  internal static let formButtonSave = L10nRepoUpdate.tr("RepoUpdate", "form_button_save")
  /// Base
  internal static let formGroupBase = L10nRepoUpdate.tr("RepoUpdate", "form_group_base")
  /// Visibility
  internal static let formGroupVisibility = L10nRepoUpdate.tr("RepoUpdate", "form_group_visibility")
  /// Description
  internal static let formLabelDesc = L10nRepoUpdate.tr("RepoUpdate", "form_label_desc")
  /// Private Repo
  internal static let formLabelIsPrivate = L10nRepoUpdate.tr("RepoUpdate", "form_label_is_private")
  /// Name
  internal static let formLabelName = L10nRepoUpdate.tr("RepoUpdate", "form_label_name")
  /// Repo Update
  internal static let title = L10nRepoUpdate.tr("RepoUpdate", "title")
}
// swiftlint:enable explicit_type_interface function_parameter_count identifier_name line_length
// swiftlint:enable nesting type_body_length type_name vertical_whitespace_opening_braces

// MARK: - Implementation Details

extension L10nRepoUpdate {
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

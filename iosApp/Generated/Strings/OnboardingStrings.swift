// swiftlint:disable all
// Generated using SwiftGen — https://github.com/SwiftGen/SwiftGen

import Foundation

// swiftlint:disable superfluous_disable_command file_length implicit_return

// MARK: - Strings

// swiftlint:disable explicit_type_interface function_parameter_count identifier_name line_length
// swiftlint:disable nesting type_body_length type_name vertical_whitespace_opening_braces
internal enum L10nOnboarding {
  /// Skip
  internal static let skip = L10nOnboarding.tr("Onboarding", "skip")
  /// This is a demo application using the open GitHub REST API. The application is under development. Develop will take place in 3 stages.
  internal static let step1Text = L10nOnboarding.tr("Onboarding", "step1_text")
  /// About the app
  internal static let step1Title = L10nOnboarding.tr("Onboarding", "step1_title")
  /// Jetpack Compose application, MVVM and the whole latest stack surrounding this technology
  internal static let step2Text = L10nOnboarding.tr("Onboarding", "step2_text")
  /// First stage
  internal static let step2Title = L10nOnboarding.tr("Onboarding", "step2_title")
  /// Development of a shared KMM module for interacting with an ios application
  internal static let step3Text = L10nOnboarding.tr("Onboarding", "step3_text")
  /// About the app
  internal static let step3Title = L10nOnboarding.tr("Onboarding", "step3_title")
  /// Application for ios on Swift using the previously developed shared module using KMM
  internal static let step4Text = L10nOnboarding.tr("Onboarding", "step4_text")
  /// About the app
  internal static let step4Title = L10nOnboarding.tr("Onboarding", "step4_title")
  /// This app uses the latest Google & JetBrains & Apple. Thank them for this opportunity! Thank you for your interest! Thanks to my wife for your patience :)
  internal static let step5Text = L10nOnboarding.tr("Onboarding", "step5_text")
  /// About the app
  internal static let step5Title = L10nOnboarding.tr("Onboarding", "step5_title")
  /// Hello!
  internal static let title = L10nOnboarding.tr("Onboarding", "title")
}
// swiftlint:enable explicit_type_interface function_parameter_count identifier_name line_length
// swiftlint:enable nesting type_body_length type_name vertical_whitespace_opening_braces

// MARK: - Implementation Details

extension L10nOnboarding {
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

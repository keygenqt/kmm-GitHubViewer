// swiftlint:disable all
// Generated using SwiftGen — https://github.com/SwiftGen/SwiftGen

import Foundation

// swiftlint:disable superfluous_disable_command file_length implicit_return prefer_self_in_static_references

// MARK: - Strings

// swiftlint:disable explicit_type_interface function_parameter_count identifier_name line_length
// swiftlint:disable nesting type_body_length type_name vertical_whitespace_opening_braces
internal enum L10nOnboarding {
  /// Back
  internal static let back = L10nOnboarding.tr("Onboarding", "back", fallback: "Back")
  /// Skip
  internal static let skip = L10nOnboarding.tr("Onboarding", "skip", fallback: "Skip")
  /// This is a demo application using the open GitHub REST API. The application is under development. Develop will take place in 3 stages.
  internal static let step1Text = L10nOnboarding.tr("Onboarding", "step1_text", fallback: "This is a demo application using the open GitHub REST API. The application is under development. Develop will take place in 3 stages.")
  /// About the app
  internal static let step1Title = L10nOnboarding.tr("Onboarding", "step1_title", fallback: "About the app")
  /// Jetpack Compose application, MVVM and the whole latest stack surrounding this technology
  internal static let step2Text = L10nOnboarding.tr("Onboarding", "step2_text", fallback: "Jetpack Compose application, MVVM and the whole latest stack surrounding this technology")
  /// First stage
  internal static let step2Title = L10nOnboarding.tr("Onboarding", "step2_title", fallback: "First stage")
  /// Development of a shared KMM module for interacting with an ios application
  internal static let step3Text = L10nOnboarding.tr("Onboarding", "step3_text", fallback: "Development of a shared KMM module for interacting with an ios application")
  /// About the app
  internal static let step3Title = L10nOnboarding.tr("Onboarding", "step3_title", fallback: "About the app")
  /// Application for ios on Swift using the previously developed shared module using KMM
  internal static let step4Text = L10nOnboarding.tr("Onboarding", "step4_text", fallback: "Application for ios on Swift using the previously developed shared module using KMM")
  /// About the app
  internal static let step4Title = L10nOnboarding.tr("Onboarding", "step4_title", fallback: "About the app")
  /// This app uses the latest Google & JetBrains & Apple. Thank them for this opportunity! Thank you for your interest! Thanks to my wife for your patience :)
  internal static let step5Text = L10nOnboarding.tr("Onboarding", "step5_text", fallback: "This app uses the latest Google & JetBrains & Apple. Thank them for this opportunity! Thank you for your interest! Thanks to my wife for your patience :)")
  /// About the app
  internal static let step5Title = L10nOnboarding.tr("Onboarding", "step5_title", fallback: "About the app")
  /// Other.strings
  ///   GitHubViewer
  /// 
  ///   Created by Виталий Зарубин on 15.01.2022.
  internal static let title = L10nOnboarding.tr("Onboarding", "title", fallback: "Hello!")
}
// swiftlint:enable explicit_type_interface function_parameter_count identifier_name line_length
// swiftlint:enable nesting type_body_length type_name vertical_whitespace_opening_braces

// MARK: - Implementation Details

extension L10nOnboarding {
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

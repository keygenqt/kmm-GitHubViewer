// swiftlint:disable all
// Generated using SwiftGen — https://github.com/SwiftGen/SwiftGen

#if os(macOS)
  import AppKit
#elseif os(iOS)
  import UIKit
#elseif os(tvOS) || os(watchOS)
  import UIKit
#endif

// Deprecated typealiases
@available(*, deprecated, renamed: "ColorAsset.Color", message: "This typealias will be removed in SwiftGen 7.0")
internal typealias AssetColorTypeAlias = ColorAsset.Color
@available(*, deprecated, renamed: "ImageAsset.Image", message: "This typealias will be removed in SwiftGen 7.0")
internal typealias AssetImageTypeAlias = ImageAsset.Image

// swiftlint:disable superfluous_disable_command file_length implicit_return

// MARK: - Asset Catalogs

// swiftlint:disable identifier_name line_length nesting type_body_length type_name
internal enum Resources {
  internal enum Assets {
    internal static let accentColor = ColorAsset(name: "AccentColor")
    internal static let arrowBack = ImageAsset(name: "arrow_back")
    internal static let arrowForward = ImageAsset(name: "arrow_forward")
    internal static let chart1 = ImageAsset(name: "chart_1")
    internal static let chart2 = ImageAsset(name: "chart_2")
    internal static let phone = ImageAsset(name: "phone")
    internal static let bgWelcome = ImageAsset(name: "bg_welcome")
    internal static let splash = ImageAsset(name: "splash")
    internal static let user = ImageAsset(name: "user")
  }
  internal enum Colors {
    internal static let bashColor = ColorAsset(name: "bashColor")
    internal static let cColor = ColorAsset(name: "cColor")
    internal static let cplusColor = ColorAsset(name: "cplusColor")
    internal static let dartColor = ColorAsset(name: "dartColor")
    internal static let elixirColor = ColorAsset(name: "elixirColor")
    internal static let erlangColor = ColorAsset(name: "erlangColor")
    internal static let groovyColor = ColorAsset(name: "groovyColor")
    internal static let haskellColor = ColorAsset(name: "haskellColor")
    internal static let javaColor = ColorAsset(name: "javaColor")
    internal static let jsColor = ColorAsset(name: "jsColor")
    internal static let kotlinColor = ColorAsset(name: "kotlinColor")
    internal static let phpColor = ColorAsset(name: "phpColor")
    internal static let pythonColor = ColorAsset(name: "pythonColor")
    internal static let rubyColor = ColorAsset(name: "rubyColor")
    internal static let rustColor = ColorAsset(name: "rustColor")
    internal static let scalaColor = ColorAsset(name: "scalaColor")
    internal static let background = ColorAsset(name: "background")
    internal static let error = ColorAsset(name: "error")
    internal static let errorContainer = ColorAsset(name: "errorContainer")
    internal static let inverseOnSurface = ColorAsset(name: "inverseOnSurface")
    internal static let inverseSurface = ColorAsset(name: "inverseSurface")
    internal static let onBackground = ColorAsset(name: "onBackground")
    internal static let onError = ColorAsset(name: "onError")
    internal static let onErrorContainer = ColorAsset(name: "onErrorContainer")
    internal static let onPrimary = ColorAsset(name: "onPrimary")
    internal static let onPrimaryContainer = ColorAsset(name: "onPrimaryContainer")
    internal static let onSecondary = ColorAsset(name: "onSecondary")
    internal static let onSecondaryContainer = ColorAsset(name: "onSecondaryContainer")
    internal static let onSurface = ColorAsset(name: "onSurface")
    internal static let onSurfaceVariant = ColorAsset(name: "onSurfaceVariant")
    internal static let onTertiary = ColorAsset(name: "onTertiary")
    internal static let onTertiaryContainer = ColorAsset(name: "onTertiaryContainer")
    internal static let outline = ColorAsset(name: "outline")
    internal static let primary = ColorAsset(name: "primary")
    internal static let primaryContainer = ColorAsset(name: "primaryContainer")
    internal static let secondary = ColorAsset(name: "secondary")
    internal static let secondaryContainer = ColorAsset(name: "secondaryContainer")
    internal static let surface = ColorAsset(name: "surface")
    internal static let surfaceVariant = ColorAsset(name: "surfaceVariant")
    internal static let tertiary = ColorAsset(name: "tertiary")
    internal static let tertiaryContainer = ColorAsset(name: "tertiaryContainer")
  }
}
// swiftlint:enable identifier_name line_length nesting type_body_length type_name

// MARK: - Implementation Details

internal final class ColorAsset {
  internal fileprivate(set) var name: String

  #if os(macOS)
  internal typealias Color = NSColor
  #elseif os(iOS) || os(tvOS) || os(watchOS)
  internal typealias Color = UIColor
  #endif

  @available(iOS 11.0, tvOS 11.0, watchOS 4.0, macOS 10.13, *)
  internal private(set) lazy var color: Color = {
    guard let color = Color(asset: self) else {
      fatalError("Unable to load color asset named \(name).")
    }
    return color
  }()

  #if os(iOS) || os(tvOS)
  @available(iOS 11.0, tvOS 11.0, *)
  internal func color(compatibleWith traitCollection: UITraitCollection) -> Color {
    let bundle = BundleToken.bundle
    guard let color = Color(named: name, in: bundle, compatibleWith: traitCollection) else {
      fatalError("Unable to load color asset named \(name).")
    }
    return color
  }
  #endif

  fileprivate init(name: String) {
    self.name = name
  }
}

internal extension ColorAsset.Color {
  @available(iOS 11.0, tvOS 11.0, watchOS 4.0, macOS 10.13, *)
  convenience init?(asset: ColorAsset) {
    let bundle = BundleToken.bundle
    #if os(iOS) || os(tvOS)
    self.init(named: asset.name, in: bundle, compatibleWith: nil)
    #elseif os(macOS)
    self.init(named: NSColor.Name(asset.name), bundle: bundle)
    #elseif os(watchOS)
    self.init(named: asset.name)
    #endif
  }
}

internal struct ImageAsset {
  internal fileprivate(set) var name: String

  #if os(macOS)
  internal typealias Image = NSImage
  #elseif os(iOS) || os(tvOS) || os(watchOS)
  internal typealias Image = UIImage
  #endif

  @available(iOS 8.0, tvOS 9.0, watchOS 2.0, macOS 10.7, *)
  internal var image: Image {
    let bundle = BundleToken.bundle
    #if os(iOS) || os(tvOS)
    let image = Image(named: name, in: bundle, compatibleWith: nil)
    #elseif os(macOS)
    let name = NSImage.Name(self.name)
    let image = (bundle == .main) ? NSImage(named: name) : bundle.image(forResource: name)
    #elseif os(watchOS)
    let image = Image(named: name)
    #endif
    guard let result = image else {
      fatalError("Unable to load image asset named \(name).")
    }
    return result
  }

  #if os(iOS) || os(tvOS)
  @available(iOS 8.0, tvOS 9.0, *)
  internal func image(compatibleWith traitCollection: UITraitCollection) -> Image {
    let bundle = BundleToken.bundle
    guard let result = Image(named: name, in: bundle, compatibleWith: traitCollection) else {
      fatalError("Unable to load image asset named \(name).")
    }
    return result
  }
  #endif
}

internal extension ImageAsset.Image {
  @available(iOS 8.0, tvOS 9.0, watchOS 2.0, *)
  @available(macOS, deprecated,
    message: "This initializer is unsafe on macOS, please use the ImageAsset.image property")
  convenience init?(asset: ImageAsset) {
    #if os(iOS) || os(tvOS)
    let bundle = BundleToken.bundle
    self.init(named: asset.name, in: bundle, compatibleWith: nil)
    #elseif os(macOS)
    self.init(named: NSImage.Name(asset.name))
    #elseif os(watchOS)
    self.init(named: asset.name)
    #endif
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

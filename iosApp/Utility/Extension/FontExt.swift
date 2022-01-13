//
//  FontExt.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 12.10.2021.
//

import Foundation
import SwiftUI

extension FontConvertible.Font {
    static let boldFont = FontFamily.Montserrat.bold
    static let extraBoldFont = FontFamily.Montserrat.extraBold
    static let mediumFont = FontFamily.Montserrat.medium
    static let regularFont = FontFamily.Montserrat.regular
    static let semiBoldFont = FontFamily.Montserrat.semiBold
}

internal extension FontConvertible.Font {
    static let navigationBarTitle = boldFont.font(size: 24)
}

//
//  FontPoppins.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 24.01.2022.
//

import Foundation

enum FontPoppins {
    case Regular,
         Italic,
         Thin,
         ThinItalic,
         ExtraLight,
         ExtraLightItalic,
         Light,
         LightItalic,
         Medium,
         MediumItalic,
         SemiBold,
         SemiBoldItalic,
         Bold,
         BoldItalic,
         ExtraBold,
         ExtraBoldItalic,
         Black,
         BlackItalic
}

func PoppinsName(_ value: FontPoppins) -> String {
    switch value {
    case .Regular:
        return "Poppins-Regular"
    case .Italic:
        return "Poppins-Italic"
    case .Thin:
        return "Poppins-Thin"
    case .ThinItalic:
        return "Poppins-ThinItalic"
    case .ExtraLight:
        return "Poppins-ExtraLight"
    case .ExtraLightItalic:
        return "Poppins-ExtraLightItalic"
    case .Light:
        return "Poppins-Light"
    case .LightItalic:
        return "Poppins-LightItalic"
    case .Medium:
        return "Poppins-Medium"
    case .MediumItalic:
        return "Poppins-MediumItalic"
    case .SemiBold:
        return "Poppins-SemiBold"
    case .SemiBoldItalic:
        return "Poppins-SemiBoldItalic"
    case .Bold:
        return "Poppins-Bold"
    case .BoldItalic:
        return "Poppins-BoldItalic"
    case .ExtraBold:
        return "Poppins-ExtraBold"
    case .ExtraBoldItalic:
        return "Poppins-ExtraBoldItalic"
    case .Black:
        return "Poppins-Black"
    case .BlackItalic:
        return "Poppins-BlackItalic"
    }
}

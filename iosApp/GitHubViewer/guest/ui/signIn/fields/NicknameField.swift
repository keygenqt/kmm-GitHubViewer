//
//  NicknameTextField.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 04.02.2022.
//

import Foundation

// validates
private func checkIsBlank(label: String, text: String) -> String? {
    return text.isEmpty ? "\(label) is required" : nil
}

private func checkIsLong(label: String, text: String) -> String? {
    return text.count >= 39 ? "\(label) too long" : nil
}

private func checkLineFirst(label: String, text: String) -> String? {
    return text.prefix(1) == "-" ? "\(label) does not match" : nil
}

private func checkSymbolLast(label: String, text: String) -> String? {
    return text.last == "-" ? "\(label) does not match" : nil
}

private func checkSymbolDouble(label: String, text: String) -> String? {
    return text.contains("--") ? "\(label) does not match" : nil
}

private func getErrorIsNotMatch(label: String, text: String) -> String? {
    return !text.matches("^[A-Za-z\\d-]+$") ? "\(label) does not match" : nil
}

// field
struct NicknameField: IFieldText {
    // params field
    var label: String = L10nSignIn.formNickname
    var value: String = ""
    var isValid: Bool = false
    var lineLimit = 1 ... 1
    var validates = [
        checkIsBlank,
        checkIsLong,
        checkLineFirst,
        checkSymbolLast,
        checkSymbolDouble,
        getErrorIsNotMatch,
    ]
}

//
//  TwitterField.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 17.10.2022.
//

import Foundation

// validates
private func checkIsBlank(label: String, text: String) -> String? {
    return text.isEmpty ? "\(label) is required" : nil
}

// field
struct TwitterField: IField {
    // params field
    var label: String = L10nSettings.formTwitterUsername
    var value: String = ""
    var isValid: Bool = false
    var lineLimit = 1 ... 1
    var validates = [checkIsBlank]
}

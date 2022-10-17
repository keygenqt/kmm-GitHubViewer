//
//  BioField.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 17.10.2022.
//

import Foundation

// validates
private func checkIsBlank(label _: String, text: String) -> String? {
    return text.isEmpty ? "Bio is required" : nil
}

// field
struct BioField: IField {
    // params field
    var label: String = ""
    var value: String = ""
    var isValid: Bool = false
    var lineLimit = 3 ... 8
    var validates = [checkIsBlank]
}

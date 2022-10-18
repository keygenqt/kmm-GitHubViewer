//
//  DescRepoField.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 18.10.2022.
//

import Foundation

// field
struct DescRepoField: IField {
    // params field
    var label: String = L10nRepoUpdate.formLabelDesc
    var value: String = ""
    var isValid: Bool = false
    var lineLimit = 1 ... 8
    var validates: [(String, String) -> String?] = []
}

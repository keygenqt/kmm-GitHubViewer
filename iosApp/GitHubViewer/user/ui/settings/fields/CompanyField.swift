//
//  CompanyField.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 17.10.2022.
//

import Foundation

// field
struct CompanyField: IField {
    // params field
    var label: String = L10nSettings.formCompany
    var value: String = ""
    var isValid: Bool = false
    var lineLimit = 1 ... 1
    var validates: [(String, String) -> String?] = []
}

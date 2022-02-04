//
//  IDataTextField.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 04.02.2022.
//

import Foundation

protocol IField {
    var label: String { get set }
    var value: String { get set }
    var isValid: Bool { get set }
    var validates: [(String, String) -> String?] { get set }
}

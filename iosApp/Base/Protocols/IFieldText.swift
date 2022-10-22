//
//  IDataTextField.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 04.02.2022.
//

import Foundation

protocol IField {
    var label: String { get set }
}

protocol IFieldSwitch: IField {
    var value: Bool { get set }
}

protocol IFieldText: IField {
    var value: String { get set }
    var isValid: Bool { get set }
    var lineLimit: ClosedRange<Int> { get set }
    var validates: [(String, String) -> String?] { get set }
}

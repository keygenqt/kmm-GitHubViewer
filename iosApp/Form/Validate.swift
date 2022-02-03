//
//  ValidateRequered.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 03.02.2022.
//

import Foundation
import SwiftUI

func validateIsBlank(label: String, text: String) -> String? {
    return text.isEmpty ? "\(label) is required" : nil
}

func validateIsLong(label: String, text: String) -> String? {
    return text.count > 10 ? "\(label) too long" : nil
}

//
//  URL.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 05.02.2022.
//

import Foundation

extension Optional where Wrapped == URL {
    func toString() -> String {
        if let path = self?.absoluteString {
            return path
        } else {
            return ""
        }
    }
}

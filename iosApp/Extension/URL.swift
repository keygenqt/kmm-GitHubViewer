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

extension URL {
    func valueOf(_ queryParameterName: String) -> String? {
        guard let url = URLComponents(string: absoluteString) else { return nil }
        return url.queryItems?.first(where: { $0.name == queryParameterName })?.value
    }
}

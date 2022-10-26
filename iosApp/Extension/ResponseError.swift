//
//  NetworkError.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 17.10.2021.
//

import Foundation

enum ResponseError: Error, Equatable {
    case error(_ message: String? = nil)
}

extension ResponseError: LocalizedError {
    public var description: String {
        if case let .error(message) = self {
            return message ?? L10nErrors.errorSomethingWrong
        }
        return L10nErrors.errorSomethingWrong
    }
}

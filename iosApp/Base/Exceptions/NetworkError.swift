//
//  NetworkError.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 17.10.2021.
//

import Foundation

enum NetworkError: Error, Equatable {
    case notFound
    case unexpected(code: Int)
}

extension NetworkError: LocalizedError {
    public var description: String {
        switch self {
        case .notFound:
            return "The 404 error status code indicates that the REST API can't map the client's URI to a resource but may be available in the future."
        default:
            return "The server either does not recognize the request method, or it cannot fulfill the request."
        }
    }
}

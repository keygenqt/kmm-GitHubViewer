//
//  AuthModel.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 13.10.2022.
//

import Foundation

class AuthModel: Identifiable {
    var accessToken: String?
    var expiresIn: Int?
    var refreshToken: String?
    var refreshTokenExpiresIn: Int?

    enum CodingKeys: String, CaseIterable {
        case access_token
        case expires_in
        case refresh_token
        case refresh_token_expires_in
    }

    init() {}

    required init(_ data: String) {
        var values: [CodingKeys: String] = [:]

        CodingKeys.allCases.forEach { value in
            let raw = value.rawValue
            values[value] = String(data.split(separator: "&").first(where: { $0.contains(raw) })?.split(separator: "=")[1] ?? "")
        }

        accessToken = values[.access_token] ?? nil
        expiresIn = Int(values[.expires_in] ?? "0")
        refreshToken = values[.refresh_token] ?? nil
        refreshTokenExpiresIn = Int(values[.refresh_token_expires_in] ?? "0")
    }
}

extension AuthModel {
    func toString() -> String {
        return """
            access_token=\(accessToken ?? "")
            &expires_in=\(expiresIn ?? 0)
            &refresh_token=\(refreshToken ?? "")
            &refresh_token_expires_in=\(refreshTokenExpiresIn ?? 0)
        """.components(separatedBy: .whitespacesAndNewlines).joined()
    }
}

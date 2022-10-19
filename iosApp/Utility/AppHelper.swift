//
//  AppHelper.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 05.02.2022.
//

import Foundation

struct AppHelper {
    /// Generate DynamicLink
    static func getDynamicLink(_ path: String) -> String {
        return "\(ConstantsApp.DYNAMIC_LINK_URL)\(path)"
    }

    /// Generate auth link github API
    static func getOauthLink(_ login: String) -> URL {
        var components = URLComponents()
        components.scheme = "https"
        components.host = "github.com"
        components.path = "/login/oauth/authorize"
        components.queryItems = [
            URLQueryItem(name: "login", value: login),
            URLQueryItem(name: "state", value: NSUUID().uuidString),
            URLQueryItem(name: "redirect_uri", value: AppHelper.getDynamicLink("oauth")),
            URLQueryItem(name: "allow_signup", value: "false"),
            URLQueryItem(name: "client_id", value: EnvironmentSecret.clientId),
        ]
        return components.url!
    }

    // Bites to human string
    static func humanReadableByteCount(_ bytes: Int) -> String {
        if bytes < 1000 { return "\(bytes) B" }
        let exp = Int(log2(Double(bytes)) / log2(1000.0))
        let unit = ["KB", "MB", "GB", "TB", "PB", "EB"][exp - 1]
        let number = Double(bytes) / pow(1000, Double(exp))
        return String(format: "%.1f %@", number, unit)
    }
}

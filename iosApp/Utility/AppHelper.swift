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
            URLQueryItem(name: "client_id", value: "{secret}"),
        ]
        return components.url!
    }
}

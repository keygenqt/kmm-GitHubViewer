//
//  RouteUser.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 22.01.2022.
//

import SwiftUI

enum RouteUser {
    case tabs
}

class RouterUser: ObservableObject {
    @Published var route: RouteUser? = .tabs

    func isActive(for route: RouteUser) -> Binding<Bool> {
        return Binding(get: { self.route != route }, set: { self.route = $0 ? nil : route })
    }
}

//
//  RouteGuest.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 22.01.2022.
//

import SwiftUI

enum RouteGuest {
    case welcome
    case signIn
}

class RouterGuest: ObservableObject {
    @Published var route: RouteGuest? = .welcome

    func isActive(for route: RouteGuest) -> Binding<Bool> {
        return Binding(get: { self.route != route }, set: { self.route = $0 ? nil : route })
    }
}

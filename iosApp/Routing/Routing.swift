//
//  Routing.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 20.01.2022.
//

import SwiftUI

protocol Routing {
    associatedtype Route
    associatedtype View: SwiftUI.View

    @ViewBuilder func view(for route: Route) -> Self.View
}

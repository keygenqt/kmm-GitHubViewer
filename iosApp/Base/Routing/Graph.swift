//
//  Graph.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 22.01.2022.
//

import SwiftUI

enum Graph {
    case onboarding
    case guest
    case user
}

class GraphObservable: ObservableObject {
    @Published var route: Graph = AppKeyValue.isAuth() ? .user : (AppKeyValue.isPassOnboarding() ? .guest : .onboarding)
}

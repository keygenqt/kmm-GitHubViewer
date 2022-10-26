//
//  Graph.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 22.01.2022.
//

import SwiftUI

enum Graph: String {
    case onboarding
    case guest
    case user
}

class GraphObservable: ObservableObject {
    @Published var route: Graph = ConstantsKMM.STORAGE.authToken.isEmpty ? (ConstantsKMM.STORAGE.isOnboardingDone ? .guest : .onboarding) : .user
}

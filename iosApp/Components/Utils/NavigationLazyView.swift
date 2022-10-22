//
//  NavigationLazyView.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 20.10.2022.
//

import Foundation
import SwiftUI

struct NavigationLazyView<Content: View>: View {
    let build: () -> Content
    init(_ build: @autoclosure @escaping () -> Content) {
        self.build = build
    }

    var body: Content {
        build()
    }
}

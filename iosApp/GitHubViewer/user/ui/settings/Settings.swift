//
//  Settings.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 16.10.2022.
//

import SwiftUI

struct Settings: View {
    // model
    @ObservedObject var viewModel = SettingsViewModel()
    // graph
    @EnvironmentObject var graph: GraphObservable
    // router
    @EnvironmentObject var router: RouterUser

    var body: some View {
        Text("Settings")
    }
}

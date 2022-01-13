//
//  NavTab.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 16.10.2021.
//

import SwiftUI

struct NavGraphUser: View {
    @State var selectedView = 1
    @State private var showingAlert = false

    var body: some View {
        TabView {
            ListRepos()
                .tabItem {
                    Image(systemName: "list.bullet.circle")
                        .font(.largeTitle)
                }
                .tag(1)

            ListFollower()
                .tabItem {
                    Image(systemName: "person.circle")
                        .font(.largeTitle)
                }
                .tag(2)
        }.accentColor(.orange)
    }
}

struct NavGraphUser_Previews: PreviewProvider {
    static var previews: some View {
        NavGraphUser()
    }
}

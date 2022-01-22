//
//  UserTabs.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 22.01.2022.
//

import SwiftUI

struct UserTabs: View {
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

struct UserTabs_Previews: PreviewProvider {
    static var previews: some View {
        UserTabs()
    }
}

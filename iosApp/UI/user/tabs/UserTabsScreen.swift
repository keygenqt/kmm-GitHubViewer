//
//  UserTabs.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 22.01.2022.
//

import SwiftUI

struct UserTabsScreen: View {
    // graph
    @EnvironmentObject var graph: GraphObservable
    // router
    @EnvironmentObject var router: RouterUser
    // page values
    @State private var selection = 0

    var body: some View {
        TabView(selection: $selection) {
            NavigationView {
                ListReposScreen()
            }
            .tabItem {
                VStack {
                    Image(systemName: "list.bullet.circle")
                        .font(.largeTitle)
                    Text(L10nRepos.title)
                }
            }
            .tag(0)

            NavigationView {
                ListFollowerScreen()
            }
            .tabItem {
                VStack {
                    Image(systemName: "person.2.circle")
                        .font(.largeTitle)
                    Text(L10nFollowers.title)
                }
            }
            .tag(1)

            NavigationView {
                ProfileScreen()
                    .navigationBarTitle(L10nProfile.title)
                    .navigationBarTitleDisplayMode(.large)
            }
            .tabItem {
                VStack {
                    Image(systemName: "person.circle")
                        .font(.largeTitle)
                    Text(L10nProfile.title)
                }
            }
            .tag(2)
        }
        .accentColor(.orange)
    }
}

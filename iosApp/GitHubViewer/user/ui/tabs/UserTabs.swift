//
//  UserTabs.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 22.01.2022.
//

import SwiftUI

struct UserTabs: View {
    // graph
    @EnvironmentObject var graph: GraphObservable
    // router
    @EnvironmentObject var router: RouterUser
    // page values
    @State private var selection = 0

    var body: some View {
        TabView(selection: $selection) {
            NavigationView {
                ListRepos()
                    .navigationBarTitle(L10nRepos.title)
                    .navigationBarTitleDisplayMode(.large)
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
                ListFollower()
                    .navigationBarTitle(L10nFollowers.title)
                    .navigationBarTitleDisplayMode(.large)
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
                ListFollower()
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

struct UserTabs_Previews: PreviewProvider {
    static var previews: some View {
        UserTabs()
    }
}

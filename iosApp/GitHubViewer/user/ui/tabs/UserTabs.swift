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
                    .navigationBarTitle("Repos")
                    .navigationBarTitleDisplayMode(.large)
            }
            .tabItem {
                Image(systemName: "list.bullet.circle")
                    .font(.largeTitle)
            }
            .tag(0)

            NavigationView {
                ListFollower()
                    .navigationBarTitle("Followers")
                    .navigationBarTitleDisplayMode(.large)
            }
            .tabItem {
                Image(systemName: "person.circle")
                    .font(.largeTitle)
            }
            .tag(1)
        }
        .accentColor(.orange)
    }
}

struct UserTabs_Previews: PreviewProvider {
    static var previews: some View {
        UserTabs()
    }
}

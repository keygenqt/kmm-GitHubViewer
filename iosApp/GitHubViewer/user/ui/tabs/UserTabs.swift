//
//  UserTabs.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 22.01.2022.
//

import SwiftUI

struct UserTabs: View {
    @State private var selection = 0

    var body: some View {
        TabView(selection: $selection) {
            NavigationView {
                ListRepos()
                    .navigationBarTitle(Text("Repos"))
                    .navigationBarTitleDisplayMode(.large)
            }
            .tabItem {
                Image(systemName: "list.bullet.circle")
                    .font(.largeTitle)
            }
            .tag(0)

            NavigationView {
                ListFollower()
                    .navigationBarTitle(Text("Followers"))
                    .navigationBarTitleDisplayMode(.large)
            }
            .tabItem {
                Image(systemName: "person.circle")
                    .font(.largeTitle)
            }
            .tag(1)
        }
        .accentColor(.orange)
        .navigationBarHidden(true)
    }
}

struct UserTabs_Previews: PreviewProvider {
    static var previews: some View {
        UserTabs()
    }
}

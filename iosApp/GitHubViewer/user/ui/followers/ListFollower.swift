//
//  ListFavorite.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 16.10.2021.
//

import SwiftUI

struct ListFollower: View {
    // model
    @ObservedObject var viewModel = FollowerViewModel()
    // graph
    @EnvironmentObject var graph: GraphObservable
    // router
    @EnvironmentObject var router: RouterUser
    // page values
    @Environment(\.openURL) var openURL

    var body: some View {
        AppList(viewModel: viewModel) { model in
            Link(destination: URL(string: model.url!)!) {
                NavigationLink(destination: EmptyView()) {
                    ListFollowerItem(model: model)
                }
            }.accentColor(Color.onBackground)
        }
    }
}

struct ListFollower_Previews: PreviewProvider {
    static var previews: some View {
        ListFollower()
    }
}

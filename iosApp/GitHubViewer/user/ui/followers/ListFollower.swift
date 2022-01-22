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
        if viewModel.isShowProgressView {
            VStack {
                ProgressView()
                    .progressViewStyle(CircularProgressViewStyle(tint: .orange))
            }
        } else {
            List(viewModel.models) { model in
                NavigationLink(destination: Button("Visit to GitHub \(model.login!)") {
                    // openURL(URL(string: model.url!)!)
                    graph.route = .guest
                }) {
                    ListFollowerItem(model: model)
                }
            }
            .overlay(alignment: .bottom) {
                if viewModel.error != nil {
                    ErrorView(error: viewModel.error)
                }
            }
            .refreshable {
                await viewModel.refresh()
            }
        }
    }
}

struct ListFollower_Previews: PreviewProvider {
    static var previews: some View {
        ListFollower()
    }
}

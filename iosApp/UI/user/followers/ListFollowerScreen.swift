//
//  ListFavorite.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 16.10.2021.
//

import SwiftUI

struct ListFollowerScreen: View {
    // model
    @ObservedObject var viewModel = FollowerViewModel()
    // graph
    @EnvironmentObject var graph: GraphObservable
    // router
    @EnvironmentObject var router: RouterUser
    // page values
    @Environment(\.openURL) var openURL
    // states
    @State var refreshable: Bool = false

    var body: some View {
       AppList(viewModel: viewModel, refreshable: $refreshable) { model in
           Link(destination: URL(string: model.url)!) {
               NavigationLink(destination: EmptyView()) {
                   ListFollowerItem(model: model)
               }
           }.accentColor(Color.onBackground)
       }
       .navigationBarTitle(L10nFollowers.title)
       .navigationBarTitleDisplayMode(.large)
       .navigationBarItems(trailing: HStack {
           if viewModel.loading && viewModel.page == 1 && !viewModel.models.isEmpty && !refreshable {
               ProgressView()
                   .progressViewStyle(CircularProgressViewStyle(tint: .orange))
           }
       })
    }
}

//
//  ListRepos.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 16.10.2021.
//

import SwiftUI
import shared

struct ListReposScreen: View {
    // model
    @ObservedObject var viewModel = ReposViewModel()
    // graph
    @EnvironmentObject var graph: GraphObservable
    // router
    @EnvironmentObject var router: RouterUser
    // states
    @State var refreshable: Bool = false

    var body: some View {
        AppList(viewModel: viewModel, refreshable: $refreshable) { model in
            NavigationLink(destination: NavigationLazyView(ViewRepoScreen(url: model.url))) {
                ListReposItem(model: model)
            }
        }
        .navigationBarTitle(L10nRepos.title)
        .navigationBarTitleDisplayMode(.large)
        .navigationBarItems(trailing: HStack {
            if viewModel.loading && viewModel.page == 1 && !viewModel.models.isEmpty && !refreshable {
                ProgressView()
                    .progressViewStyle(CircularProgressViewStyle(tint: .orange))
            } else {
                Button {
                    viewModel.toggleOrder()
                } label: {
                    if viewModel.orderASC {
                        Image(systemName: "arrow.up.arrow.down.circle.fill")
                            .imageScale(.large)
                    } else {
                        Image(systemName: "arrow.up.arrow.down.circle")
                            .imageScale(.large)
                    }
                }.disabled(viewModel.loading)
            }
        })
    }
}

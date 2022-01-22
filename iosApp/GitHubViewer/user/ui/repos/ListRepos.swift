//
//  ListRepos.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 16.10.2021.
//

import Alamofire
import SwiftUI

struct ListRepos: View {
    // model
    @ObservedObject var viewModel = ReposViewModel()
    // graph
    @EnvironmentObject var router: RouterUser

    var body: some View {
        if viewModel.isShowProgressView {
            VStack {
                ProgressView()
                    .progressViewStyle(CircularProgressViewStyle(tint: .orange))
            }
            .navigationTitle("Repos")
        } else {
            List(viewModel.models) { model in
                NavigationLink(destination: ViewRepo(model: model)) {
                    ListReposItem(model: model)
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
            .navigationTitle("Repos")
        }
    }
}

struct ListRepos_Previews: PreviewProvider {
    static var previews: some View {
        ListRepos()
    }
}

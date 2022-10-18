//
//  ListRepos.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 16.10.2021.
//

import SwiftUI

struct ListRepos: View {
    // model
    @ObservedObject var viewModel = ReposViewModel()
    // graph
    @EnvironmentObject var graph: GraphObservable
    // router
    @EnvironmentObject var router: RouterUser

    var body: some View {
        AppList(viewModel: viewModel) { model in
            NavigationLink(destination: ViewRepo(url: model.url!)) {
                ListReposItem(model: model)
            }
        }.navigationBarItems(trailing: HStack {
            Button {
                print("order")
            } label: {
                Image(systemName: "arrow.up.arrow.down.circle")
                    .imageScale(.large)
            }
        })
    }
}

struct ListRepos_Previews: PreviewProvider {
    static var previews: some View {
        ListRepos()
    }
}

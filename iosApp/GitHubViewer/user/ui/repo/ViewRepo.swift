//
//  ContentView.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 05.10.2021.
//

import Kingfisher
import SwiftUI

struct ViewRepo: View {
    let url: String
    // model
    @ObservedObject var viewModel = RepoViewModel()
    // graph
    @EnvironmentObject var graph: GraphObservable
    // router
    @EnvironmentObject var router: RouterUser

    init(url: String) {
        self.url = url
    }

    var body: some View {
        VStack {
            if viewModel.error != nil {
                VStack {
                    ErrorView(error: viewModel.error) {
                        viewModel.retry(url)
                    }
                }
            } else if viewModel.model == nil {
                VStack {
                    ProgressView()
                        .progressViewStyle(CircularProgressViewStyle(tint: .orange))
                }
            } else {
                if let model = viewModel.model {
                    Text(model.description ?? "")
                }
            }
        }.navigationBarItems(trailing: HStack {
            if let model = viewModel.model {
                NavigationLink(destination: SettingsRepo(model)) {
                    Image(systemName: "gearshape")
                        .imageScale(.medium)
                }
            }
        })
        .onAppear {
            viewModel.readDb(url)
        }
        .task {
            await viewModel.load(url)
        }
    }
}

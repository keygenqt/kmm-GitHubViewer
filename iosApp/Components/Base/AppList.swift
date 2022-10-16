//
//  AppList.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 16.10.2022.
//

import SwiftUI

struct AppList<T: Identifiable, Content: View>: View {
    @ObservedObject var viewModel: ViewModelList<T>
    var content: (T) -> Content

    var body: some View {
        if viewModel.models.isEmpty && viewModel.error == nil {
            VStack {
                ProgressView()
                    .progressViewStyle(CircularProgressViewStyle(tint: .orange))
            }
        } else {
            ScrollViewReader { sc in
                List {
                    Section(footer: HStack {
                        if viewModel.error != nil || viewModel.isEnd {
                            EmptyView()
                        } else {
                            HStack {
                                Spacer()
                                LottieView(
                                    name: "block_loader"
                                ).frame(width: 40, height: 40)
                                Spacer()
                            }.onAppear {
                                if !viewModel.isLoading {
                                    Task { await viewModel.load() }
                                }
                            }
                        }

                        ErrorListItemView(error: viewModel.error) {
                            if viewModel.models.isEmpty {
                                viewModel.clearError()
                                Task { await viewModel.reload() }
                            } else {
                                Task { await viewModel.load() }
                            }
                        }
                        .listRowBackground(Color.red)

                    }.listRowInsets(EdgeInsets(top: 15, leading: 5, bottom: 25, trailing: 5))) {
                        ForEach(viewModel.models) { model in
                            content(model).id(model.id)
                        }
                    }
                }
                .refreshable {
                    await viewModel.reload()
                }
                .onChange(of: viewModel.error) { error in
                    if error != nil {
                        if let lastElement = viewModel.models.last {
                            withAnimation {
                                sc.scrollTo(lastElement.id, anchor: .top)
                            }
                        }
                    }
                }

            }.listStyle(.insetGrouped)
        }
    }
}

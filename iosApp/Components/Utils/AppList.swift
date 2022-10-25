//
//  AppList.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 16.10.2022.
//

import SwiftUI
import shared

struct AppList<T: IModel, Content: View>: View {
    @ObservedObject var viewModel: ViewModelList<T>

    var content: (T) -> Content

    @Binding var toUp: Bool
    @Binding var refreshable: Bool

    init(
        viewModel: ViewModelList<T>,
        toUp: Binding<Bool> = .constant(true),
        refreshable: Binding<Bool> = .constant(true),
        @ViewBuilder content: @escaping (T) -> Content
    ) {
        self.viewModel = viewModel
        self.content = content
        _toUp = toUp
        _refreshable = refreshable
    }

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
                        if viewModel.error == nil {
                            HStack {
                                Spacer()
                                if viewModel.error != nil || viewModel.isEnd {
                                    LottieView(
                                        name: "end_flow_bw"
                                    ).frame(width: 70, height: 45)
                                } else {
                                    LottieView(
                                        name: "block_loader"
                                    ).frame(width: 45, height: 45)
                                }
                                Spacer()
                            }.onAppear {
                                if !viewModel.isLoadingPage && !viewModel.isEnd {
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
                        ForEach((0...viewModel.models.count-1), id: \.self) {
                            content(viewModel.models[$0]).id(viewModel.models[$0].id)
                        }
                    }
                }
                .refreshable {
                    refreshable = true
                    await viewModel.reload()
                    refreshable = false
                }
                .onChange(of: viewModel.error) { error in
                    if error != nil, viewModel.page != 1 {
                        if let lastElement = viewModel.models.last {
                            withAnimation {
                                sc.scrollTo(lastElement.id, anchor: .top)
                            }
                        }
                    }
                }
                .onChange(of: toUp, perform: { toUp in
                    if toUp {
                        if let firstElement = viewModel.models.first {
                            withAnimation {
                                sc.scrollTo(firstElement.id, anchor: .top)
                            }
                        }
                        self.toUp = false
                    }
                })

            }.listStyle(.insetGrouped)
        }
    }
}

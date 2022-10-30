//
//  ContentView.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 05.10.2021.
//

import Kingfisher
import SwiftUI
import shared

struct ViewRepoScreen: View {
    let url: String
    // model
    @ObservedObject var viewModel = RepoViewModel()
    // graph
    @EnvironmentObject var graph: GraphObservable
    // router
    @EnvironmentObject var router: RouterUser

    @State var refreshable: Bool = false

    init(url: String) {
        self.url = url
        viewModel.readDb(url)
        viewModel.load(url)
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
                    ScrollView {
                        // image block
                        VStack {
                            ZStack {
                                Image("chart_1").resizable().aspectRatio(contentMode: .fill)
                                VStack {
                                    HStack {
                                        HStack {
                                            Text(String(model.stargazersCount)).font(.system(size: 56, weight: .heavy))
                                            VStack(alignment: .leading) {
                                                Text(L10nRepo.iconStarTitle).fontWeight(.bold).padding(.bottom, 0.1)
                                                Text(L10nRepo.iconStarText).font(.caption)
                                            }.padding(.leading, 5)
                                        }
                                        Spacer()
                                    }
                                    Spacer()
                                }.padding(EdgeInsets(top: 10, leading: 15, bottom: 10, trailing: 15))

                            }.cornerRadius(50, corners: [.bottomLeft])

                            ZStack {
                                Image("chart_2").resizable().aspectRatio(contentMode: .fill)
                                VStack {
                                    HStack {
                                        HStack {
                                            Text(String(model.forks)).font(.system(size: 56, weight: .heavy))
                                            VStack(alignment: .leading) {
                                                Text(L10nRepo.iconForksTitle).fontWeight(.bold).padding(.bottom, 0.1)
                                                Text(L10nRepo.iconForksText).font(.caption)
                                            }.padding(.leading, 5)
                                        }
                                        Spacer()
                                    }
                                    Spacer()
                                }.padding(EdgeInsets(top: 10, leading: 15, bottom: 10, trailing: 15))

                            }.cornerRadius(50, corners: [.bottomLeft])
                        }

                        // item icons block
                        HStack {
                            VStack {
                                Text(L10nRepo.openIssue.uppercased()).font(.caption)

                                VStack(alignment: .leading) {
                                    Image(systemName: "ladybug.fill")
                                        .frame(width: 30, height: 30)
                                        .foregroundColor(Color.onBackground)
                                        .padding(.leading, 25)
                                        .padding(.trailing, 25)
                                        .padding(.top, 7)
                                        .padding(.bottom, 7)
                                }
                                .background(Color.surfaceVariant)
                                .clipShape(Capsule())

                                Text(String(model.openIssuesCount))
                            }

                            Spacer()

                            VStack {
                                Text(L10nRepo.openWatchers.uppercased()).font(.caption)

                                VStack(alignment: .leading) {
                                    Image(systemName: "eye.fill")
                                        .frame(width: 30, height: 30)
                                        .foregroundColor(Color.onBackground)
                                        .padding(.leading, 25)
                                        .padding(.trailing, 25)
                                        .padding(.top, 7)
                                        .padding(.bottom, 7)
                                        .accentColor(Color.onBackground)
                                }
                                .background(Color.surfaceVariant)
                                .clipShape(Capsule())

                                Text(String(model.watchersCount))
                            }

                            Spacer()

                            VStack {
                                Text(L10nRepo.labelSize.uppercased()).font(.caption)

                                VStack(alignment: .leading) {
                                    Image(systemName: "externaldrive.fill")
                                        .frame(width: 30, height: 30)
                                        .foregroundColor(Color.onBackground)
                                        .padding(.leading, 25)
                                        .padding(.trailing, 25)
                                        .padding(.top, 7)
                                        .padding(.bottom, 7)
                                }
                                .background(Color.surfaceVariant)
                                .clipShape(Capsule())

                                Text(String(ConstantsKMM.HELPER.humanReadableByte(bytes: Double(model.size * 1024))))
                            }
                        }
                        .padding(EdgeInsets(top: 20, leading: 15, bottom: 15, trailing: 15))

                        // list info
                        VStack {
                            if let value = viewModel.model?.fullName {
                                if !value.isEmpty {
                                    HStack {
                                        VStack(alignment: .leading) {
                                            Text(L10nRepo.labelFullName).font(.caption).fontWeight(.bold).padding(.bottom, 1)
                                            Text(value).font(.body)
                                        }
                                        Spacer()
                                    }
                                    .padding(EdgeInsets(top: 30, leading: 15, bottom: 5, trailing: 15))
                                }
                            }

                            if let value = viewModel.model?.license?.name {
                                if !value.isEmpty {
                                    HStack {
                                        VStack(alignment: .leading) {
                                            Text(L10nRepo.labelLicense).font(.caption).fontWeight(.bold).padding(.bottom, 1)
                                            Text(value).font(.body)
                                        }
                                        Spacer()
                                    }
                                    .padding(EdgeInsets(top: 10, leading: 15, bottom: 5, trailing: 15))
                                }
                            }

                            if let value = viewModel.model?.visibility {
                                if !value.isEmpty {
                                    HStack {
                                        VStack(alignment: .leading) {
                                            Text(L10nRepo.labelVisibility).font(.caption).fontWeight(.bold).padding(.bottom, 1)
                                            Text(value.uppercased()).font(.body)
                                        }
                                        Spacer()
                                    }
                                    .padding(EdgeInsets(top: 10, leading: 15, bottom: 5, trailing: 15))
                                }
                            }

                            if let value = viewModel.model?.owner.login {
                                if !value.isEmpty {
                                    HStack {
                                        VStack(alignment: .leading) {
                                            Text(L10nRepo.labelOwner).font(.caption).fontWeight(.bold).padding(.bottom, 1)
                                            Text(value).font(.body)
                                        }
                                        Spacer()
                                    }
                                    .padding(EdgeInsets(top: 10, leading: 15, bottom: 5, trailing: 15))
                                }
                            }

                            if let value = viewModel.model?.updatedAt {
                                HStack {
                                    VStack(alignment: .leading) {
                                        Text(L10nRepo.labelUpdatedAt).font(.caption).fontWeight(.bold).padding(.bottom, 1)
                                        Text(value.toFormatDateShort())
                                    }
                                    Spacer()
                                }
                                .padding(EdgeInsets(top: 10, leading: 15, bottom: 5, trailing: 15))
                            }

                            if let value = viewModel.model?.createdAt {
                                HStack {
                                    VStack(alignment: .leading) {
                                        Text(L10nRepo.labelCreatedAt).font(.caption).fontWeight(.bold).padding(.bottom, 1)
                                        Text(value.toFormatDateShort())
                                    }
                                    Spacer()
                                }
                                .padding(EdgeInsets(top: 10, leading: 15, bottom: 5, trailing: 15))
                            }

                            if let value = viewModel.model?.desc {
                                if !value.isEmpty {
                                    HStack {
                                        VStack(alignment: .leading) {
                                            Text(L10nRepo.labelDescription).font(.caption).fontWeight(.bold).padding(.bottom, 1)
                                            Text(value).font(.body)
                                        }
                                        Spacer()
                                    }
                                    .padding(EdgeInsets(top: 10, leading: 15, bottom: 30, trailing: 15))
                                }
                            }
                        }
                        .background(Color.surfaceVariant)
                    }.refreshable {
                            refreshable = true
                            await viewModel.loadAsync(url)
                            refreshable = false
                    }
                }
            }
        }
        .navigationBarItems(trailing: HStack {
            if viewModel.loading && !refreshable && viewModel.model != nil {
                ProgressView()
                    .progressViewStyle(CircularProgressViewStyle(tint: .orange))
            } else if let model = viewModel.model {
                NavigationLink(destination: SettingsRepoScreen(model)) {
                    Image(systemName: "gearshape")
                        .imageScale(.medium)
                }
            }
        })
        .navigationBarTitle(L10nRepo.title, displayMode: .inline)
        .onAppear {
            viewModel.readDb(url)
        }
        .task {
            if !viewModel.loading {
                await viewModel.loadAsync(url)
            }
        }
    }
}

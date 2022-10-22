//
//  Profile.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 16.10.2022.
//

import Kingfisher
import SwiftUI

struct ProfileScreen: View {
    // model
    @ObservedObject var viewModel = ProfileViewModel()
    // graph
    @EnvironmentObject var graph: GraphObservable
    // router
    @EnvironmentObject var router: RouterUser
    // theme
    @Environment(\.colorScheme) var colorScheme

    @State private var isShowLogout: Bool = false

    var body: some View {
        VStack {
            if viewModel.error != nil {
                VStack {
                    ErrorView(error: viewModel.error) {
                        viewModel.retry()
                    }
                }
            } else if viewModel.model == nil {
                VStack {
                    ProgressView()
                        .progressViewStyle(CircularProgressViewStyle(tint: .orange))
                }
            } else {
                ScrollView {
                    VStack {
                        // image block
                        ZStack {
                            KFImage(URL(string: viewModel.model?.avatarUrl ?? "")!)
                                .placeholder {
                                    ProgressView()
                                }
                                .resizable()
                                .fade(duration: 0.25)
                                .forceRefresh()
                                .aspectRatio(1, contentMode: .fill)
                                .frame(height: 300)
                                .clipped()

                            VStack {
                                Spacer()
                                Rectangle()
                                    .fill(LinearGradient(gradient: Gradient(colors: [.clear, Color.background]), startPoint: .top, endPoint: .bottom))
                                    .frame(height: 100)
                            }

                            VStack(alignment: .leading) {
                                Spacer()
                                HStack {
                                    Text(viewModel.model?.name ?? "")
                                        .font(.title)
                                        .padding(.bottom, 10)
                                    Spacer()
                                }

                            }.padding()
                        }

                        // item icons block
                        HStack {
                            VStack {
                                Text(L10nProfile.labelItemCountPublicRepos.uppercased()).font(.caption)

                                VStack(alignment: .leading) {
                                    Image(systemName: "briefcase.fill")
                                        .frame(width: 30, height: 30)
                                        .foregroundColor(Color.onBackground)
                                        .padding(.leading, 25)
                                        .padding(.trailing, 25)
                                        .padding(.top, 7)
                                        .padding(.bottom, 7)
                                }
                                .background(Color.surfaceVariant)
                                .clipShape(Capsule())

                                Text(String(viewModel.model?.publicRepos ?? 0))
                            }

                            Spacer()

                            VStack {
                                Text(L10nProfile.labelItemCountFollowers.uppercased()).font(.caption)

                                VStack(alignment: .leading) {
                                    Image(systemName: "person.2.fill")
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

                                Text(String(viewModel.model?.followers ?? 0))
                            }

                            Spacer()

                            VStack {
                                Text(L10nProfile.labelItemCountFollowing.uppercased()).font(.caption)

                                VStack(alignment: .leading) {
                                    Image(systemName: "person.fill.viewfinder")
                                        .frame(width: 30, height: 30)
                                        .foregroundColor(Color.onBackground)
                                        .padding(.leading, 25)
                                        .padding(.trailing, 25)
                                        .padding(.top, 7)
                                        .padding(.bottom, 7)
                                }
                                .background(Color.surfaceVariant)
                                .clipShape(Capsule())

                                Text(String(viewModel.model?.following ?? 0))
                            }
                        }
                        .background(Color.background)
                        .padding(EdgeInsets(top: 20, leading: 15, bottom: 15, trailing: 15))

                        // list info
                        VStack {
                            HStack {
                                VStack(alignment: .leading) {
                                    Text(L10nProfile.labelBlog).font(.caption).fontWeight(.bold).padding(.bottom, 1)
                                    Link(destination: URL(string: viewModel.model?.blog ?? "")!) {
                                        Text(viewModel.model?.blog ?? "").font(.body)
                                    }.accentColor(colorScheme == .dark ? .outline : .primary)
                                }
                                Spacer()
                            }
                            .padding(EdgeInsets(top: 30, leading: 15, bottom: 5, trailing: 15))

                            HStack {
                                VStack(alignment: .leading) {
                                    Text(L10nProfile.labelLocation).font(.caption).fontWeight(.bold).padding(.bottom, 1)
                                    Text(viewModel.model?.location ?? "").font(.body)
                                }
                                Spacer()
                            }
                            .padding(EdgeInsets(top: 10, leading: 15, bottom: 5, trailing: 15))

                            HStack {
                                VStack(alignment: .leading) {
                                    Text(L10nProfile.labelCreatedAt).font(.caption).fontWeight(.bold).padding(.bottom, 1)
                                    Text(viewModel.model?.createdAt?.toDateFromat() ?? "").font(.body)
                                }
                                Spacer()
                            }
                            .padding(EdgeInsets(top: 10, leading: 15, bottom: 5, trailing: 15))

                            HStack {
                                VStack(alignment: .leading) {
                                    Text(L10nProfile.labelBio).font(.caption).fontWeight(.bold).padding(.bottom, 1)
                                    Text(viewModel.model?.bio ?? "").font(.body)
                                }
                                Spacer()
                            }
                            .padding(EdgeInsets(top: 10, leading: 15, bottom: 30, trailing: 15))
                        }
                        .background(Color.surfaceVariant)
                    }
                }.refreshable {
                    await viewModel.load()
                }
            }
        }.navigationBarItems(trailing: HStack {
            Button {
                isShowLogout = true
            } label: {
                Image(systemName: "rectangle.portrait.and.arrow.right")
                    .imageScale(.medium)
            }
            if let model = viewModel.model {
                NavigationLink(destination: SettingsScreen(model)) {
                    Image(systemName: "gearshape")
                        .imageScale(.medium)
                }
            }
        }).confirmationDialog(L10nProfile.dialogLogoutTitle, isPresented: $isShowLogout) {
            Button(L10nProfile.dialogLogoutConfirm, role: .destructive) {
                graph.route = .guest
                AppKeyValue.clear()
            }
        } message: {
            Text(L10nProfile.dialogLogoutText)
        }
        .onAppear {
            viewModel.readDb()
        }
    }
}

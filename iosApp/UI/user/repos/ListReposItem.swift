//
//  ListReposItem.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 16.10.2021.
//

import Kingfisher
import SwiftUI
import shared

struct ListReposItem: View {
    var model: RepoModel

    var body: some View {
        HStack {
            VStack(alignment: .leading) {
                VStack(alignment: .leading) {
                    Image(systemName: (model.language ?? "").getIconLanguage())
                        .frame(width: 30, height: 30)
                        .foregroundColor((model.language ?? "").getColorLanguage())
                }
                .background(Color(white: 0.95))
                .clipShape(Circle())
                .padding(8)

                Spacer()
            }

            VStack(alignment: .leading) {
                HStack {
                    if model.isPrivate {
                        Image(systemName: "lock.fill")
                            .font(.caption2)
                    } else {
                        Image(systemName: "lock")
                            .font(.caption2)
                    }

                    Text(model.name)
                        .lineLimit(1)
                }
                .padding(.top, 5)
                .padding(.bottom, (model.desc ?? "").isEmpty ? 8 : 4)

                if !(model.desc ?? "").isEmpty {
                    Text(model.desc!)
                        .lineLimit(3)
                        .font(.caption)
                        .padding(.bottom, 8)
                }

                HStack {
                    HStack {
                        Image(systemName: "star")
                            .font(.caption2)
                        Text(String(model.stargazersCount))
                            .font(.caption2)
                    }
                    HStack {
                        Image(systemName: "arrow.triangle.branch")
                            .font(.caption2)
                        Text(String(model.forks))
                            .font(.caption2)
                    }
                    HStack {
                        Image(systemName: "eye")
                            .font(.caption2)
                        Text(String(model.watchers))
                            .font(.caption2)
                    }

                    Spacer()
                }
            }

            Spacer()
        }
    }
}

//struct ListReposItem_Previews: PreviewProvider {
//    static var previews: some View {
//        Group {
//            ListReposItem(model: RepoModel.mock)
//            ListReposItem(model: RepoModel.mock)
//        }.previewLayout(.fixed(width: 300, height: 70))
//    }
//}

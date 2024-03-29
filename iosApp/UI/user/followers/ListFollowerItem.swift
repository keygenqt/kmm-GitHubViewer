//
//  ListFavoriteItem.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 16.10.2021.
//

import Kingfisher
import SwiftUI
import shared

struct ListFollowerItem: View {
    var model: FollowerModel

    var body: some View {
        HStack {
            KFImage(URL(string: model.avatarUrl)!)
                .placeholder {
                    VStack(alignment: .leading) {
                        Image(systemName: "person.fill")
                            .frame(width: 30, height: 30)
                    }
                    .background(Color(white: 0.90))
                    .clipShape(Circle())
                }
                .resizable()
                .aspectRatio(contentMode: .fit)
                .clipShape(Circle())
                .frame(width: 30, height: 30)
                .padding(8)

            Text(model.login)

            Spacer()
        }
    }
}

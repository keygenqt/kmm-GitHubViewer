//
//  ListReposItem.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 16.10.2021.
//

import Kingfisher
import SwiftUI

struct ListReposItem: View {
    var model: RepoModel

    var body: some View {
        HStack {
            VStack(alignment: .leading) {
                Image(systemName: ConstantsLanguage.getIcon(language: model.language))
                    .frame(width: 30, height: 30)
                    .foregroundColor(.orange)
            }
            .background(Color(white: 0.90))
            .clipShape(Circle())
            .padding(8)

            Text(model.name!)
                .lineLimit(1)

            Spacer()
        }
    }
}

struct ListReposItem_Previews: PreviewProvider {
    static var previews: some View {
        Group {
            ListReposItem(model: RepoModel.mock)
            ListReposItem(model: RepoModel.mock)
        }.previewLayout(.fixed(width: 300, height: 70))
    }
}

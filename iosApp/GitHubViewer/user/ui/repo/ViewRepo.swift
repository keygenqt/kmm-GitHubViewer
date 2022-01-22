//
//  ContentView.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 05.10.2021.
//

import Kingfisher
import SwiftUI

struct ViewRepo: View {
    var model: RepoModel
    @State var heightKFImage = 30.0

    var titleName: AttributedString {
        // attributedString.foregroundColor = .textTitle
        // attributedString.font = .navigationBarTitle
        return AttributedString("Name: \(model.name ?? "nil")")
    }

    var body: some View {
        ScrollView {
            VStack {
                KFImage(URL(string: ConstantsApp.RANDOM_IMAGE)!)
                    .placeholder {
                        ProgressView()
                    }
                    .resizable()
                    .fade(duration: 0.25)
                    .forceRefresh()
                    .onSuccess { _ in
                        heightKFImage = .nan
                    }
                    .aspectRatio(contentMode: .fit)
                    .frame(height: heightKFImage)

                VStack(alignment: .leading) {
                    Text(titleName)

                    Divider().frame(height: 10)

                    HStack {
                        if model.isPrivate ?? false {
                            // Text(L10nApp.typePrivate)
                        } else {
                            // Text(L10nApp.typePublic)
                        }
                        Spacer()
                        // Text(L10nApp.typeRepo)
                    }
                    .font(.subheadline)
                    .foregroundColor(.secondary)

                    Divider()

                    HStack {
                        Text(model.createdAt!)
                        Spacer()
                        // Text(L10nApp.createdAt)
                    }
                    .font(.subheadline)
                    .foregroundColor(.secondary)

                    Divider()

                    if model.description != nil {
                        // Text(L10nApp.titleBio).font(.title2)
                        Text(model.description!).padding(.top, 0.5)
                    }
                }
                .padding()

                Spacer()
            }
            .padding(.top)
            .padding(.bottom)
            .navigationBarTitle("", displayMode: .inline)
        }
    }
}

struct ViewRepo_Previews: PreviewProvider {
    static var previews: some View {
        ViewRepo(model: RepoModel.mock)
    }
}

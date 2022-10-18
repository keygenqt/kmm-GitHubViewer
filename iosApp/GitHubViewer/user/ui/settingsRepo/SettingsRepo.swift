//
//  SettingsRepo.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 18.10.2022.
//

import SwiftUI

struct SettingsRepo: View {
    var url: String
    // model
    @ObservedObject var viewModel = SettingsRepoViewModel()
    // graph
    @EnvironmentObject var graph: GraphObservable
    // router
    @EnvironmentObject var router: RouterUser
    // form states
    @State private var error: String?
    // form value
    @State private var fieldsBase: [IField] = []
    @State private var fieldsOther: [IField] = []

    init(_ model: RepoModel) {
        url = model.url ?? ""
        _fieldsBase = State(initialValue: [
            NameRepoField(value: model.name ?? ""),
            DescRepoField(value: model.description ?? ""),
        ])
        _fieldsOther = State(initialValue: [
            IsPrivateRepoField(value: (model.isPrivate ?? false) ? "true" : "false"),
        ])
    }

    var body: some View {
        VStack {
            if viewModel.error != nil {
                VStack {
                    ErrorView(error: viewModel.error) {
                        viewModel.retry()
                    }
                }
            } else {
                AppForm(error: $error) {
                    Section(header: Text(L10nRepoUpdate.formGroupBase)) {
                        ForEach(0 ... fieldsBase.count - 1, id: \.self) {
                            AppTextField(field: $fieldsBase[$0], initValidate: true) { error in
                                self.error = error
                            }
                        }
                    }
                    Section(header: Text(L10nRepoUpdate.formGroupVisibility)) {
                        ForEach(0 ... fieldsOther.count - 1, id: \.self) {
                            AppTextField(field: $fieldsOther[$0], initValidate: true) { error in
                                self.error = error
                            }
                        }
                    }

                    Section {
                        Button(L10nRepoUpdate.formButtonSave) {
                            viewModel.update(
                                url: url,
                                name: fieldsBase[0].value,
                                desc: fieldsBase[1].value,
                                isPrivate: fieldsOther[0].value == "true"
                            )
                        }
                        .buttonStyle(BottomPrimaryStyle())
                        .disabled(fieldsBase.isNotValid() || fieldsOther.isNotValid() || viewModel.loading)
                        .listRowInsets(.init())
                        .listRowBackground(Color.clear)
                    }
                    .listRowBackground(Color.clear)
                }
            }
        }
        .navigationBarTitle(L10nRepoUpdate.title)
        .navigationBarTitleDisplayMode(.large)
        .navigationBarItems(trailing: HStack {
            if viewModel.loading {
                ProgressView()
                    .progressViewStyle(CircularProgressViewStyle(tint: .orange))
            }
        })
    }
}

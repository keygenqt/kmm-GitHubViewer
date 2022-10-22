//
//  SettingsRepo.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 18.10.2022.
//

import SwiftUI

struct SettingsRepoScreen: View {
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
    @State private var fieldNameRepo: IFieldText
    @State private var fieldDescRepo: IFieldText
    @State private var fieldIsPrivate: IFieldSwitch

    init(_ model: RepoModel) {
        url = model.url ?? ""
        _fieldNameRepo = State(
            initialValue: NameRepoField(value: model.name ?? "")
        )
        _fieldDescRepo = State(
            initialValue: DescRepoField(value: model.description ?? "")
        )
        _fieldIsPrivate = State(
            initialValue: IsPrivateRepoField(value: model.isPrivate ?? false)
        )
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
                        AppFieldText(field: $fieldNameRepo, initValidate: true) { error in
                            self.error = error
                        }
                        AppFieldText(field: $fieldDescRepo, initValidate: true) { error in
                            self.error = error
                        }
                    }

                    Section(header: Text(L10nRepoUpdate.formGroupVisibility)) {
                        AppFieldSwitch(field: $fieldIsPrivate)
                    }

                    Section {
                        Button(L10nRepoUpdate.formButtonSave) {
                            viewModel.update(
                                url: url,
                                name: fieldNameRepo.value,
                                desc: fieldDescRepo.value,
                                isPrivate: fieldIsPrivate.value
                            )
                        }
                        .buttonStyle(BottomPrimaryStyle())
                        .disabled(!fieldNameRepo.isValid || !fieldDescRepo.isValid || viewModel.loading)
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

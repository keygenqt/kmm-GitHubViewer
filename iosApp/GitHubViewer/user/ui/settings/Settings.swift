//
//  Settings.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 16.10.2022.
//

import SwiftUI

struct Settings: View {
    // model
    @ObservedObject var viewModel = SettingsViewModel()
    // graph
    @EnvironmentObject var graph: GraphObservable
    // router
    @EnvironmentObject var router: RouterUser
    // form states
    @State private var error: String?
    // form value
    @State private var fieldsAbout: [IField] = []
    @State private var fieldsLinks: [IField] = []
    @State private var fieldsBio: [IField] = []

    init(_ model: UserModel) {
        _fieldsAbout = State(initialValue: [
            NameField(value: model.name ?? ""),
            CompanyField(value: model.company ?? ""),
            LocationField(value: model.location ?? ""),
        ])
        _fieldsLinks = State(initialValue: [
            BlogField(value: model.blog ?? ""),
            TwitterField(value: model.twitterUsername ?? ""),
        ])
        _fieldsBio = State(initialValue: [
            BioField(value: model.bio ?? ""),
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
                    Section(header: Text(L10nSettings.formGroupAbout)) {
                        ForEach(0 ... fieldsAbout.count - 1, id: \.self) {
                            AppTextField(field: $fieldsAbout[$0], initValidate: true) { error in
                                self.error = error
                            }
                        }
                    }
                    Section(header: Text(L10nSettings.formGroupLinks)) {
                        ForEach(0 ... fieldsLinks.count - 1, id: \.self) {
                            AppTextField(field: $fieldsLinks[$0], initValidate: true) { error in
                                self.error = error
                            }
                        }
                    }
                    Section(header: Text(L10nSettings.formGroupBio)) {
                        ForEach(0 ... fieldsBio.count - 1, id: \.self) {
                            AppTextField(field: $fieldsBio[$0], initValidate: true) { error in
                                self.error = error
                            }
                        }
                    }
                    Section {
                        Button(L10nSettings.formButtonSubmit) {
                            viewModel.update(
                                name: fieldsAbout[0].value,
                                blog: fieldsLinks[0].value,
                                twitterUsername: fieldsLinks[1].value,
                                company: fieldsAbout[1].value,
                                location: fieldsAbout[2].value,
                                bio: fieldsBio[0].value
                            )
                        }
                        .buttonStyle(BottomPrimaryStyle())
                        .disabled(fieldsAbout.isNotValid() || fieldsLinks.isNotValid() || fieldsBio.isNotValid() || viewModel.loading)
                        .listRowInsets(.init())
                        .listRowBackground(Color.clear)
                    }
                    .listRowBackground(Color.clear)
                }
            }
        }
        .navigationBarTitle(L10nSettings.title)
        .navigationBarTitleDisplayMode(.large)
        .navigationBarItems(trailing: HStack {
            if viewModel.loading {
                ProgressView()
                    .progressViewStyle(CircularProgressViewStyle(tint: .orange))
            }
        })
    }
}

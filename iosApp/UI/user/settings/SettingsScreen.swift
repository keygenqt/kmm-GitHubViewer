//
//  Settings.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 16.10.2022.
//

import SwiftUI

struct SettingsScreen: View {
    // model
    @ObservedObject var viewModel = SettingsViewModel()
    // graph
    @EnvironmentObject var graph: GraphObservable
    // router
    @EnvironmentObject var router: RouterUser
    // form states
    @State private var error: String?
    // form value
    @State private var fieldName: IFieldText
    @State private var fieldCompany: IFieldText
    @State private var fieldLocation: IFieldText
    @State private var fieldBlog: IFieldText
    @State private var fieldTwitter: IFieldText
    @State private var fieldBio: IFieldText

    init(_ model: UserModel) {
        _fieldName = State(
            initialValue: NameField(value: model.name ?? "")
        )
        _fieldCompany = State(
            initialValue: CompanyField(value: model.company ?? "")
        )
        _fieldLocation = State(
            initialValue: LocationField(value: model.location ?? "")
        )
        _fieldBlog = State(
            initialValue: BlogField(value: model.blog ?? "")
        )
        _fieldTwitter = State(
            initialValue: TwitterField(value: model.twitterUsername ?? "")
        )
        _fieldBio = State(
            initialValue: BioField(value: model.bio ?? "")
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
                    Section(header: Text(L10nSettings.formGroupAbout)) {
                        AppFieldText(field: $fieldName, initValidate: true) { error in
                            self.error = error
                        }
                        AppFieldText(field: $fieldCompany, initValidate: true) { error in
                            self.error = error
                        }
                        AppFieldText(field: $fieldLocation, initValidate: true) { error in
                            self.error = error
                        }
                    }
                    Section(header: Text(L10nSettings.formGroupLinks)) {
                        AppFieldText(field: $fieldBlog, initValidate: true) { error in
                            self.error = error
                        }
                        AppFieldText(field: $fieldTwitter, initValidate: true) { error in
                            self.error = error
                        }
                    }
                    Section(header: Text(L10nSettings.formGroupBio)) {
                        AppFieldText(field: $fieldBio, initValidate: true) { error in
                            self.error = error
                        }
                    }
                    Section {
                        Button(L10nSettings.formButtonSubmit) {
                            viewModel.update(
                                name: fieldName.value,
                                blog: fieldBlog.value,
                                twitterUsername: fieldTwitter.value,
                                company: fieldCompany.value,
                                location: fieldLocation.value,
                                bio: fieldBio.value
                            )
                        }
                        .buttonStyle(BottomPrimaryStyle())
                        .disabled(!fieldName.isValid
                            || !fieldBlog.isValid
                            || !fieldTwitter.isValid
                            || !fieldCompany.isValid
                            || !fieldLocation.isValid
                            || !fieldBio.isValid
                            || viewModel.loading)
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

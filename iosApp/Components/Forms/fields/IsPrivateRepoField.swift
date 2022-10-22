//
//  IsPrivateRepoField.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 18.10.2022.
//

import Foundation

struct IsPrivateRepoField: IFieldSwitch {
    var label: String = L10nRepoUpdate.formLabelIsPrivate
    var value: Bool = false
}

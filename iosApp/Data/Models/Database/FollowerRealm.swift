//
//  FollowerRealm.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 16.10.2021.
//

import Foundation
import RealmSwift

class FollowerRealm: Object {
    @objc dynamic var id: Int = 1
    @objc dynamic var login: String = ""
    @objc dynamic var avatarUrl: String = ""
    @objc dynamic var url: String = ""
}

extension Array where Element == FollowerRealm {
    func toFollowerModels() -> [FollowerModel] {
        var models = [FollowerModel]()
        for x in self {
            models.append(x.toModel())
        }
        return models
    }
}

extension FollowerRealm {
    func toModel() -> FollowerModel {
        let model = FollowerModel()
        model.id = id
        model.login = login
        model.avatarUrl = avatarUrl
        model.url = url
        return model
    }
}

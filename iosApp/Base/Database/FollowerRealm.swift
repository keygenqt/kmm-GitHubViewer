//
//  FollowerRealm.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 16.10.2021.
//

import Foundation
import RealmSwift
import shared

class FollowerRealm: Object {
    @objc dynamic var id: String = ""
    @objc dynamic var login: String = ""
    @objc dynamic var avatarUrl: String = ""
    @objc dynamic var url: String = ""

    override static func primaryKey() -> String? {
        return "id"
    }
}

extension Array where Element == FollowerRealm {
    func toModels() -> [FollowerModel] {
        var models = [FollowerModel]()
        for x in self {
            models.append(x.toModel())
        }
        return models
    }
}

extension Array where Element == FollowerModel {
    func toRealms() -> [FollowerRealm] {
        var models = [FollowerRealm]()
        for x in self {
            models.append(x.toRealm())
        }
        return models
    }
}

extension FollowerRealm {
    func toModel() -> FollowerModel {
        return FollowerModelKt.createFollowerModel(
            id: id,
            login: login,
            avatarUrl: avatarUrl,
            url: url
        )
    }
}

extension FollowerModel: Identifiable {
    func toRealm() -> FollowerRealm {
        let realmModel = FollowerRealm()
        realmModel.id = id
        realmModel.login = login
        realmModel.avatarUrl = avatarUrl
        realmModel.url = url
        return realmModel
    }
}

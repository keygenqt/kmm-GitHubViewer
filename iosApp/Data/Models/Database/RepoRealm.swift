//
//  RepoModel.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 16.10.2021.
//

import Foundation
import RealmSwift

class RepoRealm: Object {
    @objc dynamic var id: Int = 1
    @objc dynamic var name: String = ""
    @objc dynamic var language: String = ""
    @objc dynamic var createdAt: String = ""
    @objc dynamic var desc: String = ""
    @objc dynamic var isPrivate: Bool = false
}

extension Array where Element == RepoRealm {
    func toRepoModels() -> [RepoModel] {
        var models = [RepoModel]()
        for x in self {
            models.append(x.toModel())
        }
        return models
    }
}

extension RepoRealm {
    func toModel() -> RepoModel {
        let model = RepoModel()
        model.id = id
        model.name = name
        model.language = language
        model.createdAt = createdAt
        model.description = desc
        model.isPrivate = isPrivate
        return model
    }
}

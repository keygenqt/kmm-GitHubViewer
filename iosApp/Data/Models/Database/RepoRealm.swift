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
    @objc dynamic var stargazersCount: Int = 0
    @objc dynamic var forks: Int = 0
    @objc dynamic var watchers: Int = 0
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
        model.stargazersCount = stargazersCount
        model.forks = forks
        model.watchers = watchers
        return model
    }
}

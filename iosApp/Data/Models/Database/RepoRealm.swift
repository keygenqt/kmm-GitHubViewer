//
//  RepoModel.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 16.10.2021.
//

import Foundation
import RealmSwift

class RepoRealm: Object {
    @objc dynamic var id: Int = 0
    @objc dynamic var name: String = ""
    @objc dynamic var language: String = ""
    @objc dynamic var desc: String = ""
    @objc dynamic var url: String = ""
    @objc dynamic var isPrivate: Bool = false
    @objc dynamic var stargazersCount: Int = 0
    @objc dynamic var forks: Int = 0
    @objc dynamic var watchers: Int = 0
    @objc dynamic var visibility: String = ""
    @objc dynamic var openIssuesCount: Int = 0
    @objc dynamic var watchersCount: Int = 0
    @objc dynamic var size: Int = 0
    @objc dynamic var fullName: String = ""
    @objc dynamic var createdAt: Double = 0
    @objc dynamic var updatedAt: Double = 0
    @objc dynamic var license: String = ""
    @objc dynamic var owner: String = ""
    @objc dynamic var isList: Bool = true

    override static func primaryKey() -> String? {
        return "url"
    }
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
        model.description = desc
        model.url = url
        model.isPrivate = isPrivate
        model.stargazersCount = stargazersCount
        model.forks = forks
        model.watchers = watchers
        model.visibility = visibility
        model.openIssuesCount = openIssuesCount
        model.watchersCount = watchersCount
        model.size = size
        model.fullName = fullName
        model.createdAt = createdAt
        model.updatedAt = updatedAt
        model.license = license
        model.owner = owner
        model.isList = isList
        return model
    }
}

//
//  RepoModel.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 16.10.2021.
//

import Foundation
import RealmSwift
import shared

class RepoRealm: Object {
    @objc dynamic var id: String = ""
    @objc dynamic var url: String = ""
    @objc dynamic var language: String?
    @objc dynamic var isPrivate: Bool = false
    @objc dynamic var name: String = ""
    @objc dynamic var fullName: String = ""
    @objc dynamic var ownerName: String = ""
    @objc dynamic var license: String?
    @objc dynamic var visibility: String = ""
    @objc dynamic var desc: String?
    @objc dynamic var stargazersCount: Int32 = 0
    @objc dynamic var forks: Int32 = 0
    @objc dynamic var openIssuesCount: Int32 = 0
    @objc dynamic var watchersCount: Int32 = 0
    @objc dynamic var size: Int32 = 0
    @objc dynamic var updatedAt: String = ""
    @objc dynamic var createdAt: String = ""
    
    @objc dynamic var isList: Bool = true

    override static func primaryKey() -> String? {
        return "url"
    }
}

extension Array where Element == RepoRealm {
    func toModels() -> [RepoModel] {
        var models = [RepoModel]()
        for x in self {
            models.append(x.toModel())
        }
        return models
    }
}

extension Array where Element == RepoModel {
    func toRealms() -> [RepoRealm] {
        var models = [RepoRealm]()
        for x in self {
            models.append(x.toRealm())
        }
        return models
    }
}

extension RepoRealm {
    func toModel() -> RepoModel {
        return RepoModelKt.createRepoModel(
            id: id,
            url: url,
            language: language,
            isPrivate: isPrivate,
            name: name,
            fullName: fullName,
            ownerName: ownerName,
            license: license,
            visibility: visibility,
            desc: desc,
            stargazersCount: stargazersCount,
            forks: forks,
            openIssuesCount: openIssuesCount,
            watchersCount: watchersCount,
            size: size,
            updatedAt: updatedAt,
            createdAt: createdAt
        )
    }
}

extension RepoModel: Identifiable {
    func toRealm() -> RepoRealm {
        let realmModel = RepoRealm()
        realmModel.id = id
        realmModel.url = url
        realmModel.language = language
        realmModel.isPrivate = isPrivate
        realmModel.name = name
        realmModel.fullName = fullName
        realmModel.ownerName = owner.login
        realmModel.license = license?.name
        realmModel.visibility = visibility
        realmModel.desc = desc
        realmModel.stargazersCount = stargazersCount
        realmModel.forks = forks
        realmModel.openIssuesCount = openIssuesCount
        realmModel.watchersCount = watchersCount
        realmModel.size = size
        realmModel.updatedAt = updatedAt
        realmModel.createdAt = createdAt
        return realmModel
    }
}

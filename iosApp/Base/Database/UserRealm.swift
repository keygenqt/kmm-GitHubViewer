//
//  UserRealm.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 17.10.2022.
//

import Foundation
import RealmSwift
import shared

class UserRealm: Object {
    @objc dynamic var id: String = ""
    @objc dynamic var avatarUrl: String = ""
    @objc dynamic var name: String = ""
    @objc dynamic var company: String?
    @objc dynamic var twitterUsername: String?
    @objc dynamic var publicRepos: Int32 = 0
    @objc dynamic var followers: Int32 = 0
    @objc dynamic var following: Int32 = 0
    @objc dynamic var blog: String?
    @objc dynamic var location: String?
    @objc dynamic var bio: String?
    @objc dynamic var createdAt: String = ""

    override static func primaryKey() -> String? {
        return "id"
    }
}

extension Array where Element == UserRealm {
    func toModels() -> [UserModel] {
        var models = [UserModel]()
        for x in self {
            models.append(x.toModel())
        }
        return models
    }
}

extension Array where Element == UserModel {
    func toRealms() -> [UserRealm] {
        var models = [UserRealm]()
        for x in self {
            models.append(x.toRealm())
        }
        return models
    }
}

extension UserRealm {
    func toModel() -> UserModel {
        return UserModelKt.createUserModel(
            id: id,
            avatarUrl: avatarUrl,
            name: name,
            company: company,
            twitterUsername: twitterUsername,
            publicRepos: publicRepos,
            followers: followers,
            following: following,
            blog: blog,
            location: location,
            bio: bio,
            createdAt: createdAt
        )
    }
}

extension UserModel: Identifiable {
    func toRealm() -> UserRealm {
        let realmModel = UserRealm()
        realmModel.id = id
        realmModel.avatarUrl = avatarUrl
        realmModel.name = name
        realmModel.company = company
        realmModel.twitterUsername = twitterUsername
        realmModel.publicRepos = publicRepos
        realmModel.followers = followers
        realmModel.following = following
        realmModel.blog = blog
        realmModel.location = location
        realmModel.bio = bio
        realmModel.createdAt = createdAt
        return realmModel
    }
}

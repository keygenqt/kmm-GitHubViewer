//
//  UserRealm.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 17.10.2022.
//

import Foundation
import RealmSwift

class UserRealm: Object {
    @objc dynamic var id: Int = 1
    @objc dynamic var login: String = ""
    @objc dynamic var nodeId: String = ""
    @objc dynamic var avatarUrl: String = ""
    @objc dynamic var gravatarId: String = ""
    @objc dynamic var url: String = ""
    @objc dynamic var htmlUrl: String = ""
    @objc dynamic var followersUrl: String = ""
    @objc dynamic var followingUrl: String = ""
    @objc dynamic var gistsUrl: String = ""
    @objc dynamic var starredUrl: String = ""
    @objc dynamic var subscriptionsUrl: String = ""
    @objc dynamic var organizationsUrl: String = ""
    @objc dynamic var reposUrl: String = ""
    @objc dynamic var eventsUrl: String = ""
    @objc dynamic var receivedEventsUrl: String = ""
    @objc dynamic var type: String = ""
    @objc dynamic var siteAdmin: Bool = false
    @objc dynamic var name: String = ""
    @objc dynamic var company: String = ""
    @objc dynamic var blog: String = ""
    @objc dynamic var location: String = ""
    @objc dynamic var email: String = ""
    @objc dynamic var hireable: String = ""
    @objc dynamic var bio: String = ""
    @objc dynamic var twitterUsername: String = ""
    @objc dynamic var publicRepos: Int = 0
    @objc dynamic var publicGists: Int = 0
    @objc dynamic var followers: Int = 0
    @objc dynamic var following: Int = 0
    @objc dynamic var createdAt: Double = 0
    @objc dynamic var updatedAt: Double = 0

    override static func primaryKey() -> String? {
        return "id"
    }
}

extension Array where Element == UserRealm {
    func toUserModels() -> [UserModel] {
        var models = [UserModel]()
        for x in self {
            models.append(x.toModel())
        }
        return models
    }
}

extension UserRealm {
    func toModel() -> UserModel {
        let model = UserModel()
        model.id = id
        model.login = login
        model.nodeId = nodeId
        model.avatarUrl = avatarUrl
        model.gravatarId = gravatarId
        model.url = url
        model.htmlUrl = htmlUrl
        model.followersUrl = followersUrl
        model.followingUrl = followingUrl
        model.gistsUrl = gistsUrl
        model.starredUrl = starredUrl
        model.subscriptionsUrl = subscriptionsUrl
        model.organizationsUrl = organizationsUrl
        model.reposUrl = reposUrl
        model.eventsUrl = eventsUrl
        model.receivedEventsUrl = receivedEventsUrl
        model.type = type
        model.siteAdmin = siteAdmin
        model.name = name
        model.company = company
        model.blog = blog
        model.location = location
        model.email = email
        model.hireable = hireable
        model.bio = bio
        model.twitterUsername = twitterUsername
        model.publicRepos = publicRepos
        model.publicGists = publicGists
        model.followers = followers
        model.following = following
        model.createdAt = createdAt
        model.updatedAt = updatedAt
        return model
    }
}

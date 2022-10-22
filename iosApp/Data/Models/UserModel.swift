//
//  UserModel.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 17.10.2022.
//

import Foundation

class UserModel: Decodable, Identifiable {
    var id: Int?
    var login: String?
    var nodeId: String?
    var avatarUrl: String?
    var gravatarId: String?
    var url: String?
    var htmlUrl: String?
    var followersUrl: String?
    var followingUrl: String?
    var gistsUrl: String?
    var starredUrl: String?
    var subscriptionsUrl: String?
    var organizationsUrl: String?
    var reposUrl: String?
    var eventsUrl: String?
    var receivedEventsUrl: String?
    var type: String?
    var siteAdmin: Bool?
    var name: String?
    var company: String?
    var blog: String?
    var location: String?
    var email: String?
    var hireable: String?
    var bio: String?
    var twitterUsername: String?
    var publicRepos: Int?
    var publicGists: Int?
    var followers: Int?
    var following: Int?
    var createdAt: Double?
    var updatedAt: Double?

    enum CodingKeys: String, CodingKey {
        case id
        case login
        case node_id
        case avatar_url
        case gravatar_id
        case url
        case html_url
        case followers_url
        case following_url
        case gists_url
        case starred_url
        case subscriptions_url
        case organizations_url
        case repos_url
        case events_url
        case received_events_url
        case type
        case site_admin
        case name
        case company
        case blog
        case location
        case email
        case hireable
        case bio
        case twitter_username
        case public_repos
        case public_gists
        case followers
        case following
        case created_at
        case updated_at
    }

    init() {}

    required init(from decoder: Decoder) throws {
        let container = try decoder.container(keyedBy: CodingKeys.self)
        id = try? container.decode(Int.self, forKey: .id)
        login = try? container.decode(String.self, forKey: .login)
        nodeId = try? container.decode(String.self, forKey: .node_id)
        avatarUrl = try? container.decode(String.self, forKey: .avatar_url)
        gravatarId = try? container.decode(String.self, forKey: .gravatar_id)
        url = try? container.decode(String.self, forKey: .url)
        htmlUrl = try? container.decode(String.self, forKey: .html_url)
        followersUrl = try? container.decode(String.self, forKey: .followers_url)
        followingUrl = try? container.decode(String.self, forKey: .following_url)
        gistsUrl = try? container.decode(String.self, forKey: .gists_url)
        starredUrl = try? container.decode(String.self, forKey: .starred_url)
        subscriptionsUrl = try? container.decode(String.self, forKey: .subscriptions_url)
        organizationsUrl = try? container.decode(String.self, forKey: .organizations_url)
        reposUrl = try? container.decode(String.self, forKey: .repos_url)
        eventsUrl = try? container.decode(String.self, forKey: .events_url)
        receivedEventsUrl = try? container.decode(String.self, forKey: .received_events_url)
        type = try? container.decode(String.self, forKey: .type)
        siteAdmin = try? container.decode(Bool.self, forKey: .site_admin)
        name = try? container.decode(String.self, forKey: .name)
        company = try? container.decode(String.self, forKey: .company)
        blog = try? container.decode(String.self, forKey: .blog)
        location = try? container.decode(String.self, forKey: .location)
        email = try? container.decode(String.self, forKey: .email)
        hireable = try? container.decode(String.self, forKey: .hireable)
        bio = try? container.decode(String.self, forKey: .bio)
        twitterUsername = try? container.decode(String.self, forKey: .twitter_username)
        publicRepos = try? container.decode(Int.self, forKey: .public_repos)
        publicGists = try? container.decode(Int.self, forKey: .public_gists)
        followers = try? container.decode(Int.self, forKey: .followers)
        following = try? container.decode(Int.self, forKey: .following)
        createdAt = try? container.decode(String.self, forKey: .created_at).toTimestamp()
        updatedAt = try? container.decode(String.self, forKey: .updated_at).toTimestamp()
    }
}

extension Array where Element == UserModel {
    func toUserRealms() -> [UserRealm] {
        var models = [UserRealm]()
        for x in self {
            models.append(x.toRealm())
        }
        return models
    }
}

extension UserModel {
    func toRealm() -> UserRealm {
        let realmModel = UserRealm()
        realmModel.id = id ?? 1
        realmModel.login = login ?? ""
        realmModel.nodeId = nodeId ?? ""
        realmModel.avatarUrl = avatarUrl ?? ""
        realmModel.gravatarId = gravatarId ?? ""
        realmModel.url = url ?? ""
        realmModel.htmlUrl = htmlUrl ?? ""
        realmModel.followersUrl = followersUrl ?? ""
        realmModel.followingUrl = followingUrl ?? ""
        realmModel.gistsUrl = gistsUrl ?? ""
        realmModel.starredUrl = starredUrl ?? ""
        realmModel.subscriptionsUrl = subscriptionsUrl ?? ""
        realmModel.organizationsUrl = organizationsUrl ?? ""
        realmModel.reposUrl = reposUrl ?? ""
        realmModel.eventsUrl = eventsUrl ?? ""
        realmModel.receivedEventsUrl = receivedEventsUrl ?? ""
        realmModel.type = type ?? ""
        realmModel.siteAdmin = siteAdmin ?? false
        realmModel.name = name ?? ""
        realmModel.company = company ?? ""
        realmModel.blog = blog ?? ""
        realmModel.location = location ?? ""
        realmModel.email = email ?? ""
        realmModel.hireable = hireable ?? ""
        realmModel.bio = bio ?? ""
        realmModel.twitterUsername = twitterUsername ?? ""
        realmModel.publicRepos = publicRepos ?? 0
        realmModel.publicGists = publicGists ?? 0
        realmModel.followers = followers ?? 0
        realmModel.following = following ?? 0
        realmModel.createdAt = createdAt ?? 0
        realmModel.updatedAt = updatedAt ?? 0
        return realmModel
    }
}

//
//  RepoModel.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 09.10.2021.
//

import Foundation

class RepoModel: Decodable, Identifiable {
    var id: Int?
    var name: String?
    var language: String?
    var createdAt: String?
    var description: String?
    var isPrivate: Bool?
    var stargazersCount: Int?
    var forks: Int?
    var watchers: Int?

    enum CodingKeys: String, CodingKey {
        case id
        case name
        case language
        case created_at
        case description
        case `private`
        case stargazers_count
        case forks
        case watchers
    }

    init() {}

    required init(from decoder: Decoder) throws {
        let container = try decoder.container(keyedBy: CodingKeys.self)
        id = try? container.decode(Int.self, forKey: .id)
        name = try? container.decode(String.self, forKey: .name)
            .replacingOccurrences(of: "android-", with: "")
            .replacingOccurrences(of: "api-", with: "")
            .replacingOccurrences(of: "compose-", with: "")
            .replacingOccurrences(of: "js-", with: "")
            .replacingOccurrences(of: "design-", with: "")
            .replacingOccurrences(of: "yii2-", with: "")
            .capitalizedSentence()
        language = try? container.decode(String.self, forKey: .language)
        createdAt = try? container.decode(String.self, forKey: .created_at)
        description = try? container.decode(String.self, forKey: .description)
        isPrivate = try? container.decode(Bool.self, forKey: .private)
        stargazersCount = try? container.decode(Int.self, forKey: .stargazers_count)
        forks = try? container.decode(Int.self, forKey: .forks)
        watchers = try? container.decode(Int.self, forKey: .watchers)
    }
}

extension Array where Element == RepoModel {
    func toRepoRealms() -> [RepoRealm] {
        var models = [RepoRealm]()
        for x in self {
            models.append(x.toRealm())
        }
        return models
    }
}

extension RepoModel {
    func toRealm() -> RepoRealm {
        let realmModel = RepoRealm()
        realmModel.id = id ?? 1
        realmModel.name = name ?? ""
        realmModel.language = language ?? ""
        realmModel.createdAt = createdAt ?? ""
        realmModel.desc = description ?? ""
        realmModel.isPrivate = isPrivate ?? false
        realmModel.stargazersCount = stargazersCount ?? 0
        realmModel.forks = forks ?? 0
        realmModel.watchers = watchers ?? 0
        return realmModel
    }

    static var mock: RepoModel {
        let model = RepoModel()
        model.id = 1
        model.name = "android-KMM"
        model.language = "kotlin"
        model.createdAt = "Saturday, October 16, 2021"
        model.description = "Web applications are increasingly showing recommended users from social media along with some descriptions, an attempt to show relevancy - why they are being shown."
        model.isPrivate = false
        return model
    }
}

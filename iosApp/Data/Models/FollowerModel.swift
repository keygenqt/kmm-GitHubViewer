//
//  FollowerModel.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 09.10.2021.
//

import Foundation

class FollowerModel: Decodable, Identifiable {
    var id: Int?
    var login: String?
    var avatarUrl: String?
    var url: String?

    enum CodingKeys: String, CodingKey {
        case id
        case login
        case avatar_url
        case html_url
    }

    init() {}

    required init(from decoder: Decoder) throws {
        let container = try decoder.container(keyedBy: CodingKeys.self)
        id = try? container.decode(Int.self, forKey: .id)
        login = try? container.decode(String.self, forKey: .login)
        avatarUrl = try? container.decode(String.self, forKey: .avatar_url)
        url = try? container.decode(String.self, forKey: .html_url)
    }
}

extension Array where Element == FollowerModel {
    func toFollowerRealms() -> [FollowerRealm] {
        var models = [FollowerRealm]()
        for x in self {
            models.append(x.toRealm())
        }
        return models
    }
}

extension FollowerModel {
    func toRealm() -> FollowerRealm {
        let realmModel = FollowerRealm()
        realmModel.id = id ?? 1
        realmModel.login = login ?? ""
        realmModel.avatarUrl = avatarUrl ?? ""
        realmModel.url = url ?? ""
        return realmModel
    }

    static var mock: FollowerModel {
        let model = FollowerModel()
        model.id = 1
        model.login = "Viatliy"
        model.avatarUrl = "https://w.wallhaven.cc/full/ym/wallhaven-ym192l.jpg"
        model.url = "https://github.com/keygenqt"
        return model
    }
}

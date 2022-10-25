////
////  RepoModel.swift
////  GitHubViewer
////
////  Created by Виталий Зарубин on 09.10.2021.
////
//
//import Foundation
//
//class RepoModelLicense: Decodable, Identifiable {
//    var name: String?
//}
//
//class RepoModelOwner: Decodable, Identifiable {
//    var login: String?
//}
//
//class RepoModel: Decodable, Identifiable {
//    var id: Int?
//    var name: String?
//    var language: String?
//    var description: String?
//    var url: String?
//    var isPrivate: Bool?
//    var stargazersCount: Int?
//    var forks: Int?
//    var watchers: Int?
//    var visibility: String?
//    var openIssuesCount: Int?
//    var watchersCount: Int?
//    var size: Int?
//    var fullName: String?
//    var createdAt: Double?
//    var updatedAt: Double?
//    var license: String?
//    var owner: String?
//    var isList: Bool = true
//
//    enum CodingKeys: String, CodingKey {
//        case id
//        case name
//        case language
//        case description
//        case url
//        case `private`
//        case stargazers_count
//        case forks
//        case watchers
//        case visibility
//        case open_issues_count
//        case watchers_count
//        case size
//        case full_name
//        case created_at
//        case updated_at
//        case owner
//        case license
//    }
//
//    init() {}
//
//    required init(from decoder: Decoder) throws {
//        let container = try decoder.container(keyedBy: CodingKeys.self)
//        id = try? container.decode(Int.self, forKey: .id)
//        name = try? container.decode(String.self, forKey: .name)
//            .replacingOccurrences(of: "android-", with: "")
//            .replacingOccurrences(of: "api-", with: "")
//            .replacingOccurrences(of: "compose-", with: "")
//            .replacingOccurrences(of: "js-", with: "")
//            .replacingOccurrences(of: "design-", with: "")
//            .replacingOccurrences(of: "yii2-", with: "")
//            .capitalizedSentence()
//
//        language = try? container.decode(String.self, forKey: .language)
//        description = try? container.decode(String.self, forKey: .description)
//        url = try? container.decode(String.self, forKey: .url)
//        isPrivate = try? container.decode(Bool.self, forKey: .private)
//        stargazersCount = try? container.decode(Int.self, forKey: .stargazers_count)
//        forks = try? container.decode(Int.self, forKey: .forks)
//        watchers = try? container.decode(Int.self, forKey: .watchers)
//        visibility = try? container.decode(String.self, forKey: .visibility)
//        openIssuesCount = try? container.decode(Int.self, forKey: .open_issues_count)
//        watchersCount = try? container.decode(Int.self, forKey: .watchers_count)
//        size = try? container.decode(Int.self, forKey: .size)
//        fullName = try? container.decode(String.self, forKey: .full_name)
//        createdAt = try? container.decode(String.self, forKey: .created_at).toTimestamp()
//        updatedAt = try? container.decode(String.self, forKey: .updated_at).toTimestamp()
//        owner = try? container.decode(RepoModelOwner.self, forKey: .owner).login
//        license = try? container.decode(RepoModelLicense.self, forKey: .license).name
//    }
//}
//
//extension Array where Element == RepoModel {
//    func toRealms() -> [RepoRealm] {
//        var models = [RepoRealm]()
//        for x in self {
//            models.append(x.toRealm())
//        }
//        return models
//    }
//}
//
//extension RepoModel {
//    func toRealm() -> RepoRealm {
//        let realmModel = RepoRealm()
//        realmModel.id = id ?? 1
//        realmModel.name = name ?? ""
//        realmModel.language = language ?? ""
//        realmModel.desc = description ?? ""
//        realmModel.url = url ?? ""
//        realmModel.isPrivate = isPrivate ?? false
//        realmModel.stargazersCount = stargazersCount ?? 0
//        realmModel.forks = forks ?? 0
//        realmModel.watchers = watchers ?? 0
//        realmModel.visibility = visibility ?? ""
//        realmModel.openIssuesCount = openIssuesCount ?? 0
//        realmModel.watchersCount = watchersCount ?? 0
//        realmModel.size = size ?? 0
//        realmModel.fullName = fullName ?? ""
//        realmModel.createdAt = createdAt ?? 0
//        realmModel.updatedAt = updatedAt ?? 0
//        realmModel.owner = owner ?? ""
//        realmModel.license = license ?? ""
//        realmModel.isList = isList
//        return realmModel
//    }
//
//    static var mock: RepoModel {
//        let model = RepoModel()
//        model.id = 1
//        model.name = "android-KMM"
//        model.language = "kotlin"
//        model.description = "Web applications are increasingly showing recommended users from social media along with some descriptions, an attempt to show relevancy - why they are being shown."
//        model.isPrivate = false
//        return model
//    }
//}

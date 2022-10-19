//
//  ReposDataService.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 17.10.2021.
//

import Foundation
import RealmSwift

struct ReposData {
    func getList() -> [RepoRealm] {
        do {
            let realm = try Realm()
            return realm.objects(RepoRealm.self).filter("isList=true").map { $0 }
        } catch {
            // handle error
            print(error)
        }
        return []
    }

    func save(_ models: [RepoRealm]) {
        do {
            let realm = try Realm()
            try realm.write {
                realm.add(models)
            }
        } catch {
            // handle error
            print(error)
        }
    }

    func clear() {
        do {
            let realm = try Realm()
            let list = realm.objects(RepoRealm.self)
            try realm.write {
                realm.delete(list)
            }
        } catch {
            // handle error
            print(error)
        }
    }

    func getModel(_ url: String) -> RepoRealm? {
        do {
            let realm = try Realm()
            return realm.object(ofType: RepoRealm.self, forPrimaryKey: url)
        } catch {
            // handle error
            print(error)
        }
        return nil
    }

    func save(_ model: RepoRealm) {
        do {
            let realm = try Realm()
            if let obj = getModel(model.url) {
                try realm.write {
                    obj.id = model.id
                    obj.name = model.name
                    obj.language = model.language
                    obj.desc = model.desc
                    obj.isPrivate = model.isPrivate
                    obj.stargazersCount = model.stargazersCount
                    obj.forks = model.forks
                    obj.watchers = model.watchers
                    obj.isList = model.isList
                }
            } else {
                model.isList = false
                try realm.write {
                    realm.add(model)
                }
            }

        } catch {
            // handle error
            print(error)
        }
    }
}

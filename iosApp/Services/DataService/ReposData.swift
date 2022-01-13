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
            return realm.objects(RepoRealm.self).map { $0 }
        } catch {
            // handle error
            print(error)
        }
        return []
    }

    func saveList(_ models: [RepoRealm]) {
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
            let allUploadingObjects = realm.objects(RepoRealm.self)
            try realm.write {
                realm.delete(allUploadingObjects)
            }
        } catch {
            // handle error
            print(error)
        }
    }
}

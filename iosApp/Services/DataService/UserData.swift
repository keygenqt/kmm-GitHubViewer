//
//  UserData.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 17.10.2022.
//

import Foundation
import RealmSwift

struct UserData {
    func getUser() -> UserRealm? {
        do {
            let realm = try Realm()
            let list = realm.objects(UserRealm.self).map { $0 }
            return list.isEmpty ? nil : list[0]
        } catch {
            // handle error
            print(error)
        }
        return nil
    }

    func saveUser(_ model: UserRealm) {
        do {
            let realm = try Realm()
            try realm.write {
                realm.add(model)
            }
        } catch {
            // handle error
            print(error)
        }
    }

    func clear() {
        do {
            let realm = try Realm()
            let allUploadingObjects = realm.objects(UserRealm.self)
            try realm.write {
                realm.delete(allUploadingObjects)
            }
        } catch {
            // handle error
            print(error)
        }
    }
}

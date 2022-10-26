////
////  FollowerDataService.swift
////  GitHubViewer
////
////  Created by Виталий Зарубин on 17.10.2021.
////
//
//import Foundation
//import RealmSwift
//
//struct FollowerData {
//    func getList() -> [FollowerRealm] {
//        do {
//            let realm = try Realm()
//            return realm.objects(FollowerRealm.self).map { $0 }
//        } catch {
//            // handle error
//            print(error)
//        }
//        return []
//    }
//
//    func saveList(_ models: [FollowerRealm]) {
//        do {
//            let realm = try Realm()
//            try realm.write {
//                realm.add(models)
//            }
//        } catch {
//            // handle error
//            print(error)
//        }
//    }
//
//    func clear() {
//        do {
//            let realm = try Realm()
//            let allUploadingObjects = realm.objects(FollowerRealm.self)
//            try realm.write {
//                realm.delete(allUploadingObjects)
//            }
//        } catch {
//            // handle error
//            print(error)
//        }
//    }
//}

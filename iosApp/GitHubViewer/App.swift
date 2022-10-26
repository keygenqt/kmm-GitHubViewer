//
//  GitHubViewerApp.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 05.10.2021.
//

import Firebase
import SwiftUI
import shared

@main
struct GitHubViewer: App {
    @UIApplicationDelegateAdaptor private var appDelegate: AppDelegate

    var body: some Scene {
        WindowGroup {
            NavGraph()
        }
    }

    class AppDelegate: NSObject, UIApplicationDelegate {
        func application(_: UIApplication, didFinishLaunchingWithOptions _: [UIApplication.LaunchOptionsKey: Any]? = nil) -> Bool {
            FirebaseApp.configure()
            return true
        }
    }
}

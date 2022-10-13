//
//  GitHubViewerApp.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 05.10.2021.
//

import AlamofireNetworkActivityLogger
import Firebase
import SwiftUI

@main
struct AppGitHubViewer: App {
    @UIApplicationDelegateAdaptor private var appDelegate: AppDelegate

    var body: some Scene {
        WindowGroup {
            NavGraph()
        }
    }

    class AppDelegate: NSObject, UIApplicationDelegate {
        func application(_: UIApplication, didFinishLaunchingWithOptions _: [UIApplication.LaunchOptionsKey: Any]? = nil) -> Bool {
            // configure fb
            FirebaseApp.configure()
            // logging http
            #if DEBUG
                NetworkActivityLogger.shared.level = .debug
                NetworkActivityLogger.shared.startLogging()
            #endif
            return true
        }
    }
}

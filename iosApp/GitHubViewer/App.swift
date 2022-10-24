//
//  GitHubViewerApp.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 05.10.2021.
//

import AlamofireNetworkActivityLogger
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
            // init value-key
            
            print("-----------")
            let st = StorageKMM(impl: UserDefaults.standard)
            print(st.isOnboardingDone)
            st.isOnboardingDone = !st.isOnboardingDone
            print(st.isOnboardingDone)
            print("----------- clearCache")
            st.clearCache()
            print(st.isOnboardingDone)
            print("-----------")
            
            UserDefaults.standard.register(defaults: [
                AppKeyValue.keyRepoOrder: true,
            ])
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

//
//  NavGraphGuest.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 13.01.2022.
//

import SwiftUI

struct NavGraph: View {
    // graph
    @StateObject var graph = GraphObservable()
    // routers
    @StateObject var routerOnboarding = RouterOnboarding()
    @StateObject var routerGuest = RouterGuest()
    @StateObject var routerUser = RouterUser()

    var body: some View {
        NavigationView {
            switch graph.route {
            case .onboarding:
                OnboardingScreen()
            case .guest:
                WelcomeScreen()
            case .user:
                UserTabs()
            }
        }
        .environmentObject(graph)
        .environmentObject(routerOnboarding)
        .environmentObject(routerGuest)
        .environmentObject(routerUser)
    }
}

// struct NavGraphGuest: View {
//    @StateObject var graph = AppGraph1()
//    @StateObject var router1 = AppRouting1()
//    @StateObject var router2 = AppRouting2()
//
//    var body: some View {
//        NavigationView {
//            switch graph.graph {
//            case .graph1:
//                ContentView()
//            case .graph2:
//                ContentView10()
//            }
//        }
//        .environmentObject(router2)
//        .environmentObject(router1)
//        .environmentObject(graph)
//    }
// }

// class AppGraph1: ObservableObject {
//    @Published var route: AppGraph = .graph1
// }

// enum AppGraph {
//    case graph1
//    case graph2
// }

// enum AppRoute1 {
//    case view1
//    case view2
//    case view3
// }
//
// enum AppRoute2 {
//    case view1
//    case view2
//    case view3
// }
//
// class AppRouting1: ObservableObject {
//    @Published var route: AppRoute1? = .view1
//
//    func isActive(for route: AppRoute1) -> Binding<Bool> {
//        return Binding(get: { self.route != route }, set: { self.route = $0 ? nil : route })
//    }
// }
//
//// struct NavigationView1: View {
////    @StateObject var router = AppRouting1()
////
////    var body: some View {
////        ContentView().environmentObject(router)
////    }
//// }
//
// struct ContentView: View {
//    @EnvironmentObject var router: AppRouting1
//
//    var body: some View {
//        NavigationLink(
//            destination: ContentView2(),
//            isActive: router.isActive(for: .view1)
//        ) {
//            Text("Hello, World!")
//        }
//        .isDetailLink(false)
//        .navigationBarTitle("Root")
//    }
// }
//
// struct ContentView2: View {
//    @EnvironmentObject var router: AppRouting1
//
//    var body: some View {
//        NavigationLink(
//            destination: ContentView3(),
//            isActive: router.isActive(for: .view2)
//        ) {
//            Text("Hello, World #2!")
//        }
//        .isDetailLink(false)
//        .navigationBarTitle("Two")
//    }
// }
//
// struct ContentView3: View {
//    @EnvironmentObject var router: AppRouting1
//    @EnvironmentObject var graph: AppGraph1
//
//    var body: some View {
//        VStack {
//            Text("Hello, World #3!")
//
//            Spacer().frame(height: 30)
//
//            Button(action: {
//                self.router.route = .view1
//            }) {
//                Text("Pop to root")
//            }
//
//            Spacer().frame(height: 30)
//
//            Button(action: {
//                self.router.route = .view2
//            }) {
//                Text("Pop to View2")
//            }
//
//            Spacer().frame(height: 30)
//
//            Button(action: {
//                self.graph.route = .graph2
//            }) {
//                Text("NEXT graph")
//            }
//
//        }.navigationBarTitle("Three")
//    }
// }
//
/////////////
//
// class AppRouting2: ObservableObject {
//    @Published var route: AppRoute2? = .view1
//
//    func isActive(for route: AppRoute2) -> Binding<Bool> {
//        return Binding(get: { self.route != route }, set: { self.route = $0 ? nil : route })
//    }
// }
//
//// struct NavigationView2: View {
////    @StateObject var router = AppRouting2()
////
////    var body: some View {
////        ContentView().environmentObject(router)
////    }
//// }
//
// struct ContentView10: View {
//    @EnvironmentObject var router: AppRouting2
//
//    var body: some View {
//        NavigationLink(
//            destination: ContentView11(),
//            isActive: router.isActive(for: .view1)
//        ) {
//            Text("ContentView10")
//        }
//        .isDetailLink(false)
//        .navigationBarTitle("Root")
//    }
// }
//
// struct ContentView11: View {
//    @EnvironmentObject var router: AppRouting2
//
//    var body: some View {
//        NavigationLink(
//            destination: ContentView12(),
//            isActive: router.isActive(for: .view2)
//        ) {
//            Text("ContentView11")
//        }
//        .isDetailLink(false)
//        .navigationBarTitle("Two")
//    }
// }
//
// struct ContentView12: View {
//    @EnvironmentObject var router: AppRouting2
//    @EnvironmentObject var graph: AppGraph1
//
//    var body: some View {
//        VStack {
//            Text("ContentView12")
//
//            Spacer().frame(height: 30)
//
//            Button(action: {
//                self.router.route = .view1
//            }) {
//                Text("Pop to root")
//            }
//
//            Spacer().frame(height: 30)
//
//            Button(action: {
//                self.router.route = .view2
//            }) {
//                Text("Pop to View2")
//            }
//
//            Spacer().frame(height: 30)
//
//            Button(action: {
//                self.graph.route = .graph1
//            }) {
//                Text("NEXT graph")
//            }
//
//        }.navigationBarTitle("Three")
//    }
// }

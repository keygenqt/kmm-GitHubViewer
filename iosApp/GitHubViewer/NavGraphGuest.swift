//
//  NavGraphGuest.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 13.01.2022.
//

import SwiftUI

struct NavGraphGuest: View {
    let router = OnboardingRouter()
    @State var activeNavigation: OnboardingRoute?
    @State var selectedView = 1

    var body: some View {
        NavigationView {
            let router = AppRouter()
            ContentView(router: router, isActive: router.$isActive1)
        }
    }
}

protocol AppRouting {
    associatedtype Route
    associatedtype View: SwiftUI.View
    var active: SelectionStore { get }
    @ViewBuilder func view(for route: Route) -> Self.View
}

enum AppRoute {
    case top
    case best
}

final class SelectionStore: ObservableObject {
    var selection: AppRoute = .top {
        didSet {
            print("Selection changed to \(selection)")
        }
    }
}

struct AppRouter: AppRouting {
    @State var isActive1: Bool = false
    @State var isActive2: Bool = false

    @ObservedObject var active = SelectionStore()

    func view(for route: AppRoute) -> some View {
        switch route {
        case .top:
            ContentView(router: self, isActive: $isActive1)
        case .best:
            ContentView2(router: self, isActive: $isActive2)
        }
    }
}

// prefix func ! (value: Binding<AppRoute>) -> Binding<Bool> {
//    Binding<Bool>(
//        get: { value.wrappedValue != .view1 },
//        set: { value.wrappedValue = $0 ? .view1 : .view3 }
//    )
// }

struct ContentView<Router: AppRouting>: View where Router.Route == AppRoute {
    let router: Router
    @Binding var isActive: Bool

    var body: some View {
        Button {
            print("test")
            router.active.selection = .best
        } label: {
            Text("Hello, World #1!")
        }
        NavigationLink(
            destination: router.view(for: .best),
            isActive: $isActive,
            label: { EmptyView() }
        )
        .isDetailLink(false)

//            NavigationLink(
//                destination: router.view(for: .view2),
//                isActive: !$active,
//                label: { EmptyView() }
//            )
//            .isDetailLink(false)
//            .navigationBarTitle("Root")

//            NavigationLink(
//                destination: ContentView2(rootIsActive: self.$isActive),
//                isActive: self.$isActive
//            ) {
//                Text("Hello, World!")
//            }
//            .isDetailLink(false)
//            .navigationBarTitle("Root")
    }
}

struct ContentView2<Router: AppRouting>: View where Router.Route == AppRoute {
    let router: Router
    @Binding var isActive: Bool
//    @ObservedObject var active: SelectionStore
    ///
    // @Binding var rootIsActive: Bool
    // @State var isActive2: Bool = false

    var body: some View {
        Button {
            print("test")
            router.active.selection = .top
        } label: {
            Text("Hello, World #2!")
        }

        NavigationLink(
            destination: router.view(for: .top),
            isActive: $isActive,
            label: { EmptyView() }
        )
        .isDetailLink(false)

//        NavigationLink(
//            destination: ContentView3(
//                shouldPopToRootView: self.$rootIsActive,
//                shouldPopToView2: self.$isActive2
//            ),
//            isActive: self.$isActive2
//        ) {
//            Text("Hello, World #2!")
//        }
//        .isDetailLink(false)
//        .navigationBarTitle("Two")
    }
}

struct ContentView3: View {
    @Binding var shouldPopToRootView: Bool
    @Binding var shouldPopToView2: Bool

    var body: some View {
        VStack {
            Text("Hello, World #3!")
            Button(action: { self.shouldPopToRootView = false }) {
                Text("Pop to root")
            }

            Button(action: { self.shouldPopToView2 = false }) {
                Text("Pop to View2")
            }
        }.navigationBarTitle("Three")
    }
}

struct NavGraphGuest_Previews: PreviewProvider {
    static var previews: some View {
        NavGraphGuest()
    }
}

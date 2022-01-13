//
//  NavGraphGuest.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 13.01.2022.
//

import SwiftUI

struct NavGraphGuest: View {
    @State var selectedView = 1
    @State private var showingAlert = false

    var body: some View {
        OnboardingScreen()
    }
}

struct NavGraphGuest_Previews: PreviewProvider {
    static var previews: some View {
        NavGraphGuest()
    }
}

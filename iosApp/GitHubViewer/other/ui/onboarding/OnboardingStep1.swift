//
//  OnboardingStep1.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 13.01.2022.
//

import Lottie
import SwiftUI

struct OnboardingStep1: View {
    var body: some View {
        VStack {
            LottieView(name: "step1").frame(width: 150, height: 150)
            Text(L10nApp.appName)
            Text(L10nApp.appLoading)
        }
    }
}

struct OnboardingStep1_Previews: PreviewProvider {
    static var previews: some View {
        OnboardingStep1()
    }
}

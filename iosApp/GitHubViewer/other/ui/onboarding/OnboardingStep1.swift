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

            Spacer().frame(height: 30)

            TextTitleLarge(
                text: L10nOnboarding.step1Title,
                alignment: .center
            )

            Spacer().frame(height: 20)

            TextBodyLarge(
                text: L10nOnboarding.step1Text,
                alignment: .center
            )
        }
    }
}

struct OnboardingStep1_Previews: PreviewProvider {
    static var previews: some View {
        OnboardingStep1()
    }
}

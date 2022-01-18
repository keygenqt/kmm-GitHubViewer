//
//  OnboardingStep5.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 13.01.2022.
//

import SwiftUI

struct OnboardingStep5: View {
    var body: some View {
        VStack {
            LottieView(name: "step5").frame(width: 150, height: 150)

            Spacer().frame(height: 30)

            TextTitleLarge(
                text: L10nOnboarding.step5Title,
                alignment: .center
            )

            Spacer().frame(height: 20)

            TextBodyLarge(
                text: L10nOnboarding.step5Text,
                alignment: .center
            )
        }.padding(20)
    }
}

struct OnboardingStep5_Previews: PreviewProvider {
    static var previews: some View {
        OnboardingStep5()
    }
}

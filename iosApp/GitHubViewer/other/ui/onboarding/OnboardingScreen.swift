//
//  OnboardingScreen.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 13.01.2022.
//

import AlertToast
import SwiftUI

struct OnboardingScreen: View {
    @ObservedObject var viewModel = OnboardingViewModel()

    @State private var tabSelection = 1
    @State private var showToast = false

    var body: some View {
        NavigationView {
            VStack {
                TabView(selection: $tabSelection) {
                    OnboardingStep1().tag(1)
                    OnboardingStep2().tag(2)
                    OnboardingStep3().tag(3)
                    OnboardingStep4().tag(4)
                    OnboardingStep5().tag(5)
                }
                .tabViewStyle(PageTabViewStyle())

                Button {
                    withAnimation {
                        self.tabSelection = tabSelection >= 5 ? 1 : tabSelection + 1
                    }
                } label: {
                    Text("Next")
                }
            }
            .toast(isPresenting: $showToast) {
                AlertToast(type: .regular, title: "Message Sent!")
            }
            .padding(20)
            .navigationTitle(L10nOnboarding.title)
            .navigationBarTitleDisplayMode(.inline)
            .toolbar {
                Button {
                    showToast.toggle()
                } label: {
                    TextLabelLarge(text: L10nOnboarding.skip)
                }
            }
        }
    }
}

struct OnboardingScreen_Previews: PreviewProvider {
    static var previews: some View {
        OnboardingScreen()
    }
}

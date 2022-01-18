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
    @State var progress: Float = 1

    init() {
        UIPageControl.appearance().currentPageIndicatorTintColor = UIColor(Color.onBackground)
        UIPageControl.appearance().pageIndicatorTintColor = UIColor(Color.onBackground).withAlphaComponent(0.2)
    }

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
                .tabViewStyle(PageTabViewStyle(indexDisplayMode: .always))

                ZStack {
                    CircularProgressIndicator(progress: progress)

                    Button {
                        withAnimation {
                            self.tabSelection = tabSelection >= 5 ? 1 : tabSelection + 1
                            self.progress = 1 - Float(tabSelection - 1) / Float(5 - 1)
                        }
                    } label: {
                        Image(systemName: "chevron.forward")
                            .frame(width: 60, height: 60)
                            .foregroundColor(Color.onPrimary)
                            .background(Color.primary)
                            .clipShape(Circle())
                    }
                }
            }
            .toast(isPresenting: $showToast) {
                AlertToast(displayMode: .banner(.slide), type: .regular, title: "Message Sent!")
            }
            .padding(.bottom, 20)
            .navigationTitle(L10nOnboarding.title)
            .navigationBarTitleDisplayMode(.inline)
            .toolbar {
                Button {
                    showToast.toggle()
                } label: {
                    TextLabelLarge(text: L10nOnboarding.skip)
                }
            }
            .background(Color.background)
        }
    }
}

struct OnboardingScreen_Previews: PreviewProvider {
    static var previews: some View {
        OnboardingScreen()
    }
}

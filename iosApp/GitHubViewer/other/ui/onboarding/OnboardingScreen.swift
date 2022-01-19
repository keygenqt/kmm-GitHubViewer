//
//  OnboardingScreen.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 13.01.2022.
//

import SwiftUI

struct OnboardingScreen: View {
    @ObservedObject var viewModel = OnboardingViewModel()

    @State private var tabSelection = 1
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
                    CircularProgressIndicator(progress: 1 - Float(tabSelection - 1) / Float(5 - 1))
                    Button {
                        withAnimation {
                            if self.tabSelection >= 5 {
                                viewModel.disableOnboarding()
                                print("TEST")
                            } else {
                                self.tabSelection += 1
                            }
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
            .padding(.bottom, 20)
            .navigationTitle(tabSelection == 1 ? L10nOnboarding.title : "")
            .navigationBarTitleDisplayMode(.inline)
            .navigationBarItems(leading:
                tabSelection > 1 ? Button(action: {
                    withAnimation {
                        self.tabSelection -= 1
                    }
                }, label: {
                    HStack {
                        Image(systemName: "chevron.backward")
                            .foregroundColor(Color.onBackground)
                            .font(.system(size: 14))
                        TextLabelLarge(text: L10nOnboarding.back)
                    }

                }) : nil)
            .toolbar {
                Button {
                    viewModel.disableOnboarding()
                    print("TEST")
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

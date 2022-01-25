//
//  LottieView.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 18.01.2022.
//

import Lottie
import SwiftUI

struct LottieView: View {
    var name: String
    @State private var lottieID = UUID()

    var body: some View {
        LottieUIView(name: name)
            .id(lottieID)
            .onAppear {
                lottieID = UUID()
            }
    }
}

private struct LottieUIView: UIViewRepresentable {
    var name: String
    var animationView = AnimationView()

    func makeUIView(context _: UIViewRepresentableContext<LottieUIView>) -> UIView {
        let view = UIView(frame: .zero)

        animationView.animation = Animation.named(name)
        animationView.contentMode = .scaleAspectFit
        animationView.loopMode = .loop
        animationView.play()

        animationView.translatesAutoresizingMaskIntoConstraints = false
        view.addSubview(animationView)

        NSLayoutConstraint.activate([
            animationView.heightAnchor.constraint(equalTo: view.heightAnchor),
            animationView.widthAnchor.constraint(equalTo: view.widthAnchor),
        ])

        return view
    }

    func updateUIView(_: UIView, context _: UIViewRepresentableContext<LottieUIView>) {}
}

//
//  CircularProgressIndicator.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 18.01.2022.
//

import SwiftUI

struct CircularProgressIndicator: View {
    var progress: Float
    @State private var offset: CGFloat = 0

    var body: some View {
        ZStack {
            Circle()
                .stroke(lineWidth: 2.0)
                .opacity(0.3)
                .foregroundColor(Color.primary)

            Circle()
                .trim(from: 0.0, to: CGFloat(min(self.progress, 1.0)))
                .stroke(style: StrokeStyle(lineWidth: 2.0, lineCap: .round, lineJoin: .round))
                .foregroundColor(Color.primary)
                .rotationEffect(Angle(degrees: 0))
                .animation(Animation.easeInOut(duration: 1.0), value: offset)
        }.frame(width: 65, height: 65)
    }
}

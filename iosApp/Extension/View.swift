//
//  View.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 15.10.2022.
//

import Foundation
import SwiftUI

extension View {
    func cornerRadius(_ radius: CGFloat, corners: UIRectCorner) -> some View {
       clipShape(RoundedCorner(radius: radius, corners: corners))
    }
}

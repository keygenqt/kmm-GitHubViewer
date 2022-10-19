//
//  Extension.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 04.02.2022.
//

import Foundation

extension Array where Element == IFieldText {
    func isValid() -> Bool {
        return !contains(where: { $0.isValid == false })
    }

    func isNotValid() -> Bool {
        return contains(where: { $0.isValid == false })
    }
}

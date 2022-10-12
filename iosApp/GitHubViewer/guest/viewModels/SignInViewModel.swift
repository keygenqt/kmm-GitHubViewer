//
//  SignInViewModel.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 13.01.2022.
//

import Combine
import Foundation

class SignInViewModel: ObservableObject, Identifiable {
    func authUser(_ tappedUrl: URLComponents?) -> Bool {
        AppKeyValue.setAuth(true)

        return tappedUrl?.path == "/oauth"
    }
}

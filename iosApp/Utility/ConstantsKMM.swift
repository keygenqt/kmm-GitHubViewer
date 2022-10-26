//
//  ConstantsApp.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 17.10.2021.
//

import Foundation
import shared

struct ConstantsKMM {
    // KMM storage
    static let STORAGE: CrossStorage = CrossStorage(storage: UserDefaults.standard)
    
    // KMM network service
    static let CLIENT: AppHttpClient = AppHttpClient(token: STORAGE.authToken)
    
    // KMM network service
    static let CONST: AppConstants = AppConstants()
    
    // KMM helper
    static let HELPER: AppHelper = AppHelper()
}

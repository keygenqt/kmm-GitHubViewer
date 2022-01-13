//
//  APIHandler.swift
//  GitHubViewer
//
//  Created by Виталий Зарубин on 17.10.2021.
//

import Alamofire
import Foundation

class APIHandler {
    var statusCode = Int.zero

    func handleResponse<T: Decodable>(_ response: DataResponse<T, AFError>) -> Any? {
        switch response.result {
        case .success:
            return response.value
        case .failure:
            return nil
        }
    }
}

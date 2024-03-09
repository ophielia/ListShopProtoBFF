//
//  iosApp.swift
//  ios
//
//  Created by Kevin Galligan on 9/10/23.
//

import SwiftUI
import allshared

@main
struct iosApp: App {
    let sdk = StartSDKKt.startSDK(analytics: IosAnalytics())

    
    var body: some Scene {
        WindowGroup {
            ContentView(viewModel: .init(sdk: sdk))
        }
    }
}

class IosAnalytics: Analytics {
    func sendEvent(eventName: String, eventArgs: [String : Any]) {
        // In a real app, you would call to your analytics backend here
        print("\(eventName) - \(eventArgs)")
    }
}

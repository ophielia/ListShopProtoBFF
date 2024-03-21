import SwiftUI
import allshared

struct ContentView: View {
  @ObservedObject private(set) var viewModel: ViewModel

    var body: some View {
        NavigationView {
            listView()
            .navigationBarTitle("List Shop POC")
            .navigationBarItems(trailing:
                Button("Reload") {
                    self.viewModel.loadTags(forceReload: true)
            })
        }
    }

    private func listView() -> AnyView {
        switch viewModel.tags {
        case .loading:
            return AnyView(Text("Loading...").multilineTextAlignment(.center))
        case .result(let tags):
            return AnyView(Text("Loading...").multilineTextAlignment(.center))
            /* return AnyView(List(tags) { tag in
                TagRow(tag: tag)
            })*/
        case .error(let description):
            return AnyView(Text(description).multilineTextAlignment(.center))
        }
    }
}

extension ContentView {
    
    enum LoadableTags {
        case loading
        case result([ListshopTag])
        case error(String)
    }
    
    @MainActor
    class ViewModel: ObservableObject {
        let tagUCP : ListshopTagUCP
        let dashboardUCP : ListshopDashboardUCP
        let onboardingUCP : ListshopOnboardingUCP
        @Published var tags = LoadableTags.loading
        
        init(sdk: SDKHandle) {
            print("STARTING OUT!!")
            self.tagUCP = sdk.tagUCP
            self.onboardingUCP = sdk.onboardingUCP
            self.dashboardUCP = sdk.dashboardUCP
            self.loadTags(forceReload: false)
            //self.callLoginUseCase()
            // self.callLogoutUsecase()
            self.callGetLaunchScreenUseCase()
            print("STARTING OUT!!")
        }
        
        func loadTags(forceReload: Bool) {
            Task {
                do {
                    self.tags = .loading
                    //let tags = try await tagUCP.getTags(forceReload: forceReload)
                    //self.tags = .result(tags)
                } catch {
                    self.tags = .error(error.localizedDescription)
                }
            }
        }   
        
        func callLoginUseCase() {
            Task {
                do {
                    let connectionStatus = ListshopConnectionStatus.online
                    let result = try await onboardingUCP.signIn(userName: "meg@the-list-shop.com", password: "sarrieb1357")
                    let test = result as ListshopBFFResult
                    if test.isSuccess ,
                       let val = result.value {
                        switch onEnum(of: val) {
                        case .onboarding(let screen):
                            print("the result is \(screen)")
                            
                        default:
                            print("dont know what kind of result this is")
                        }
                        print("the BFF result is: \(val)")
                    }
                    print("the first result is: \(result)")
                } catch {
                    self.tags = .error(error.localizedDescription)
                }
            }
        }
        
        
        
        func callGetLaunchScreenUseCase() {
            Task {
                do {
                    let connectionStatus = ListshopConnectionStatus.online
                    let result = try await onboardingUCP.systemGetLaunchScreen(connectionStatus: connectionStatus)
                    let test = result as ListshopBFFResult
                    if test.isSuccess ,
                       let val = result.value {
                        switch onEnum(of: val) {
                        case .onboarding(let screen):
                            print("the result is \(screen)")
                            print("the result is \(type(of: screen))")
                        case .listManagementScreen(let screen):
                            print("the result is \(screen)")
                            print("THIS IS WHAT WE NEED \(type(of: screen))")
                            print("THIS IS ALSO WHAT WE NEED \(type(of: screen.shoppingLists))")
                            
                         
                        default:
                            print("dont know what kind of result this is")
                        }
                        print("the BFF result is: \(val)")
                    }
                    print("the first result is: \(result)")
                } catch {
                    self.tags = .error(error.localizedDescription)
                }
            }
        }

        func callLogoutUsecase() {
                    Task {
                        do {
                            let connectionStatus = ListshopConnectionStatus.online
                            let result = try await dashboardUCP.logout()
                            let test = result as ListshopBFFResult
                            if test.isSuccess ,
                               let val = result.value {
                                switch onEnum(of: val) {
                                case .onboarding(let screen):
                                    print("the result is \(screen)")

                                default:
                                    print("dont know what kind of result this is")
                                }
                                print("the BFF result is: \(val)")
                            }
                            print("the first result is: \(result)")
                        } catch {
                            self.tags = .error(error.localizedDescription)
                        }
                    }
                }
    }
    
    
}

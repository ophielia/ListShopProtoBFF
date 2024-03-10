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
            return AnyView(List(tags) { tag in
                TagRow(tag: tag)
            })
        case .error(let description):
            return AnyView(Text(description).multilineTextAlignment(.center))
        }
    }
}

extension ContentView {

    enum LoadableTags {
        case loading
        case result([BreedsTag])
        case error(String)
    }

    @MainActor
    class ViewModel: ObservableObject {
        let tagUCP : BreedsTagUCP
        @Published var tags = LoadableTags.loading

        init(sdk: SDKHandle) {
            self.tagUCP = sdk.tagUCP
            self.loadTags(forceReload: false)
        }

        func loadTags(forceReload: Bool) {
            Task {
                do {
                    self.tags = .loading
                    let tags = try await tagUCP.getTags(forceReload: forceReload)
                    self.tags = .result(tags)
                } catch {
                    self.tags = .error(error.localizedDescription)
                }
            }
        }
    }
}

extension BreedsTag: Identifiable { }

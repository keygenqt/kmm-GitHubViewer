import SwiftUI
import shared

struct ContentView: View {

	let greet = Greeting().greeting()
	@Environment(\.openURL) var openURL

	var body: some View {
		NavigationView {
			VStack {
				Text(greet)
				Divider().frame(height: 10)
				Text("Kotlin multiplatform mobile")
			}.navigationTitle("SwiftUI")
		}
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
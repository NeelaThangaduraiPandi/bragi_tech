# bragi_tech
The tech assignment consists of creating an app to show the list of movies by genre.

## TMDB - Movies App

### Architecture and Others
* **Clean Architecture** with **Jetpack Compose**
* **Coroutines with Flow** for Reactive Programming
* **Hilt** - Dependency Injection
* **Retrofit** for API calls

### Building the application
* Run Environment (IDE) - **Android Studio Ladybug** with **Version Catalog**
* minSDK version - **24**
* compile and target SDK version - **35**
* kotlin - **2.1.0**
* GITHub Repository
  * https://github.com/NeelaThangaduraiPandi/bragi_tech.git
* Steps
  * Clone the Android project from the above repository
  * Open the project in Android Studio
  * You can Preview screen files under '../presentation/ui/screen'
  * Run the application using connected real device or emulator

### Rooms for Improvements
* Securing the API Key - Could have stored the api_key in **local.properties** with proper encoding
* Pagination - Since "discover/movies" supports pagination, it could have been implemented when the user scrolls to the bottom of the list

### Acceptance Criteria - Check list
* The app should support Android OS 7+ [x]
* Follow Clean Architecture by Robert C. Martin (Uncle Bob) [x]
* Use Coroutines or RxJava [x]
* Use any Dependency Injection [x]
* Use Compose or XML Layouts [x]
* The repository should contain a README file explaining: [x]
  * how to build the app
  * general architecture
  * breakdown of libraries used
* Please provide a public link to git repository in order to review the assignment (we would like to
  see how you organize your work - please don’t do the task in the one commit) [x]
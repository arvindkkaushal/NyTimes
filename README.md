# NyTimes
NY Times app illustrating Android development best practices with Android Jetpack,Kotlin,MVVM & DataBinding.

This app is about NY Times Most Popular Articles, In this app basically you will able to checkout 1,7 and 30 days older articles

Introduction
------------
This project uses the Gradle build system. To build this project, use the gradlew build command or use "Import Project" in Android Studio.
There are two Gradle tasks for testing the project:

There are two Gradle tasks for testing the project:
* `connectedAndroidTest` - for running Espresso on a connected device
* `test` - for running unit tests

Screenshots
-----------
![List of articles](screenshots/nyyimes_one.jpg "A list of articles")
![end of articles](screenshots/nyyimes_two.jpg "end list of articles")


Libraries Used
--------------
* [Foundation][0] - Components for core system capabilities, Kotlin extensions and support for
  multidex and automated testing.
  * [AppCompat][1] - Degrade gracefully on older versions of Android.
  * [Android KTX][2] - Write more concise, idiomatic Kotlin code.
  * [Test][4] - An Android testing framework for unit and runtime UI tests.
  
* [Architecture][10] - A collection of libraries that help you design robust, testable, and
  maintainable apps. Start with classes for managing your UI component lifecycle and handling data
  persistence.
  * [Data Binding][11] - Declaratively bind observable data to UI elements.
  * [LiveData][13] - Build data objects that notify views when the underlying database changes.
  * [Dagger][96] - Automate dependency injection in your app & you don't have to write tedious & error-prone boilerplate code.
  * [ViewModel][17] - Store UI-related data that isn't destroyed on app rotations. Easily schedule
     asynchronous tasks for optimal execution.
     
* [UI][30] - Details on why and how to use UI Components in your apps - together or separate
  * [Layout][35] - Lay out widgets using different algorithms.
* Third party
  * [Rx Java][91] for managing background threads with simplified code and reducing needs for callbacks
  * [Retrofit2][92] for client and server intreaction and callback
  
  
[0]: https://developer.android.com/jetpack/components
[1]: https://developer.android.com/topic/libraries/support-library/packages#v7-appcompat
[2]: https://developer.android.com/kotlin/ktx
[4]: https://developer.android.com/training/testing/
[10]: https://developer.android.com/jetpack/arch/
[11]: https://developer.android.com/topic/libraries/data-binding/
[13]: https://developer.android.com/topic/libraries/architecture/livedata
[17]: https://developer.android.com/topic/libraries/architecture/viewmodel
[30]: https://developer.android.com/guide/topics/ui
[35]: https://developer.android.com/guide/topics/ui/declaring-layout
[91]: http://reactivex.io/intro.html
[92]: https://square.github.io/retrofit/#introduction
[96]: https://developer.android.com/training/dependency-injection/dagger-android

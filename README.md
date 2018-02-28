# Kotlin News Reader - Android App

Rod Bailey
Wednesday 28 Feb 2018

Note: The pictures and links in this document only work when this document is viewed on the Github web site.

# Summary

This is an Android application created as a technical exercise for Android Developers. 
It aims to illustrate an exemplary architecture, the use of some core technologies, and solid
unit testing practice. 
  
The requirements document is in a MarkDown file [here](/doc/task_android.md)

# Building the app

- The app was developed in Android Studio 3.0.1 (stable).
- The code (and this document) is stored in a Git repository at `https://github.com/replicant1/KotlinNewsReader.git`
- The app targets Android Oreo 8.1 (API 27) and will work on Android Ice Cream Sandwich 4.0.3/4 (API 15) upwards

# Screenshots

This is what the app looks like when it has successfully loaded a set of News Assets (each article summary is called 
a "News Asset").

![Screenshot](/doc/kotlin_news_reader_load_complete.png)

# Design Overview

Although this coding exercise is small in scope, I have tried to maintain the same architectural and testing
approaches that I would take for a larger system.

## News Asset List

Here is the design behind the News Asset List - which is the sole screen of the app. It's division into
architectural layers is illustrated.

_Layered architecture diagram goes here - View, Model, Command, Service_

Features of the design:

- The overall app architecture and the structure of individual features follow the `MVP` pattern.
- Views as simple and "dumb" (devoid of logic) as possible.
- As per usual MVP, each layer only communicates with the layer above and below it. View never talks to Model directly, but only via the intermediary of the presenter. Model never talks to View directly, but only via the intermediary of the presenter.
- Runtime configuration properties are set in the file `assets/config.properties`
- Views and Presenters communicate via well defined interfaces e.g. `IScenario1View`, `IScenario1Presenter`.
- Presenters send messages to Models synchronously, via well defined interfaces. 
- Models send messages to Presenters asynchrounsly using Events and an EventBus.

# Libraries

The following third party libraries have been used:

- **GSON** for parsing of JSON
- **Otto** an event bus that the Model uses to notify presenters of a change in data
- **ButterKnife** for injecting Views
- **Android Support Library** for backwards compatibility
- **Espresso** for instrumented unit testing
- **JUnit** for un-instrumented unit testing
- **Android Annotations** - for useful annotations such as `@EActivity`, `@EFragment`, `@AfterViews`, `@ViewById` etc.
- **Timber** - logging facility that, unlike native Android logging, puts the method name in the trace
- **Simple Item Decoration** - for the horizontal line separating news assets, which is implemented as a `Decoration`
- **RxAndroid** and **RxJava** - for the asynchrony of JSON retrieval
- **Glide** - for the asynchronous loading and caching of images

The following commonly used libraries have **not** been used:

- **Retrofit** - Is useful for exposing an API as RxJava Observable's. Omitted because there is only
 one endpoint in this API, so it seemed like overkill.
- **Volley** - Useful for managing HTTP requests. Like Retrofit, it seemed like overkill.
- **Dagger** - On larger projects I have often used Dagger for dependency injection. Typically, one wants
to inject the presenters into their views, amongst other things. I omitted it here due to
the small size of the app.

# Interpretive Issues
There are a few ambiguities in the requirements which have forced me to make some interpretive decisions:

### Selection of thumbnail image
The requirements specify that the app should "display the smallest image available for the asset in the cell". It
is not clear what "smallest" means in this context. Is it according to image area (width * height), or is it in
terms of file size (in bytes)? Further, examination of the news feed shows that the dimensions of some images are often
reported incorrectly as having a width of `0`. For this reason, I hid the definition of "smallest" behind the
interface `IThumbnailSelectionStrategy` and provided my own interpretation in `SimpleThumbnailSelectionStrategy` with
 the intent that if I've got it wrong, alternative implementations of `IThumbnailSelectionStrategy` can be substituted
 in future.

### Viewing the original article
Clicking on a synopsis is meant to take you to another screen that shows the HTML of the article in full.
The requirements state "Tapping a story should present the asset’s URL in a WebView (or any other better alternate)".
Originally I thought to implement this as a screen containing an Android `WebView` component. But then I decided to 
simply launch the default browser app on the story's URL instead. The browser already does a far better job of
displaying HTML, progress monitoring of the load, etc, than what I have time to do in this app. It also seems
more inkeeping with Android philosophy to let another specialist app handle this task. By firing off an Intent,
if there are multiple browsers installed, the user can select their favourite one. This is a power they won't have if
I force them to use my own WebView.

### Device Orientation
The requirements make no mention of how the app should handle changes in device orientation. For simplicity
I decided to lock it to portrait orientation.

# Future Improvement

Had I more time, I would've like to make the following improvements.

- Implement a disk-based caching strategy for the news assets e.g. using files or SQL.
- Use Glide's "placeholder" capability to put a progress monitor over thumbnails while they are loading.
- Offer different layouts beyond a simple list. This is easily implemented by switching the layout managers for the 
`RecyclerView` to, say, a `GridLayoutManager` so that the synopsis' appear in 2 or more columns.
- When there are no news articles in the feed, or the feed can't be parsed, don't just 
display an empty list. Display a placeholder where the list should be, saying "The news feed is currently empty"
or something similar.

# Test Coverage

Current code coverage of the Espresso tests is 85%, as shown in the following report:

![Test Coverage](/doc/espresso-test-coverage.png)

There are a few issues in the "Issue tracker" for this repository that should help get this up to 100% 


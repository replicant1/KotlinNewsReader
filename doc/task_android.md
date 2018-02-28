

## Task

The included API endpoint returns, amongst other data, an array of news stories (assets).
You are tasked with creating a Phone app that consumes the provided API and displays a list of news articles to the user, ideally in a RecycleView or GridView. Tapping a story should present the asset’s URL in a WebView (or any other better alternate)

## Requirements

* The list of articles should display at least the following fields:
-- headline
-- theAbstract
-- byLine

* Display latest article first in the list, use article's 'timeStamp'

* If there are related images available for an asset, display the smallest image available for the asset in the cell.
* Images should be loaded asynchronously and cached

* The style of cells is up to you, with necessary padding and layout.
* UI with activity and fragments where appropriate, but should be adaptable to all Phone screen sizes.

* Comment your code so it can be understood in six months
* A good unit test coverage is expected as part of solution -- instrument/espresso tests are bonus

* Code written in Android Studio 3 (stable) with Kotlin (highly regarded) and/or Java, please specify code compilation notes in your submission.

Please feel free to ask if you have any questions when interpreting this document!

## Submission Notes

* Code Compilation instructions; IDE/Plugin versions expected, dependency management
* Short description explaining architecture and logical modules its comprised of (e.g View, ViewModel...)
* Any 3rd party libraries used and rational
* Explain what each unit test does in comments or in document format
* Any additional features -- apart the requirements given above

* Please either send us solution in zip file or share link to your cloud version control, include above notes in submission.


## How we evaluate

We want you to succeed! We aim to evaluate each submission with the same criteria, they are:

 * *requirements* you've build the right product, attention to details!
 * *code architecture* appropriate design patterns used (MVVM, MVP ...)
 * *code style* idiomatic, safe, clean, concise.
 * *testability* design, dependencies, unit tests.
 * *user experience* responsive, user-centric design.

## Resources

API Endpoint:
https://bruce-v2-mob.fairfaxmedia.com.au/1/coding_test/13ZZQX/full

# Lesson 2 - Activities and intents

Practicals related to [Android Developer Fundamentals (Version 2) - Unit 1: Get started - Lesson 2: Activities and intents](https://google-developer-training.github.io/android-developer-fundamentals-course-concepts-v2/unit-1-get-started/lesson-2-activities-and-intents/2-1-c-activities-and-intents/2-1-c-activities-and-intents.html)

## 2.1 - Activities and intents (twoActivities)

[Related codelab](https://codelabs.developers.google.com/codelabs/android-training-create-an-activity/#0).

Create an activity that launches a second activity.

### Goals
  - Use of `parentActivityName` attribute to indicate parent-child activity relationship (*Up navigation*).
  - Use of `meta-data` to define parent-child relationship for APIs earlier to 16.
  - User of `Bundle` to send data between aplications.

### Learned concepts
  - `android:label` in manifest.xml adds the Activity's title to the app bar.
  - `android:visibility="invisible"`: make initially invisible the view where applied.
  
## Coding challenge (threeButtons)

https://codelabs.developers.google.com/codelabs/android-training-create-an-activity/#7

Create an activity with 3 buttons that start a second activity with a scroll view showing a different text depending on the button clicked.

## Homework (sayHello)

https://codelabs.developers.google.com/codelabs/android-training-create-an-activity/#11

### Issues

When it goes back from second activity to first one, count is resetted to zero.

# 2.2 - Activity lifecycle and state

[Related codelab](https://codelabs.developers.google.com/codelabs/android-training-activity-lifecycle-and-state).

## Task 1 (lifecycleCallbacks)

Goals: observe application lifecycle via `Log` messages.

## Task 2 - Save and restore Activity instance state (instanceState)

Goals: saving instance state to avoid losing data when activity destroyed and recreated (i.e. when rotating the device).

Source code: *lifecycleCallbacks*.

### Key points

  - `Bundle` `Activity` *instance state*.
  - `onSaveInstanceState()` method (called between `onPause()` and `onStop()`.
  - State information of some of the `View` elements automatically saved accross configuration changes (i.e. `EditText`).
  - It is possible to restore the state either in `onCreate` or in `onRestoreInstanceState()`.
    - Most of the time the better place is `onCreate()`, but sometimes it is convenient to do it in `onRestoreInstanceState()`.
  - When you go back from second activity to first one with back button, first activity is resumed and you preserve the data.
  - When you do the same with the up navigation arrow, the first activity is destroyed and re created, so you lose all of the data.

## Coding challenge - Shopping-list app (shoppingList)

https://codelabs.developers.google.com/codelabs/android-training-activity-lifecycle-and-state/#4

### Key points
  - Use constants as key to pass information in the intents.
  - Arrange items.
  - Include landscape variation in both activities.
  - Visibility of empty `TextViews`.
  - State saving to avoid lose information when rotate.
    - When you use up navigation, onCreate receives null savedInstanceState bundle.
    
## Homework - CounterHomework (counterHomework)

https://codelabs.developers.google.com/codelabs/android-training-activity-lifecycle-and-state/#8

Make an app with a counter that preserves its value when device rotated.

# 2.3 - Implicit intents

[Related codelab](https://codelabs.developers.google.com/codelabs/android-training-activity-with-implicit-intent/#0).

## Tasks 1-4 - Sending implicit intents (implicitIntents)

Building and app that send implicit intents to perform:
  - Open URL in a web browser.
  - Open map location.
  - Share text.

## Task 5 - Receiving an implicit intent (implicitIntentsReceiver)
  
Creating a simple intent-receiver that accepts implicit intents for a specific action.

It only accepts 'http' protocol with developer.android.com address.

Run both *implicitIntents* and *implicitIntentsReceiver* to test the latter.

## Coding challenge (shoppingListMap)

Add an EditText and a Button to the Shopping-List app to locate a store on a map.

## Homework (implicitIntentsCamera)

Adding a "take picture" button to the Implicit Intents app that will open the camera app to take a picture, without returning that picture.

### Key points

  - Restrict visibility on Google Play to devices with camera: advertise that tagging manifest:
  
    `<uses-feature android:name="android.hardware.camera"
                       android:required="true" />`
  - Action for the Intent: `MediaStore.ACTION_IMAGE_CAPTURE`
  - If required doing something with the picture (not the case), `startActivityForResult`
  
### Possible future homework
  - Doing something with the picture.                         
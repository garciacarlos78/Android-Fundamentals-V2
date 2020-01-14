# Unit 4 - Saving User Data

# Lesson 9 - Preferences and settings

Codelabs from *Android Developer Fundamentals (Version 2)* - Unit 4: Saving User Data - Lesson 9: Preferences and settings

## 9.1 - Shared preferences

Codelab from https://codelabs.developers.google.com/codelabs/android-training-shared-preferences

### 9.1.1 - Tasks (helloSharedPrefs)

Full project downloaded from https://github.com/google-developer-training/android-fundamentals-starter-apps-v2/tree/master/HelloSharedPrefs.

This project is the base project to which are applied tasks from the  codelab.

Updated Android Gradle plugin from version 3.1.2 to version 3.5.3 and Gradle to version 5.4.1.

### 9.1.2 - Coding challenge (sharedPrefsCodingChallenge)

**Challenge:** Modify the HelloSharedPrefs app so that instead of automatically saving the state to the preferences file, add a second activity to change, reset, and save those preferences. Add a button to the app named Settings to launch that activity. Include toggle buttons and spinners to modify the preferences, and Save and Reset buttons for saving and clearing the preferences.

The coding challenge uses as a base module the original *HelloSharedPrefs* app, in which SharedPreferences are not implemented and the state is saved via instance states.

Steps:
  1. Design the second Activity (blueprint).
  2. Create the new Activity and apply the pattern designed (xml file).
  3. Add Settings button to the main activity. It must launch the new activity passing in the current score and color
  4. Check the received data in the new activity and put it on the UI (including spinner)
  5. Check the saved preferences in the new activity and put it on the UI
  6. Implement *Save Changes Button*. It must save the current count and the selected color in the spinner to the SharedPreferences, and also update the UI to reflect the new saved preferences.
  7. Implement *Reset* button. It must empty SharedPreferences.
  8. Create an UI for horizontal device
  
  
Key points:
  - It is not possible to use a switch statement to check the color if you want that part of code to be independent from the xml definition.
    For using a switch statement, you need static values to compare with. Imagine you use static values here; if you change one of the colors of the buttons for the background, this switch statement will fall into default case. If you don't want to have to remember to change this part of code if you change the color of the buttons, you have to check the buttons colors inside the code.

### 9.1.3 - Homework (sharedPrefsHomework)

Base module: *scoreKeeperChallenge* (https://github.com/garciacarlos78/Android-Fundamentals-V2/tree/master/lesson5/scoreKeeperChallenge)

Aim: replace saved instance state with shared preferences. Add a **Reset** button that resets the score values to 0 and clears shared preferences.

Steps:
  1. Initialize preferences.
  2. Save preferences in onPause(). This will substitute `onSaveInstanceState()` method, we no longer save state but SharedPreferences.
  3. Restore preferences in `onStart()`. Remove instance state recovery from `onCreate()`.
  4. Modify UI to add *Reset* button.
  5. Implement method to reset the scores.
  
Key points:
  - The recovery of the SharedPreferences data and show in the UI is moved from `onCreate()` to `onStart()`. This way when preferences are reset with the reset button you can call `onStart()` to update the UI.
  - The saving of the SharedPreferences is done in `onPause()`. Doing it this way, during the normal app lifecycle the preferences are always saved. But, if you stop the app directly from Android Studio you lose the data (the app doesn't goes through the `onPause()` state).   
                                   
## 9.2 - App settings

Codelab from https://codelabs.developers.google.com/codelabs/android-training-adding-settings-to-app

### 9.2.1 - Task 1 - Add a switch setting to an app (appWithSettings)

Task 1: https://codelabs.developers.google.com/codelabs/android-training-adding-settings-to-app/#2.

Key points:
  - Codelab suggested implementation: 'com.android.support:preference-v7:26.1.0'
    Implementation needed using AndroidX: 'androidx.preference:preference:1.1.0'
  - Best practice: use a regular *Activity* that hosts a `PreferenceFragment`. 
  -`SettingsFragment` will be added to the existing `SettingsActivity`, making it easy to add or remove a `Fragment` while the `Activity` is running.                                    
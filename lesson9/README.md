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
  
### 9.2.2 - Task 2 - Use the Settings Activity template (droidCafeWithSettings)

Task 2: https://codelabs.developers.google.com/codelabs/android-training-adding-settings-to-app/#3.

Base module: https://github.com/google-developer-training/android-fundamentals-apps-v2/tree/master/DroidCafeOptionsUp.

Migrated to AndroidX and tested.

The current Settings Activity template does not match the template shown in the task, but it's still possible to do the task.

Instead of changing the "Data & sync" settings, I will add a new "Account" settings.

**Note:** followed the steps as indicated in the task, when the app runs on a tablet emulator it does not take advantage of the extra space, the preferences are shown as in a phone. Must be investigated.

Steps:
  1. Create a new preferences XML resource (*account_preferences.xml*).
  2. Add the corresponding string arrays in *arrays.xml*, for the text to show and for the values (*market_entries* and *market_values*).
  3. Create a ListPreference item in the *account_preferences.xml* resource.
  4. Create an icon (New --> Image Asset --> Action bar and tab icons)
  5. Create *AccountFragment* class in *SettingsActivity* extending `PreferenceFragmentCompat`.
  6. Add new preference title in strings.xml.
  7. Add new *Preference* in *header_preferences.xml*, using the new fragment, icon and title, and adding a key.
  8. Add code in MainActivity to set default values for the settings. Add missing default values in XML preferences files.
  9. Add code to read values for the settings and to show a Toast in the MainActivity.
  
Key points:
  - Key strings shouldn't be extracted to string resources, it's better to use them as hard-coded text. They might be translated along with other strings, which might cause the app to crash.
  
### 9.2.3 - Coding challenge

It is not possible to do this coding challenge, the app's behaviour is not the expected when you add a Settings Activity. I might have changed since the creation of the codelab (the menu does not change when you use a tablet, the behaviour is the same as in a phone).

### 9.2.4 - Homework (droidCafeWithSettings)

Instructions: https://codelabs.developers.google.com/codelabs/android-training-adding-settings-to-app/#9

Homework is done in the same app as Task2 (it's a new setting).

Add a new **General** settings screen. The new preferences is supposed to be added to the *General* settings screen, but it does not exist, so it's necessary to create it first.

Steps:
  1. Add a new xml resource to allocate the *General* preference (*general_preferences.xml*).
  2. Create a new icon for the new setting. Add also a string resource to use as title.
  3. Add new array resources for the delivery methods in *arrays.xml*.
    **IMPORTANT:** There are two arrays needed when using `ListPreference`, one for the entries and one for the values. Anyway, it is possible to use the same array for both values in the XML file.
  4. Add a new `ListPreference` item in *general_preferences.xml*, using the just created arrays as `android:entries` and `android:entryValues` attributes.
  5. Create `GeneralFragment`, an inner class in `SettingsActivity` class extending `PreferenceFragmentCompat`.
  6. Add the new settings title in strings.xml (*General*).
  7. Add the new *General* preference in *header_preferences.xml*, using the new fragment, icon and title, and adding a key.
  8. Add code in MainActivity to set default values for settings. 
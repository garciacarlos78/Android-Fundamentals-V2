# Unit 2 - User experience

# Lesson 6 - Testing yout UI (UITesting)

Codelabs from *Android Developer Fundamentals (Version 2)* - Unit 2 (User experience) - Lesson 6

## 6.1 - Espresso for UI testing

Codelab from https://codelabs.developers.google.com/codelabs/android-training-espresso-for-ui-testing.

### 6.1.1 - Tasks 1 and 2 (UITesting)

Source project: *TwoActivities* project from https://github.com/google-developer-training/android-fundamentals-apps-v2/tree/master/TwoActivities, renamed to 'Lesson 6 - Testing your UI', and *app* module renamed to *UITesting*.

Task 1 - Set up Espresso:
  1. Check for the Android Support Repository
    In *Android Studio 3.5.2* the option provided in the codelab does not exist (**Android Support Repository** in **Tools > Android > SDK Manager > SDK Tools > Support Repository**).
    I continue the task steps.
  2. Configure Espresso
    In **build.gradle (Module: UITesting)** `dependencies` section, the following is already included:
      `testImplementation 'junit:junit:4.12'
       androidTestImplementation 'com.android.support.test:runner:1.0.1'
       androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.1'`
     There are warnings warning about new versions of the two last dependencies. I don't update in case there were incompatibilities with the codelab.
   3. Turn off animations on test device.
     - Turn on USB Debugging
     - Look for **Drawing** section in **Developer Options** and turn off window and transition animation scale, and animator duration scale.
       All of them were set to *Animation scale 1x*.
       
Task 2 - Test for Activity switching and text input
  1. Run the example test (**Project > Android > java > FQDN app (androidTest) > ExampleInstrumentedTest --> right-click > Run**).
  2. Define a class for a test and set up the Activity. Editing the example test rather than adding a new one.
    - Rename class to `ActivityInputOutputTest`
    - Add the following to the top of the class:
      `@Rule
       public ActivityTestRule mActivityRule = new ActivityTestRule<>(MainActivity.class);`
   3. Test switching from one Activity to another. Follow the steps in the codelab.
   4. Test text input and output. Follow the steps in the codelab.
   5. Introduce an error to show a test failing. Follow the steps in the codelab.
   
### 6.1.2 - Task 3 - Test the display of spinner selections (spinnerEspressoTest)

Source: *app* module from https://github.com/google-developer-training/android-fundamentals-apps-v2/tree/master/PhoneNumberSpinnerEspresso, added to current project renamed as *spinnerEspressoTest*.
   
             
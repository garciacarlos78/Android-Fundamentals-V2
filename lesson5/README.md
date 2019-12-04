# Unit 2 - User experience

# Lesson 5 - Delighful user experience

Codelabs from *Android Developer Fundamentals (Version 2)* - Unit 2 (User experience) - Lesson 5

## 5.1 - Drawables, styles, and themes (scoreKeeper)

Codelab from https://codelabs.developers.google.com/codelabs/android-training-drawables-styles-and-themes/#0.

Create an app to maintain a score for two teams, with buttons for increasing and decreasing the score of each team.

 ### Key points
   - ShapeDrawable.
   - Create style and apply them to multiple `View` elements and layouts.
   - Style inheritance:
     - Child style inherints all of the parent's attributes
     - Style attributes defined in both parent and child uses child's definition (that's, child overrides parent)
     - Child style can include additional attributes that the parent doesn't define
   - Theme: style that's applied to an entire `Activity` or app.
   - Theme for night: `DayNight` theme.

### 5.1.1 - Coding challenge (scoreKeeperChallenge)

Coding challenge from https://codelabs.developers.google.com/codelabs/android-training-drawables-styles-and-themes/#7.

  "For this challenge problem, create a Drawable resource that changes the background of the ImageButton to the same color as the border when the state of the ImageButton is "pressed". You should also set the color of the text inside the ImageButton elements to a selector that makes it white when the button is "pressed"."
  
#### Steps

To change the background of the `ImageButton`:
  1. Create the drawable resource for the pressed button.
    In this case, modify the existing *button_background.xml* to include a `selector`, convert the current `shape` in an item, and add the item for the pressed state. With these steps the drawable resource has been converted into a State list, with the drawable resources defined inside.
  2. Create the XML file with the selector.
  3. Apply the just created XML file as the button background.
  
To change the color of the text inside the `ImagenButton`:
  1. Delete the attribute `android:tint` from the `ScoreButtons` style.
  2. Add the attribute `android:tint` to the `ic_minus.xml` vector drawable with the color to be shown when not pressed.
  3. Add a new vector drawable, `ic_minus_pressed.xml`, copying the content of the latter and changing the `android:tint` to white.
  4. Add a new State list, `ic_state_minus`,  using `ic_minus_pressed` drawable resource for `android:state_pressed` attribute and `ic_minus` as default.
  5. Change `MinusButtons` style to use the new State list as `android:src`.
  6. Do the same with `PlusButtons`.
  
#### Key points
  - It would be more polite to have the background `State List` with references to drawable resources for each state, instead of having them defined in the same list.
  
### 5.1.2 - Homework (drawableTest)

Create an app with a battery icon, a minus and a plus button. Each click on plus button fills the battery, and each click on minus button empties it.

#### Steps
  1. Create the different drawables (7 batteries each one representing one charge level).
  2. Create a level list drawable with the battery icon.
  3. Add the drawable and the buttons to the view.
  4. Add the behaviour to the button clicks.     
  
#### Key points
  - According to https://google-developer-training.github.io/android-developer-fundamentals-course-concepts-v2/unit-2-user-experience/lesson-5-delightful-user-experience/5-1-c-drawables-styles-and-themes/5-1-c-drawables-styles-and-themes.html#drawables, the instruction to set a level is `setLevel(level)`.
    In the practical, the instruction seems to be `setImageLevel(level)`.

## 5.2 - Cards and colors

Codelab from https://codelabs.developers.google.com/codelabs/android-training-cards-and-colors.

### 5.2.1 - Tasks (MaterialMe-Starter/app)

Task description:
The MaterialMe app is a mock sports-news app with very poor design implementation. You will fix it up to meet the design guidelines to create a delightful user experience!

Source app (before aplying tasks' instructions): https://github.com/google-developer-training/android-fundamentals-starter-apps-v2/tree/master/MaterialMe-Starter

Considerations:
  - In this case, `MaterialMe-Starter` is not a module of the `Lesson 5` project, it's a project itself.
  
#### Key points
  - String array initialization from xml file:
    - Java code: 
      `String[] sportsList = getResources()
                                 .getStringArray(R.array.sports_titles);`
    - strings.xml:
      `    <string-array name="sports_titles">
               <item>Baseball</item>
               ...
               ...
               <item>Tennis</item>
           </string-array>`
  - Use of *Glide* library to load large images efficiently.
  - `TypedArray`. Allows to store an array of other XML resources.
    - Clean up data after using it (to free up memory): `myTypedArray.recycle()`
  - `ItemTouchHelper` class. Used to define what happens to `RecyclerView` list items on touch actions (swipe, drag'n'drop...).
    - Common use cases already implemented in `ItemTouchHelper.SimpleCallback`.

#### TO DO
  - Persistence. When, for example, rotating the device, the data is reset. Avoid this using `onSaveInstanceState`, database or SharedPreferences.
  
### 5.2.2 - Coding challenge 1 (MaterialMe-Starter/codingChallenge1)

  - Add real details to the Sport object and pass the details to the DetailActivity.
  - Implement a way to ensure that the state of the app is persistent across orientation changes.

#### Key points
  - Persistence added using `onSaveInstanceState`
  
#### Steps

Adding real details:

  1. Create a string-array with the details in `res/values/strings.xml`.
    Don't forget escape apostrophes (\').
  2. Add a member variable in Sport class to allocate the description. Add it to the contructor and add the getter method.
  3. Modify MainActivity.initializeData to include these descriptions.
  4. Add the sport description to the intent in SportsAdapter.ViewHolder.onClick().
  5. Catch the new data from the intent in DetailActivity.java and set to the TextView.
    
Persistence:
  1. Think about things to save: must save the current state of the list, that is, order and deleted items. We have all of this in member variable `mSportsData`.
  2. Implement method `MainActivity.onSaveInstance(Bundle outState)`, saving in the `Bundle` the member `mSportsData`.
  3. Check in `MainActivity.onCreate(Bundle savedInstanceState)` if the input parameter bundle is not null, and recover the data from there (instead of initialize).
  
Main difficulties:
  - `Sport` class must implement `Parcelable` so that you can `putParcelableArrayList` into the bundle. This process is almost authomatic with Android Studio, just write `implements Parcelable` and solve automatically the errors with ctrl-enter.
  - You cannot simply check if the bundle is not null and then assign the content of the bundle to the member variable and notify the adapter the data changed. The problem seems to be that you're changing the object after having added it to the adapter.       
  
### 5.2.3 - Coding challenge 2 (transitionsAndAnimations -- NOT FINISHED)

Coding challenge 2 from https://codelabs.developers.google.com/codelabs/android-training-cards-and-colors/#7.

I tried to change the simple behaviour proposed when touching the android icon (start to a secondary activity, changing the android position with another icon with a transition, always the same change), by a more complex behaviour: randomly change all of the icons' positions, executing a transition.

The app currently do what is supposed to do except when touching the android icon. It changes randomly the icons' order but without a transition.

#### Steps
  1. Change the minimun SDK from 15 to 21, in order to implement shared element transitions.
    Alternative: check version where transitions are used:
      // Check if we're running on Android 5.0 or higher
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
          // Apply activity transition
      } else {
          // Swap without transition
      }
  2. Create the drawables (3 shapes and an icon).
  3. Add the `ImageButtons` to a `GridLayout`.
  4. Assign the `onClick` actions to the buttons and define them in `MainActivity`.
  5. Check the correctness with dummy code (a different `Toast` for each `onClick` method).
  6. Implement the final code of each `onClick` method.
  
Square shape: relaunches activity using Explode animation for both enter and exit transitions:
  1. Add the following item to the style:
    `<item name="android:windowActivityTransitions">true</item>`
  2. In the corresponding `onClick` method:
    - Create the intent to relaunch the same activity.
    - Indicate in the intent the type of transition (*Explosion* in this case).
    - Start an exit transition with explosion: `getWindow().setExitTransition(new Explode());`
    - Relaunch the activity with *transition animation*: `startActivity(intent, ActivityOptions.makeSceneTransitionAnimation((Activity)this).toBundle());`
  3. In `MainActivity.onCreate()` method:
    - Check if we have an extra in the intent with the type used to indicate the transition (a class String constant named *TRANSITION_TYPE*).
    - If we have it, check which kind of transition is.
    - If is *Explosion* type, set the enter transition with the following instruction: `getWindow().setEnterTransition(new Explode());`
    
Ring shape: same as square, but using *Fade* transition.
  - Same steps as with square, just changing `Explode()` by `Fade()`.

Line shape: starts an in-place animation (a rotation):
  1. In the corresponding `onClick` method, create an `ObjectAnimator` object, set its parameters, and start it.          

Android icon: starts a secondary activity with a Shared Element Transition swapping the Android block with one of the other blocks.
  Instead of doing this, I do the following:
    - When Android block is pressed, the second activity is launched rearranging the 4 buttons randomly.
    - In order to implement the transition, I will add a new ImageView and I will swap the `GridLayout` containing the four blocs with this `ImageView`.
    
  1. Create a seconday activity, copying the XML from the main activity. Copy also the `MainActivity.java` code (for the moment, it will do the same).
  2. Implement `androidOnClick` on both activities, just launching the other one for the moment, and check if everything works.
  3. Create a method to randomly change the position. I use a member field, an `ArrayList<String>` which determines the order of the icons. Using `Collections.shuffle(ArrayList<>)` I randomly rearrange the icons.
  4. Once checked that the previous steps work right, I add the `ImageView` and implement the transition.
    
  
#### TO DO
  - Make the line shape adjust `ImageButton` in width.
  - `MainActivity.java` and `SecondaryActivity.java` use the same methods to manage the clicks on the image views. Maybe it could be possible to have the methods in another class and then use them from both activities (instead of having them declared twice, one per activity class).      

### 5.2.4 - Homework (MaterialMe-Starter/homework)

Base module: MaterialMe-Starter/codingChallenge1

Homework from codelabs.developers.google.com/codelabs/android-training-cards-and-colors/#11

#### Steps
  1. Specify shared element transitions in *styles.xml*
  2. Create transition resource file
  3. Change the current `onClick` method to include tha transition.
    - Create a public constant string in detail activity used to pair the image
    - Pair the clicked image with the image in the detail activity
      `ActivityOptionsCompat activityOptionsCompat = 
      ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) mContext, 
      new Pair<>(view.findViewById(R.id.sportsImage), DetailActivity.VIEW_NAME_HEADER_IMAGE));`
    - Start the activity providing the activity options as a bundle
  4. In the `onCreate` method of `DetailActivity`, pair the image with the constant string defined above.
    `ViewCompat.setTransitionName(sportsImage, VIEW_NAME_HEADER_IMAGE);`
  5. \[Optional] Do the same with title and subtitle.
  
#### Important things

Include `<changeBounds />` in the `<transitioSet>` block in the transition resource.
According to the sample, "*changeBounds is used for the TextViews which are shared*", but in the homework I could observe that without that option the transition was nos smooth. It became smooth just after include it in the transition resource file.
To make sure of this:
  - I eliminated the transitions in the TextViews and shared just the image using that option. The transition was smooth.
  - I added again the TextViews and let that option. The transition still was smooth.
  - I deleted again the *changeBounds* option. The transition became again rough.
  
The first time the image is loaded in the secondary activity the transition is quite less smootlhy than following times. In the *Google* sample (https://github.com/android/animation/tree/master/ActivitySceneTransitionBasic), they use *Picasso* instead of *Glide* and they load first the thumbnail and then the full image. Probably doing it that way would improve the first time transition.

#### TO DO
Try to make the image load as in the Google example, loading first a thumbnail and then the full image.

## 5.3 - Adaptive Layouts (AdaptiveLayout)

Codelab from https://codelabs.developers.google.com/codelabs/android-training-adaptive-layouts.

Tasks, coding challenges and homework as modules of project *AdaptiveLayout*.

Base module: *MaterialMe-Starter/homework*.

### 5.3.1 - Task 1 (AdaptiveLayout/task1)

Base module: *MaterialMe-Starter/homework*.

Task description: support landscape orientation. When in landscape, the app will show the sports in two columns instead of a large column.

### 5.3.2 - Task 2 (AdaptiveLayout/task2)

Base module: *MaterialMe-Starter/task1*.

Task description: add additional resource qualifiers to change the appearence of the app when used on tablets.

Column count will be 2 for the tablet in portrait orientation and 3 in landscape orientation.

Also created and applied styles to change text size.

### 5.3.3 - Task 3 (AdaptiveLayout/task3)

Base module: *AdaptiveLayout/task2*.

Task description: localize the app. Add resources for different locales.

The word 'Soccer' will be 'Football' in any locale but English - United States.

### 5.3.4 - Coding challenge 1 (AdaptiveLayout/codingChallenges)

Base module: *AdaptiveLayout/task3*.

Challenge description: research for countries where *soccer* is used instead of *football* and add localized string resources for them.

Countries list (not exhaustive): Australia, New Zealand, South Africa, Philippines, Singapore.

Interesting things:
  - It's not necessary to duplicate all of the string resources, just the changed ones. In this case, it's enough to copy the string-arrays *sports_titles* and *sports-info* (the two ones that have the *soccer* word).
    - For the not duplicated elements, the app uses the default locale strings.
  - In fact, it can be better to duplicate only the required strings. For example, if you copy all of the strings and you change the app name, you must remember to change it in all of the string resources.

### 5.3.5 - Coding challenge 2 (AdaptiveLayout/codingChallenges)

Base module: in this case, it will be a continuation of *Coding challenge 1*, so the same module *codingChallenges* will implement both challenges.

Challenge description: translate all of the strings into a different language.

Language selected: spanish (Spain).
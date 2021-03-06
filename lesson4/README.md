# Unit 2 - User experience
# Lesson 4: User interaction

Practicals related to [Android Developer Fundamentals (Version 2) - Unit 2: User experience - Lesson 4: User interaction](https://google-developer-training.github.io/android-developer-fundamentals-course-concepts-v2/unit-2-user-experience/lesson-4-user-interaction/4-1-c-buttons-and-clickable-images/4-1-c-buttons-and-clickable-images.html)

## 4.1 - Buttons and clickable images (droidCafe) 

Codelab from https://codelabs.developers.google.com/codelabs/android-training-clickable-images

### Changes from instructions

  - You don't have a `public void onClick(View view)` method, instead, you have `fab.setOnClickListener(new View.OnClickListener() {...});`, so, you have to change this and not the indicated method.
  
  - You have to change the following activity_main.xml line:
    `app:srcCompat="@android:drawable/ic_dialog_email"`
    for: `app:srcCompat="@drawable/ic_shopping_cart"`
    but this produces the next error, although it works:
    
        To use VectorDrawableCompat, you need to set 
        android.defaultConfig.vectorDrawables.useSupportLibrary = true.  
    
        To use VectorDrawableCompat, you need to make two modifications to your project. First, set 
        android.defaultConfig.vectorDrawables.useSupportLibrary = true in your build.gradle file, and second,
        use app:srcCompat instead of android:src to refer to vector drawables.  
    
        Issue id: VectorDrawableCompat 
        http://chris.banes.me/2016/02/25/appcompat-vector/#enabling-the-flag
    
    Following the instructions of the link above, you just must add the following line to `build.gradle (Module: droidCafe)` (inside `android { default config {`):
    
        vectorDrawables.useSupportLibrary = true
      
      - There are no clear instructions about changing the icon in the codelab.
      

### TODO

  - Read [How to Write Doc Comments for the Javadoc Tool](http://www.oracle.com/technetwork/articles/java/index-137868.html)
  
  - When you order a dessert and you change the phone orientation, you lose the order. In the *Homework* version this will be solved (saving state).
  
### HomeWork (droidCafeHomework)

Added horizontal orientation layout and saving state.  

## 4.2 - Input controls (droidCafeInput)

Codelab from https://codelabs.developers.google.com/codelabs/android-training-input-controls.

### Problems

  - When you go through input fields (*EditText*) with tab key, the order is 1-2-4-3, I mean, from second field it jumps to last and from that to third.
    - Solution: add the following attribute in XML layout to EditText "2": `android:nextFocusForward="@id/phone_text"`, being `@id/phone_text` the EditText that I want to be third in order, that is, "3" in the sequence.
    
### Coding Challenge (droidCafeChallenge)

Make a radio button a default button, that is, mark it as selected when the activity is first opened.

Solution: include the following line in the XML layout, in the button we want to be de default one: `android:checked="true"`

Included in this module Task 3 of codelab (*Use a spinner for user choices*).

#### TO DO

Save state to avoid losing information when rotate the device.
Create a landscape layout.

### Coding Challenge 2 (droidCafeChallenge2)

Write code to perform an action directly from the keyboard by tapping a Send key, dialing a phone number.

Instead of creating a new project for this, I use Droid Cafe project, applying to the phone field in the Order Activity.

### Homework (checkboxesAndEtc)

Create an app with five checkboxes and a *Show Toast* button.
When you press the button, a toast appears showing which checkboxes are selected.
     
## 4.3 - Menus and pickers

Codelab from https://codelabs.developers.google.com/codelabs/android-training-menus-and-pickers.

### Task 1 - Add items to the options menu (droidCafeOptionsT1)

Task developed using *droidCafeChallenge* as a source module.

#### Interesting key point

  - Using `NoActionBar` themes prevents the app from using native `ActionBar` class to provide app bar.
    - We must use these themes if we plan to use `AppBarLayout` to define the app bar.
       
### Tasks 2-3 - Add icons for menu items - Handle selected menu item (droidCafeOptionsT2)

Task developed using *droidCafeOptionsT1* as a source module.

### Coding challenge (contextMenuScrollingText)

Adding a *context menu* to the *Scrolling Text* app.

*Scrolling Text* source: https://github.com/garciacarlos78/Android-Fundamentals-V2/tree/master/scrollingtext.

### Task 4 - Use a dialog to request a user's choice (dialogForAlert)

Create a *Button* to trigger a standard alert dialog.

#### Concepts

  - *Builder* design pattern. Static member class of the class it builds.
  - Create the dialog in `onCreate()` method so it is always available to trigger it (in this example is in the `onClickshowalert` method).

### Task 5 - Use a picker for user input (pickerForDate)

Create a Date Picker.

Changes from codelab:
  - Spanish date format (dd/mm/yyyy), not U.S.   

### Coding challenge 2 (pickerForTime)

Create a Time Picker.        

### Homework (droidCafeOptionsDate)

Add a Date button to DroidCafeOptions app to show a date picker and show the chosen date in a Toast.

Source module: droidCafeOptionsT2.

## 4.4 - User navigation

Codelab from https://codelabs.developers.google.com/codelabs/android-training-provide-user-navigation.

### Key points
  - Tabs appropriate for four or fewer sibling screens.
  - Standard adapters for using fragments with `ViewPager`:
    - `FragmentPagerAdapter`. Navigating between sibling screens representing a fixed, small number of screens.
    - `FragmentStatePagerAdapter`. Paging across a collection of undetermined number of screens. It destroys each `Fragment` as the user navigates to other screens, minimizing memory usage.


### Task 1 - Add an Up button for ancestral navigation (droidCafeNavigation)

Add an up button to source module *droidCafeOptionsDate*.

### Task 2 - Use tab navigation with swipe views (tabExperiment)

New module to experiment with tab navigation and swipe views.

#### Changes from codelab
  - Using *AndroidX* instead of *Android support library*.
    - Toolbar: androidx.appcompat.widget.Toolbar
    - ViewPager: androidx.viewpager.widget.ViewPager    
    - TabLayout: com.google.android.material.tabs.TabLayout
      To be able to use `TabLayout`view, I've had to add it via XML design tab, getting the following message:
      
      This operation requires the library com.google.android.material:material:+.      
      Problem: Inconsistencies in the existing project dependencies found.
      Version incompatibility between:
       - androidx.appcompat:appcompat:1.1.0@aar
         and:
       - androidx.appcompat:appcompat:1.1.0@aar       
      With the dependency:
       - androidx.annotation:*:1.1.0
       versus:
       - androidx.annotation:*:2.0.0      
      The project may not compile after adding this library.
      Would you like to add it anyway?
      
      After having added it, the project has compiled.

 - `FragmentPagerAdapter(FragmentManager fm)` is deprecated. Used FragmentPagerAdapter(FragmentManager, int) with BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT as int.

### Coding challenge - Navigation drawer (navDrawerChallenge)

New module from scratch, created from *Empty Activity* template (not *Navigation Drawer template*), following the instructions from https://google-developer-training.github.io/android-developer-fundamentals-course-concepts-v2/unit-2-user-experience/lesson-4-user-interaction/4-4-c-user-navigation/4-4-c-user-navigation.html#descendant.

Added a Navigation drawer activity from template. From that moment on, many needed and not defined resources became available (@dimen/activity_vertical_margin, @drawable/side_nav_bar...)

#### Changes from documentation

  - `NavigationDrawer` added via XML design tab, with the following warning:
  
    This operation requires the library com.google.android.material:material:+.
    
    Accepted, and added as `com.google.android.material.navigation.NavigationView`


#### Key points
  - Best practice: provide navigation to all of the child screens in the app.
  - Use `NoActionBar` theme: `android:theme="@style/AppTheme.NoActionBar"`
  
#### TO DO
  - Read [Navigation Drawer](https://developer.android.com/design/patterns/navigation-drawer.html) design guide.  
  
### Homework (threeChildren)

Homework from codelab 4.4 - User navigation.

## 4.5 - RecyclerView (recyclerView)

Codelab from https://codelabs.developers.google.com/codelabs/android-training-create-recycler-view.

Create an app with a RecyclerView showing a list of words, make this list interactive, making the clicked word to change, and add behavior to the FAB, adding a new word when clicked.

### Key points
  - Extracting styles.
  
### Coding challenge 1 (recyclerViewChallenge1)

Change the `Settings` menu option to `Reset`, and make it return the list of words to its original state.

### Coding challenge 2 (recyclerViewChallenge2)

We have a click listener for item in the list. Easy but performance hurting.

Challenge: change it to a more efficient solution.

#### Coding challenge 2 - Version 1 (recyclerViewChallenge2v1)

Proposal: set an unique click listener to the adapter.

Source: https://youtu.be/ZBEtt2rNS6M.

##### Key points
  - Make `WordListAdapter` implements `View.onClickListener` (instead of `WordViewHolder`, which implemented it before).
  - The implementation of the listener is done in `MainActivity`, and then setted to the private listener of `WordListAdapter`via the public method `WordListAdapter.setOnClickListener(View.OnClickListener listener)`.
  - You must implement it in `MainActivity` because the listener needs to access private attributes of the class (`mRecyclerview` and `mAdapter`).
  - As `WordListAdapter` implements the listener, you must implement the method `public void onClick(View v)`. In this case, you simply call the private listener `mListener.onClick(View)` method, which was setted in previuos step.  

#### Coding challenge 2 - Version 2 (recyclerViewChallenge2v2)
  - `WordListAdapter` doesn't implement `View.onClickListener`, it just has it as a private attribute.
  - This private attribute is setted in the constructor method.
  - The implementation will be the same as in version 1, but setted in the constructor call.

#### Homework (recyclerViewHomework)

RecyclerView codelab homework (https://codelabs.developers.google.com/codelabs/android-training-create-recycler-view/index.html?index=..%2F..%2Fandroid-training#10)  
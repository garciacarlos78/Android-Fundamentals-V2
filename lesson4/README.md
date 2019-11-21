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
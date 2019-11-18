# Unit 2 - User experience
# Lesson 4: User interaction

Practicals related to [Android Developer Fundamentals (Version 2) - Unit 2: User experience - Lesson 4: User interaction](https://google-developer-training.github.io/android-developer-fundamentals-course-concepts-v2/unit-2-user-experience/lesson-4-user-interaction/4-1-c-buttons-and-clickable-images/4-1-c-buttons-and-clickable-images.html)

## 4.1 - Buttons and clickable images (DroidCafe) 

Codelab from https://codelabs.developers.google.com/codelabs/android-training-clickable-images

### Changes from instructions

  - You don't have a `public void onClick(View view)` method, instead, you have `fab.setOnClickListener(new View.OnClickListener() {...});`, so, you have to change this and not the indicated method.
  
  - You have to change the following activity_main.xml line:
    `app:srcCompat="@android:drawable/ic_dialog_email"`
    for: `app:srcCompat="@drawable/ic_shopping_cart"`
    but this produces the next error, although it works:
    
    To use VectorDrawableCompat, you need to set android.defaultConfig.vectorDrawables.useSupportLibrary = true.  
    
    To use VectorDrawableCompat, you need to make two modifications to your project. First, set android.defaultConfig.vectorDrawables.useSupportLibrary = true in your build.gradle file, and second, use app:srcCompat instead of android:src to refer to vector drawables.  
    
    Issue id: VectorDrawableCompat 
    http://chris.banes.me/2016/02/25/appcompat-vector/#enabling-the-flag
    
    Following the instructions of the link above, you just must add the following line to `build.gradle (Module: app` (inside `android { default config {`:
      `vectorDrawables.useSupportLibrary = true  
`     
      - There are no clear instructions about changing the icon in the codelab.
      

### TODO

  - Read [How to Write Doc Comments for the Javadoc Tool](http://www.oracle.com/technetwork/articles/java/index-137868.html)
  
  - When you order a dessert and you change the phone orientation, you lose the order. In the *Homework* version this will be solved (saving state).
  
### HomeWork (droidCafeHomework)

Added horizontal orientation layout and saving state.  
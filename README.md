# Android-Fundamentals-V2

Codelabs from Android Developers Fundamentals (Version 2)

A single project with multiple modules implementing each codelab.

# Modules

## 1.1 - Hello World (helloworld)

The related practical is [1.1 Android Studio and Hello World](https://codelabs.developers.google.com/codelabs/android-training-hello-world).

## 1.1 Coding challenge - Happy Birthday (happybirthday)

Coding challenge from [1.1 Android Studio and Hello World](https://codelabs.developers.google.com/codelabs/android-training-hello-world).

## 1.2 Part A - Hello Toast (hellotoast)

The related practical is [1.2 Part A: Your first interactive UI](https://codelabs.developers.google.com/codelabs/android-training-layout-editor-part-a).

### Goals
  - Use of constraints
  - Definition of View attributes
  - Use of no hard-coded resources
  - Use of event handler
  - Use of Toast
  
## 1.2 Part A Challenge - Hello Toast Challenge (helloToastChallenge)

Coding challenge from [1.2 Part A: Your first interactive UI](https://codelabs.developers.google.com/codelabs/android-training-layout-editor-part-a).

### Issues
  - When device orientation is changed count value is set to 0.
  
## 1.2 Part B - Hello Toast B (helloToastB)

The related practical is [1.2 Part B: The layout editor](https://codelabs.developers.google.com/codelabs/android-training-layout-editor-part-b)

Adding landscape phone layout and tablet layout to Hello Toast.

After "HelloToastB changed to LinearLayout" commit, the `ConstraintLayout` in the phone portrait orientation is changed to `LinearLayout`.

After "HelloToastB changed to RelativeLayout" commit, the `LinearLayout` in the phone portrait orientation is changed to `RelativeLayout`.

### Goals
  - Learning more features of `ConstraintLayout` and the layout editor.
  - Introduce two other `ViewGroup` subclasses: `LinearLayout` and `RelativeLayout`
  - Create layout variant for horizontal orientation
  - Layout variant for tablets and larger displays
  - Use baseline constraint
  - Use pack and align buttons
  - Position views witihin a `LinearLayout` and within a `RelativeLayout`
  
### Interesting learning
  - If just one View has the attribute `weight` set, this View expands to occupy all of the free space. This is how the `TextView` occupies most of the screen.
  - If you have 3 elements with weights of 1,2 and 1, they occupy 1/4, 2/4 and 1/4 of the space respectively.
  - textAlignmen `TextView` attribute, not available in *Attributes*, can be set directly via XML. 

## 1.2 Part B Challenge - Hello Toast B Challenge (helloToastBChallenge)

Coding challenge from [1.2 Part B: The layout editor](https://codelabs.developers.google.com/codelabs/android-training-layout-editor-part-b).
  
Centering the buttons from Hello Toast B in a tablet when in landscape.  

## 1.2 Part B Homework - Hello Constraints (helloConstraints)
[1.2 Part B Homework](https://codelabs.developers.google.com/codelabs/android-training-layout-editor-part-b/#9).

### Learned concepts
  - A method used by an event holder (onClick...) must be **public void method_name (View v)**
  - When a `View`calls a handler, it's more efficient from handler access the view directly from parameter (i.e. v) than getting it using findViewbyId.
    - If possible, always use the `View` from parameter.
  
## 1.3 Text and scrolling views - Scrolling Text (scrollingtext)
The related practical is [1.3 Text and scrolling views](https://codelabs.developers.google.com/codelabs/android-training-text-and-scrolling-views)
### Concepts
  - `ScrollView` can only hold a child within it.
    - If more views needed, the best choice is a `LinearLayout` with vertical orientation containing the views.
  - `ScrollView` is memory greedy. It's efficient and ideal for text, because it loads all of the content in memory and that's fast. 
    - If you want to show a long list of items better use `RecyclerView`. 
  - Text:
    - It only accepts \<b> and \<i> (bold and italic) HTML tags.
    - It only starts new paragraph with \n.
    - A "return" in the resource string will be seen as a space, you must not separate paragraphs this way.
    - Must escape (\) special symbols (apostrophe, quotes...).
    - Hyperlinks can be automated with special attribute in `View` declaration (.XML).

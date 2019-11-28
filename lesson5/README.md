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
    In the practical, the instruction is `setImageLevel(level)`.
    
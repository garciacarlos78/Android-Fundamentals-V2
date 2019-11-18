# Lesson 3: Testing, debugging, and using support libraries

Practicals related to [Android Developer Fundamentals (Version 2) - Unit 1: Get started - Lesson 3: Testing, debugging, and using support libraries](https://google-developer-training.github.io/android-developer-fundamentals-course-concepts-v2/unit-1-get-started/lesson-3-testing,-debugging,-and-using-support-libraries/3-1-c-the-android-studio-debugger/3-1-c-the-android-studio-debugger.html)

## 3.1 - The debugger (SimpleCalc)

Codelab from https://codelabs.developers.google.com/codelabs/android-training-using-debugger

*SimpleCalc* contains the whole project imported from https://github.com/google-developer-training/android-fundamentals-starter-apps-v2/tree/master/SimpleCalc.

Android Gradle Plugin update recommended, not updated.

### Key points
  - Conditional breakpoint -> right click on breakpoint, add condition.
  
## 3.2 - Unit tests

Codelab from https://codelabs.developers.google.com/codelabs/android-training-unit-tests

### Task 2 - Add more unit test to CalculatorTest (SimpleCalcUnitTests)

Added required unit tests.

### Coding challenges (SimpleCalcUnitTest)

Challenges from the codelab implemented in base *SimpleCalc*.

#### Challenge 1

I can't get an `IllegalArgumentException` because I get *Infinity* as a result, not a exception.

Anyway, I've implemented the unitary test according to https://stackoverflow.com/questions/156503/how-do-you-assert-that-a-certain-exception-is-thrown-in-junit-4-tests.

#### Challenge 2

**PENDING**

### Homework (SimpleCalcHomework)

Special values for testing (from https://www.tutorialspoint.com/java/lang/math_pow.htm):
  - Second argument == 0, -0 (=1.0)
  - Second argument 1.0 (= fist arg)
  - Second argument NaN (=NaN)
  - First NaN, second != 0 (=NaN)
  - Abs value first > 1, second positive infinity OR
    abs value first < 1, second negative infinity
      - Result: positive infinity
  - Abs value first > 1, second ngative infinity OR
    abs first < 1, second positive infinity
      - Result: positive 0
  - First 1, second infinite (=NaN)
  - First +0, second > 0 OR
    First +infinity, second < 0
      - Result: +0     
  - ...

#### Key points
  - Must change `android:inputType="numberDecimal"` by `android:inputType="numberSigned|numberDecimal"`, so it's possible enter negative numbers.
  
## 3.3 - Support libraries (HelloCompat)

Codelab: https://codelabs.developers.google.com/codelabs/android-training-support-libraries.

Whole project in folder *HelloCompat*.

### Interesting points
  - Get a random number between 0 and 19: `Random.nextInt(20)`.
  - Get resource identifier: `int colorResourceName = getResources().getIdentifier(colorName, "color", getApplicationContext().getPackageName());`
  - A way to know how to avoid using ContextCompat:
    - Set a debugger breakpoint in the line that uses it
    - Debug and use *step into*
    - You can then watch the different behaviour depending on the API.
    
    In this concrete case:
    `int colorRes = ContextCompat.getColor(this, colorResourceName);`
    When you step into, you can see:
    
    `@ColorInt
        public static int getColor(@NonNull Context context, @ColorRes int id) {
            if (Build.VERSION.SDK_INT >= 23) {
                return context.getColor(id);
            } else {
                return context.getResources().getColor(id);
            }
        }`
        
     So, you can avoid using ContextCompat replacing it with the code indicated.
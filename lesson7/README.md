# Unit 3 - Working in the background

# Lesson 7 - AsyncTask and AsyncTaskLoader

Codelabs from *Android Developer Fundamentals (Version 2)* - Unit 3: Working in the background - Lesson 7: Background tasks

## 7.1 - AsyncTask and AsyncTaskLoader (simpleAsyncTask)

Codelab from https://codelabs.developers.google.com/codelabs/android-training-create-asynctask.

*simpleAsyncTask* module include tasks 1, 2 and 3, and the coding challenge.

### 7.1.1 - Coding challenge (simpleAsyncTask)

Update the UI with current sleep time.

Steps:
  1. Change the second parameter of the `AsyncTask` subclass, from `Void` to `Integer`. This will be the number of ms with the current sleep time.
    It doesn't allow to use `int`, it may not accept primitive types.
  2. Change the `Thread.sleep(s)` instruction, that sleeps the thread for the whole random time, by a loop with `Thread.sleep(100)`. In each turn of the loop we increment by 100 the counter and call `publishProgress()`. This way we inform of the progress each 100 ms.
  3. Override `onProgressUpdate(Integer... values` method. This method is called from the loop, with `publishProgress()`. In this method we indicate the current sleeping time in the UI.
  
### 7.1.2 - Homework (homework)

Base module: *simpleAsyncTask*.

Objective: change the information about the progress by a progress bar.

Steps:
  1. Add the ProgressBar to the activity_main.xml, defining attributes `progress=0` and `max=100`.
  2. Add a the following member variable to `SimpleAsyncTask`:
    `private WeakReference<ProgressBar> mProgressBar;`
  3. Implement the `onPreExecute()` method to initialize the progress bar to 0.
  4. We'll fill the ProgressBar in increments of 5%, so we change the loop to reflect this (instead of steps of 100ms, as we did before).
  5. Change the `onProgressUpdate` to increment the progress bar percentaje. Now we don't need any parameter to know the increment to apply, we'll inrement always by 5%, so, we change the second parameter of the subclass from `Integer` to `Void`.
  6. As we divide the total amount of millisecons by 20, it can happen that the last increment of the loop lets the progress bar in 95%. To avoid this, in `onPostExecute` we fill the progress bar to 100%.
     
## 7.2 - AsyncTask and AsyncTaskLoader

Codelab from https://codelabs.developers.google.com/codelabs/android-training-asynctask-asynctaskloader.

### 7.2.1 - Task 2 - Create the app (whoWroteIt)

App creation, following the instructions from https://codelabs.developers.google.com/codelabs/android-training-asynctask-asynctaskloader/index.html?index=..%2F..%2Fandroid-training#3.

### 7.2.2 - Task 3 - Implement UI best practices (whoWroteItBP)

Source module: *whoWroteIt*

Implementation of UI best practices, following the instructions from https://codelabs.developers.google.com/codelabs/android-training-asynctask-asynctaskloader/index.html?index=..%2F..%2Fandroid-training#4.      
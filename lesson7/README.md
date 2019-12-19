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

#### Interesting points
  - NetworkInfo is *deprecated*.      

### 7.2.3 - Task 4 - Migrate to AsyncTaskLoader (whoTaskLoader)

Source module: *whoWroteItBP*

Refactor of *AsyncTask* to *AsyncTaskLoader*, following the instructions from https://codelabs.developers.google.com/codelabs/android-training-asynctask-asynctaskloader/index.html?index=..%2F..%2Fandroid-training#5.

Main goal: be able of rotating the screen during a query getting the right result. *AsyncTask* becomes disconnected from the *Activity* and is not able tu update the UI, so we change this *AsyncTask* class to *AsyncTaskLoader* class. 

#### Interesting points
  -`AsyncTaskLoader` part of Android platform's `Loader` API, deprecated in *Android P (API 28)* in favor of `ViewModels` and `LiveData`.


### 7.2.4 - Coding challenge (whoWroteEPUB)

Restrict the result to books downloadable in EPUB format.

Base module: *whoTaskLoader*

Steps:
  1. Explore Books API and find the corresponding parameter.
    Parameter: *download*
    Value: "epub"
  2. Add the corresponding parameter to the query.
    In *NetworkUtils.java* class, in *getBookInfo()*
    
### 7.2.5 - Homework (webSource)

Homework corresponding to https://codelabs.developers.google.com/codelabs/android-training-asynctask-asynctaskloader/index.html?index=..%2F..%2Fandroid-training#10.

Create an app in which you introduce a URL, select a protocol (http/https), and when you press the button you obtain the source code of the page.   

Steps:
  1. Create the UI.
    Main difficulties:
    - EditText baseline related to spinner.
      - Solution: it works right when there is content inside. Including the XML attribute `android:entries="@array/spinner_array"` the visibility in the preview is correct.
    - Spinner content.
      - Attribute showed in the previous point.
      - Definition of the adapter in `MainActivity`.
    - Default selection: the first item in the array. It also avoids no item selection option.
  2. Get the content when the button is pressed. For the moment, a Log.d will be shown.
  3. Create an AsynTaskLoader (my class WebLoader).
  4. Create the *NetworkUtils* class, which will do the connection to the website.
  5. Modify manifest.xml to include internet permissions. Should have been a previous step.
  6. Make `MainActivity` implement `LoaderManager.LoaderCallbacks<String>`, to handle the results of the `loadInBackground()` `AsynTaskLoader` method.
  7. Check correctness of query via Log.d (implemented in *NetworkUtils*).
  8. Hide keyboard and update TextView when pressed button
  9. Check connectivity and empty string introduced before calling the AsyncTaskLoader
  10. Make the query to the TaskLoader in `getSource` method.
  11. Make `MainActivity` implement `LoaderManager.LoaderCallbacks<String>`, and implement the required methods.
  12. Create the `WebLoader` in the `onCreateLoader` method.
  13. Put the result of the query in the TextView in the `onLoadFinished` method.
  14. Reconnect to the loader if it exists in case of device rotation.

Extra: exception treatment.

  15. Return the error string when an exception occurs and show it in the result TextView.
  
## 7.3 - Broadcast receivers

Codelab from https://codelabs.developers.google.com/codelabs/android-training-broadcast-receivers

### 7.3.1 - PowerReceiver app (powerReceiver)

App creation, following the instructions from https://codelabs.developers.google.com/codelabs/android-training-broadcast-receivers/index.html?index=..%2F..%2Fandroid-training#1

The PowerReceiver app will register a BroadcastReceiver that displays a toast message when the device is connected or disconnected from power. The app will also send and receive a custom broadcast to display a different toast message.



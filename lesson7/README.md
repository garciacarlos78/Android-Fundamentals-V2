# Unit 3 - Working in the background

# Lesson 7 - AsyncTask and AsyncTaskLoader

Codelabs from *Android Developer Fundamentals (Version 2)* - Unit 3: Working in the background - Lesson 7: Background tasks

## 7.1 - AsyncTask and AsyncTaskLoader (simpleAsyncTask)

Codelab from https://codelabs.developers.google.com/codelabs/android-training-create-asynctask.

*simpleAsyncTask* module include tasks 1, 2 and 3, and the coding challenge.

### Coding challenge

Update the UI with current sleep time.

Steps:
  1. Change the second parameter of the `AsyncTask` subclass, from `Void` to `Integer`. This will be the number of ms with the current sleep time.
    It doesn't allow to use `int`, it may not accept primitive types.
  2. Change the `Thread.sleep(s)` instruction, that sleeps the thread for the whole random time, by a loop with `Thread.sleep(100)`. In each turn of the loop we increment by 100 the counter and call `publishProgress()`. This way we inform of the progress each 100 ms.
  3. Override `onProgressUpdate(Integer... values` method. This method is called from the loop, with `publishProgress()`. In this method we indicate the current sleeping time in the UI.
  
   
  
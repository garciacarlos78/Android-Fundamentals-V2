# Unit 3 - Working in the background

# Lesson 8 - Alarms and schedulers

Codelabs from *Android Developer Fundamentals (Version 2)* - Unit 3: Working in the background - Lesson 8: Alarms and schedulers

## 8.1 - Notifications (notifyMe)

Codelab from https://codelabs.developers.google.com/codelabs/android-training-notifications

Includes tasks 1, 2 and 3, and coding challenge.

Notes:
  - The image for notification must be 2:1 aspect ratio and 450dp or less width
  - In the instructions, the method `setNotificationButtonState` receives three `Boolean` as arguments. It seems it's enough to pass it three primitive `boolean`.

Coding challenge:
  - Method to use: `NotificationCompat.Builder setDeleteIntent(PendingIntent intent)`
  
### 8.1.1 - Homework (homework)

Source module: *notifyMe*, from 8.1.

Goal: change updated notification to use `InboxStyle` expanded layout instead of `BigPictureStyle`.

Fake string data for each line and for the summary text.

## 8.2 - The alarm manager

Codelab from https://codelabs.developers.google.com/codelabs/android-training-alarm-manager

### 8.2.1 - Tasks 1-2 - The alarm manager (standUp)

*standUp* module includes tasks 1 and 2.

### 8.2.2 - Task 3 - Repeating alarm (standUpAlarm)

Task 3, based on *standUp* module.

Interesting points:
  - *setInexactRepeating()*: if you don't need precission, it's more resource-efficient because it lets the system bundle alarms from differents apps together.
  
### 8.2.3 - Coding challenge - Showing next time alarm (standUpNext)

Coding challenge: add a button that displays a Toast showing the time of the next alarm clock.

According to https://developer.android.com/reference/android/app/AlarmManager#getNextAlarmClock(), "the alarm clocks considered are those scheluded by any application using the `setAlarmClock(AlarmManager.AlarmClockInfo, PendingIntent)` method". As a consequence, I think it's not possible to set a repeating alarm, such as in previous task 8.2.

To accomplish the coding challenge, I'll put 3 toggle buttons: one to activate the alarm in 25' from the moment of activating it, another one in 5' and the last one in 15'.

With the aditional button that displays the Toast it will be shown the time of the next alarm.

Steps:
  1. Add the buttons to the XML
  2. Add the listeners to the buttons
    2.1. Include logical to have just one of the three buttons toggled on
  3. Implement the functionality to the button
    - Change *notifyIntent* from local variable to member variable, so that we can check if there is an active alarm
    - Change *alarmManager* from local final variable to member not final variable, so that we can call its `getNextAlarmClock()` method.
      - `getNextAlarmClock()` only returns alarm clocks scheduled using the `setAlarmClock(AlarmManager.AlarmClockInfo, PnedingIntent)` method. Consequently, `alarmManager.setInexactRepeating...` must be changed.
      
### 8.2.4 - Homework (wishApp)

Make an app that delivers a notification when the time is 11:11 AM. The screen displays a toggle switch that turns the alarm on and off.

#### Steps
1. Create the UI with the toggle switch
2. Add behaviour on change and check it via Toast
3. Create the notification and the notification channel (for compatibility with Android 8.0, API level 27). 
4. Change the Toast in the toggle switch, sending the notification and cancelling it when swithced on or off. Check that it works without predefined time (when clicking the button).
5. Change the behaviour of the notification, being sent at a specified time (11:11), creating an alarm.
   1. Create the broadcast receiver that receives the broadcast intents from the `AlarmManager`.
   2. Set up the broadcast pending intent.
   3. Set the alarm.
   
      Important: it's important to check the current time: if it's past 11:11, you should add a day to the alarm, otherwise the notification would be sent inmediately.
     
   4. Check the state of the alarm when app launched.
   
      Important: this check should be done after *notifyIntent* is created, but before *notifyPendingIntent* is created. Otherwise, it's always true.  

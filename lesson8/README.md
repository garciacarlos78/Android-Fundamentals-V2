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
package com.example.a07;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class AlarmReceiver extends BroadcastReceiver {
    String notificationText;

    @Override
    public void onReceive(Context context, Intent intent) {
        int notificationId = intent.getIntExtra("notification_id", 0);  // Get id from Intent

        notificationText = "Click to start the Questionnaire."; //! TODO: Change this to a string resource
//        switch (notificationId) {
//            case 1:
//                notificationText = "Notification Text for ID 1";
//                break;
//            case 2:
//                notificationText = "Notification Text for ID 2";
//                break;
//            case 3:
//                notificationText = "Notification Text for ID 3";
//                break;
//        }

        // Intent to launch activity
        Intent activityIntent = new Intent(context, IntroductoryActivity.class);
        activityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activityIntent.putExtra("runFunction", "openQuestionnaire"); // Pass the function to be executed as an extra
        PendingIntent activityPendingIntent = PendingIntent.getActivity(context, 0, activityIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Build the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "questionnaireNotification")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Time for a Questionnaire!") //! TODO: Change this to a string resource
                .setContentText(notificationText)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);  // removes notification when clicked

        // Set the intent to launch activity
        builder.setContentIntent(activityPendingIntent);

        // Get an instance of NotificationManager
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        // Show the notification
        notificationManager.notify(notificationId, builder.build());
    }
}


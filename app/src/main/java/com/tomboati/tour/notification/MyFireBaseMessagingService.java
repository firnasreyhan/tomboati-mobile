package com.tomboati.tour.notification;
import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.tomboati.tour.R;
import com.tomboati.tour.preference.AppPreference;
import com.tomboati.tour.view.activity.SplashScreenActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyFireBaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

//        if (!remoteMessage.getNotification().getTitle().equals("Admin")) {
//            @SuppressLint("SimpleDateFormat")
//            String tanggal = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
//            AppPreference.saveNotif(
//                    getApplicationContext(),
//                    tanggal,
//                    remoteMessage.getNotification().getTitle(),
//                    remoteMessage.getNotification().getBody()
//            );
//        }

        Intent intent = new Intent(this, SplashScreenActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        String channelId = "Default";
        NotificationCompat.Builder builder = new NotificationCompat
            .Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_stat_icon)
            .setContentTitle(remoteMessage.getNotification().getTitle())
            .setContentText(remoteMessage.getNotification().getBody())
            .setAutoCancel(true)
            .setContentIntent(pendingIntent);
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, "Default channel", NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(channel);
        }
        manager.notify(0, builder.build());
    }
}
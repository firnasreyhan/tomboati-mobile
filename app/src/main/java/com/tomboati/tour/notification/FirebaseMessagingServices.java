package com.tomboati.tour.notification;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FirebaseMessagingServices extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.d("TITLE", remoteMessage.getNotification().getTitle());
        Log.d("BODY", remoteMessage.getNotification().getBody());
    }
}

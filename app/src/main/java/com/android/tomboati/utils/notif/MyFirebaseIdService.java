package com.android.tomboati.utils.notif;

import com.android.tomboati.preference.AppPreference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;

public class MyFirebaseIdService extends FirebaseMessagingService {

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        String userKey = AppPreference.getUser(getApplicationContext()).getEmail();
        String refreshToken= FirebaseInstanceId.getInstance().getToken();
        if(userKey != null){
            updateToken(refreshToken);
        }
    }

    private void updateToken(String refreshToken) {
        String userKey = AppPreference.getUser(getApplicationContext()).getEmail();
        Token token = new Token(refreshToken);
        FirebaseDatabase.getInstance().getReference("TomboAti").child("Token").child(userKey).setValue(token);
    }
}
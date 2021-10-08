package com.tomboati.tour.utils.notif;

import android.content.Context;

import com.tomboati.tour.preference.PreferenceAkun;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.messaging.FirebaseMessaging;

public class TokenFMS {

    private String token = "";

    public TokenFMS(Context context) {
        setToken(context);
    }

    public TokenFMS(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private void setToken(Context context) {
        FirebaseMessaging.getInstance().getToken().addOnSuccessListener(new OnSuccessListener<String>() {
            @Override
            public void onSuccess(String s) {
                PreferenceAkun.setToken(context, s);
            }
        });
    }
}

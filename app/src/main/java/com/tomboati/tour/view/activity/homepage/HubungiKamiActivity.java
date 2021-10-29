package com.tomboati.tour.view.activity.homepage;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.tomboati.tour.databinding.ActivityHubungiKamiBinding;
import com.tomboati.tour.view.activity.base.BaseToolbarActivity;

public class HubungiKamiActivity extends BaseToolbarActivity {

    private ActivityHubungiKamiBinding bind;
    private static final String TAG = "HUBUNGI KAMI ACTIVITY";

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        bind = ActivityHubungiKamiBinding.inflate(getLayoutInflater());
        setToolbar(bind.toolbar, "Hubungi Kami");

        bind.materialCardViewWhatsapp.setOnClickListener(v -> {
            try {
                final String TO_NUMBER = "6281235438885";
                final String TEXT_BODY = "Halo, saya dari pengguna aplikasi tomboatitour ingin menanyakan sesuatu kepada anda bahwa ....";
                final String URI = "https://api.whatsapp.com/send?phone="+ TO_NUMBER + "&text="+TEXT_BODY;
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setData(Uri.parse(URI));
                startActivity(sendIntent);
            } catch (Exception e) {
                Log.d(TAG, "onCreate: " + e.getMessage());
            }
        });

        bind.materialCardViewEmail.setOnClickListener(v -> {
            try {
                final String TO_EMAIL = "tomboatitour@gmail.com";
                final String TEXT_SUBJECT = "Tanya Admin";
                final String TEXT_BODY = "Halo, saya dari pengguna aplikasi tomboatitour ingin " +
                        "menanyakan sesuatu kepada anda bahwa ....";
                Intent sendIntent = new Intent(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {TO_EMAIL});
                sendIntent.putExtra(Intent.EXTRA_SUBJECT, TEXT_SUBJECT);
                sendIntent.putExtra(Intent.EXTRA_TEXT, TEXT_BODY);
                sendIntent.setType("message/rfc822");
                startActivity(Intent.createChooser(sendIntent, "Chose an Email client : "));
            }catch (Exception e) {
                Log.d(TAG, "onCreate: " + e.getMessage());
            }
        });

        bind.materialCardViewFacebook.setOnClickListener(v -> {
            try {
                final String URI = "https://facebook.com/tomboatitour";
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setData(Uri.parse(URI));
                startActivity(sendIntent);
            }catch (Exception e) {
                Log.d(TAG, "onCreate: " + e.getMessage());
            }
        });

        bind.materialCardViewInstagram.setOnClickListener(v -> {
            try {
                final String URI = "https://instagram.com/tomboatitour_official";
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setData(Uri.parse(URI));
                startActivity(sendIntent);
            }catch (Exception e) {
                Log.d(TAG, "onCreate: " + e.getMessage());
            }
        });

        bind.materialCardViewWeb.setOnClickListener(v -> {
            try {
                final String URI = "https://tomboatitour.com";
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setData(Uri.parse(URI));
                startActivity(sendIntent);
            }catch (Exception e) {
                Log.d(TAG, "onCreate: " + e.getMessage());
            }
        });
    }

    @Override
    protected View getContentView() {
        return bind.getRoot();
    }

}

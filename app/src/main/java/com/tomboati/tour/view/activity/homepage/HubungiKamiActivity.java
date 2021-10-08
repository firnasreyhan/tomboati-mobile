package com.tomboati.tour.view.activity.homepage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.tomboati.tour.R;
import com.google.android.material.card.MaterialCardView;

public class HubungiKamiActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private MaterialCardView materialCardViewWhatsapp, materialCardViewEmail,
            materialCardViewFacebook;
    private static final String TAG = "HUBUNGI KAMI ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_hubungi_kami);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Hubungi Kami");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        materialCardViewWhatsapp = findViewById(R.id.materialCardViewWhatsapp);
        materialCardViewEmail = findViewById(R.id.materialCardViewEmail);
        materialCardViewFacebook = findViewById(R.id.materialCardViewFacebook);

        materialCardViewWhatsapp.setOnClickListener(v -> {
            try {
                final String TO_NUMBER = "6281216361984";
                final String TEXT_BODY = "Halo, saya dari pengguna aplikasi tomboatitour ingin menanyakan sesuatu kepada anda bahwa ....";
                final String URI = "https://api.whatsapp.com/send?phone="+ TO_NUMBER + "&text="+TEXT_BODY;
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setData(Uri.parse(URI));
                startActivity(sendIntent);
            } catch (Exception e) {
                Log.d(TAG, "onCreate: " + e.getMessage());
            }
        });

        materialCardViewEmail.setOnClickListener(v -> {
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

        materialCardViewFacebook.setOnClickListener(v -> {
            try {
                final String URI = "https://facebook.com/tomboatitour";
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setData(Uri.parse(URI));
                startActivity(sendIntent);
            }catch (Exception e) {
                Log.d(TAG, "onCreate: " + e.getMessage());
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

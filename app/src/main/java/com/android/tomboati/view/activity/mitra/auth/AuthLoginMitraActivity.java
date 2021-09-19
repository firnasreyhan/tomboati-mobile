package com.android.tomboati.view.activity.mitra.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.tomboati.R;
import com.android.tomboati.preference.PreferenceAkun;

public class AuthLoginMitraActivity extends AppCompatActivity {


    private TextView textViewBack, textViewPrefix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_auth_login_mitra);

        textViewBack = findViewById(R.id.textViewBack);
        textViewPrefix = findViewById(R.id.textViewPrefix);

        if(PreferenceAkun.getAkun(this) == null){
            textViewBack.setText("Daftar");
            textViewPrefix.setVisibility(View.VISIBLE);
        }


        textViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });






    }
}
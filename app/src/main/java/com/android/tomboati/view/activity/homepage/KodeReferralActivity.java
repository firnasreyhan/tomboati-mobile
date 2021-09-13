package com.android.tomboati.view.activity.homepage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

import com.android.tomboati.R;
import com.android.tomboati.preference.PreferenceAkun;
import com.google.android.material.textfield.TextInputEditText;

public class KodeReferralActivity extends AppCompatActivity {

    private TextView textInputEditReferralFrom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_kode_referral);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Kode Referral");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        textInputEditReferralFrom = findViewById(R.id. textInputEditReferralFrom);
        textInputEditReferralFrom.setText(PreferenceAkun.getAkun(this).getReferral());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
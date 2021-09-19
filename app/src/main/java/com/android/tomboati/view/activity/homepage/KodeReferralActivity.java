package com.android.tomboati.view.activity.homepage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

import com.android.tomboati.R;
import com.android.tomboati.model.AkunModel;
import com.android.tomboati.preference.PreferenceAkun;

public class KodeReferralActivity extends AppCompatActivity {

    private TextView textViewReferralFrom, textViewKodeReferral;

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

        textViewReferralFrom = findViewById(R.id. textViewReferralFrom);
        textViewKodeReferral = findViewById(R.id. textViewKodeReferral);

        AkunModel data = PreferenceAkun.getAkun(this);

        textViewReferralFrom.setText(data.getReferral());

        if(data.getPaket().equals("MITRA")) {
            textViewKodeReferral.setText(data.getUserId());
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
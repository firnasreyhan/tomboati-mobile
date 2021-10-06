package com.android.tomboati.view.activity.homepage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.tomboati.R;
import com.android.tomboati.view.activity.pendaftaran.ListPaketActivity;
import com.android.tomboati.view.activity.pendaftaran.ListPaketHajiActivity;

public class UmrohHajiActivity extends AppCompatActivity {

    final int[] arrId = {
        R.id.cardViewUmrohPromo, R.id.cardViewUmrohHemat, R.id.cardViewUmrohBisnis,
        R.id.cardViewUmrohVIP, R.id.cardViewUmrohPlus, R.id.cardViewHajiPlus,
        R.id.cardViewHajiTanpaAntri, R.id.cardViewHajiReguler, R.id.cardViewHajiTalangan,
        R.id.cardViewHajiBadal
    };

    final String[] arrTitle = {
        "Umroh Promo", "Umroh Hemat", "Umroh Bisnis", "Umroh VIP", "Umroh Plus",
        "Haji Plus", "Haji Tanpa Antri", "Haji Reguler", "Haji Talangan", "Haji Badal"
    };

    final String[] arrNamePaket = {
        "Promo", "Hemat", "Bisnis", "VIP", "Plus", "Plus", "TanpaAntri", "Reguler", "Talangan", "Badal"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_umroh_haji);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Umroh & Haji");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        for(int i = 0; i < arrId.length; i++) {
            final int j = i;
            findViewById(arrId[i]).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), (j < 7) ? ListPaketActivity.class : ListPaketHajiActivity.class);
                    intent.putExtra((j < 5) ? "PAKET": "PAKET_HAJI",arrNamePaket[j]);
                    intent.putExtra("TITLE", arrTitle[j]);
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
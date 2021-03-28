package com.android.tomboati.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.tomboati.R;

public class UmrohHajiActivity extends AppCompatActivity {

    final int[] arrId = {
        R.id.cardViewUmrohPromo, R.id.cardViewUmrohHemat, R.id.cardViewUmrohBisnis,
        R.id.cardViewUmrohVIP, R.id.cardViewUmrohPlus, R.id.cardViewHajiPlus,
        R.id.cardViewHajiTanpaAntri
    };

    final String[] arrTitle = {
        "Umroh Promo", "Umroh Hemat", "Umroh Bisnis", "Umroh VIP", "Umroh Plus",
        "Haji Plus", "Haji Tanpa Antri"
    };

    final String[] arrNamePaket = {
        "Promo", "Hemat", "Bisnis", "VIP", "Plus", "Plus", "TanpaAntri"
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
                    Intent intent = new Intent(v.getContext(), ListPaketActivity.class);
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
package com.android.tomboati.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.tomboati.R;

public class SholatActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private CardView cardViewSholatWajib, cardViewWudhu, cardViewArahKiblat, cardViewJadwalSholat, cardViewMasjidTerdekat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_sholat);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Sholat");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        cardViewSholatWajib = findViewById(R.id.cardViewSholatWajib);
        cardViewWudhu = findViewById(R.id.cardViewWudhu);
        cardViewArahKiblat = findViewById(R.id.cardViewArahKiblat);
        cardViewJadwalSholat = findViewById(R.id.cardViewJadwalSholat);
        cardViewMasjidTerdekat = findViewById(R.id.cardViewMasjidTerdekat);

        cardViewSholatWajib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), SholatWajibActivity.class));
            }
        });

        cardViewWudhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), WudhuActivity.class));
            }
        });

        cardViewArahKiblat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), ArahKiblatActivity.class));
            }
        });

        cardViewJadwalSholat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), JadwalSholatActivity.class));
            }
        });

        cardViewMasjidTerdekat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), MasjidTerdekatActivity.class));
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
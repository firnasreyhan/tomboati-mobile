package com.android.tomboati.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.android.tomboati.R;

public class SholatActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private LinearLayout linearLayoutSholatWajib, linearLayoutWudhu, linearLayoutArahKiblat, linearLayoutJadwalSholat, linearLayoutMasjidTerdekat;

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

        linearLayoutSholatWajib = findViewById(R.id.linearLayoutSholatWajib);
        linearLayoutWudhu = findViewById(R.id.linearLayoutWudhu);
        linearLayoutArahKiblat = findViewById(R.id.linearLayoutArahKiblat);
        linearLayoutJadwalSholat = findViewById(R.id.linearLayoutJadwalSholat);
        linearLayoutMasjidTerdekat = findViewById(R.id.linearLayoutMasjidTerdekat);

        linearLayoutSholatWajib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), SholatWajibActivity.class));
            }
        });

        linearLayoutWudhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), WudhuActivity.class));
            }
        });

        linearLayoutArahKiblat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), ArahKiblatActivity.class));
            }
        });

        linearLayoutJadwalSholat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), JadwalSholatActivity.class));
            }
        });

        linearLayoutMasjidTerdekat.setOnClickListener(new View.OnClickListener() {
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
package com.android.tomboati.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.tomboati.R;

public class UmrohHajiActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private CardView cardViewUmrohPromo, cardViewUmrohHemat, cardViewUmrohBisnis, cardViewUmrohVIP, cardViewUmrohPlus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_umroh_haji);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Umroh & Haji");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        cardViewUmrohPromo = findViewById(R.id.cardViewUmrohPromo);
        cardViewUmrohHemat = findViewById(R.id.cardViewUmrohHemat);
        cardViewUmrohBisnis = findViewById(R.id.cardViewUmrohBisnis);
        cardViewUmrohVIP = findViewById(R.id.cardViewUmrohVIP);
        cardViewUmrohPlus = findViewById(R.id.cardViewUmrohPlus);

        cardViewUmrohPromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ListPaketActivity.class);
                intent.putExtra("PAKET", "Promo");
                intent.putExtra("TITLE", "Umroh Promo");
                startActivity(intent);
            }
        });

        cardViewUmrohHemat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ListPaketActivity.class);
                intent.putExtra("PAKET", "Hemat");
                intent.putExtra("TITLE", "Umroh Hemat");
                startActivity(intent);
            }
        });

        cardViewUmrohBisnis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ListPaketActivity.class);
                intent.putExtra("PAKET", "Bisnis");
                intent.putExtra("TITLE", "Umroh Bisnis");
                startActivity(intent);
            }
        });

        cardViewUmrohVIP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ListPaketActivity.class);
                intent.putExtra("PAKET", "VIP");
                intent.putExtra("TITLE", "Umroh VIP");
                startActivity(intent);
            }
        });

        cardViewUmrohPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ListPaketActivity.class);
                intent.putExtra("PAKET", "Plus");
                intent.putExtra("TITLE", "Umroh Plus");
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
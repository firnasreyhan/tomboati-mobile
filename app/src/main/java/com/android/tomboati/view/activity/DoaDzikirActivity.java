package com.android.tomboati.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.tomboati.R;
import com.android.tomboati.utils.Utility;

public class DoaDzikirActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private CardView cardViewAsmaulHusna, cardViewIstighosah, cardViewYasinTahlil,
            cardViewDoaHarian, cardViewDoaUmrah, cardViewDoaHaji, cardViewDoaZiarah,
            cardViewTempatMustajab, cardViewWaktuMustajab, cardViewAdabBerdoa,
            cardViewDoaRamadhan, cardViewTasbih, cardViewAnekaSholawat, cardViewTembangSholawat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_doa_dzikir);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Do’a & Dzikir");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Utility.addValue();

        cardViewAsmaulHusna = findViewById(R.id.cardViewAsmaulHusna);
        cardViewIstighosah = findViewById(R.id.cardViewIstighosah);
        cardViewYasinTahlil = findViewById(R.id.cardViewYasinTahlil);
        cardViewDoaHarian = findViewById(R.id.cardViewDoaHarian);
        cardViewDoaUmrah = findViewById(R.id.cardViewDoaUmrah);
        cardViewDoaHaji = findViewById(R.id.cardViewDoaHaji);
        cardViewDoaZiarah = findViewById(R.id.cardViewDOaZiarah);
        cardViewTempatMustajab = findViewById(R.id.cardViewTempatMustajab);
        cardViewWaktuMustajab = findViewById(R.id.cardViewWaktuMustajab);
        cardViewAdabBerdoa = findViewById(R.id.cardViewAdabBerdoa);
        cardViewDoaRamadhan = findViewById(R.id.cardViewDoaRamadhan);
        cardViewTasbih = findViewById(R.id.cardViewTasbih);
        cardViewAnekaSholawat = findViewById(R.id.cardViewAnekaSholawat);
        cardViewTembangSholawat = findViewById(R.id.cardViewTembangSholawat);

        cardViewAsmaulHusna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), NewAsmaulHusnaActivity.class));
            }
        });

        cardViewIstighosah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), IstighosahActivity.class));
            }
        });

        cardViewYasinTahlil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), YasinTahlilActivity.class));
            }
        });

        cardViewDoaHarian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), DoaSehariHariActivity.class));
            }
        });

        cardViewDoaUmrah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), DoaUmrahActivity.class));
            }
        });

        cardViewDoaHaji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), DoaHajiActivity.class));
            }
        });

        cardViewDoaZiarah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), DoaZiarahActivity.class));
            }
        });

        cardViewTempatMustajab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), TampatMustajabActivity.class));
            }
        });

        cardViewWaktuMustajab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), WaktuMustajabActivity.class));
            }
        });

        cardViewAdabBerdoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), AdabBerdoaActivity.class));
            }
        });

        cardViewDoaRamadhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), DoaRamadhanActivity.class));
            }
        });

        cardViewTasbih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), TasbihActivity.class));
            }
        });

        cardViewAnekaSholawat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), AnekaSholawatActivity.class));
            }
        });

        cardViewTembangSholawat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), TembangSholawatActivity.class));
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
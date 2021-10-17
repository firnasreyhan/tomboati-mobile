package com.tomboati.tour.view.activity.doa_dzikir;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.tomboati.tour.R;
import com.tomboati.tour.databinding.ActivityDoaDzikirBinding;
import com.tomboati.tour.helper.Common;
import com.tomboati.tour.utils.Utility;

public class DoaDzikirActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        ActivityDoaDzikirBinding bind = ActivityDoaDzikirBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        setSupportActionBar(bind.toolbar);
        setTitle("Do’a & Dzikir");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Utility.addValue();

        bind.cardViewAsmaulHusna.setOnClickListener(v -> Common.startActivity(v, NewAsmaulHusnaActivity.class));
        bind.cardViewIstighosah.setOnClickListener(v -> Common.startActivity(v, IstighosahActivity.class));
        bind.cardViewYasinTahlil.setOnClickListener(v -> Common.startActivity(v, YasinTahlilActivity.class));
        bind.cardViewDoaHarian.setOnClickListener(v -> Common.startActivity(v, DoaSehariHariActivity.class));
        bind.cardViewDoaUmrah.setOnClickListener(v -> Common.startActivity(v, DoaUmrahActivity.class));
        bind.cardViewDoaHaji.setOnClickListener(v -> Common.startActivity(v, DoaHajiActivity.class));
        bind.cardViewDOaZiarah.setOnClickListener(v -> Common.startActivity(v, DoaZiarahActivity.class));
        bind.cardViewTempatMustajab.setOnClickListener(v -> Common.startActivity(v, TampatMustajabActivity.class));
        bind.cardViewWaktuMustajab.setOnClickListener(v -> Common.startActivity(v, WaktuMustajabActivity.class));
        bind.cardViewAdabBerdoa.setOnClickListener(v -> Common.startActivity(v, AdabBerdoaActivity.class));
        bind.cardViewDoaRamadhan.setOnClickListener(v -> Common.startActivity(v, DoaRamadhanActivity.class));
        bind.cardViewTasbih.setOnClickListener(v -> Common.startActivity(v, TasbihActivity.class));
        bind.cardViewAnekaSholawat.setOnClickListener(v -> Common.startActivity(v, AnekaSholawatActivity.class));
        bind.cardViewTembangSholawat.setOnClickListener(v -> Common.startActivity(v, TembangSholawatActivity.class));

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
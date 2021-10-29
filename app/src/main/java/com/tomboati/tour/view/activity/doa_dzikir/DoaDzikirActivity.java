package com.tomboati.tour.view.activity.doa_dzikir;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tomboati.tour.databinding.ActivityDoaDzikirBinding;
import com.tomboati.tour.utils.Utility;
import com.tomboati.tour.view.activity.base.BaseToolbarActivity;

public class DoaDzikirActivity extends BaseToolbarActivity {

    private ActivityDoaDzikirBinding bind;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        bind = ActivityDoaDzikirBinding.inflate(getLayoutInflater());
        setToolbar(bind.toolbar, "Doa & Dzikir");
        Utility.addValue();
        bind.cardViewAsmaulHusna.setOnClickListener(v -> startsActivity( NewAsmaulHusnaActivity.class));
        bind.cardViewIstighosah.setOnClickListener(v -> startsActivity( IstighosahActivity.class));
        bind.cardViewYasinTahlil.setOnClickListener(v -> startsActivity( YasinTahlilActivity.class));
        bind.cardViewDoaHarian.setOnClickListener(v -> startsActivity( DoaSehariHariActivity.class));
        bind.cardViewDoaUmrah.setOnClickListener(v -> startsActivity( DoaUmrahActivity.class));
        bind.cardViewDoaHaji.setOnClickListener(v -> startsActivity( DoaHajiActivity.class));
        bind.cardViewDOaZiarah.setOnClickListener(v -> startsActivity( DoaZiarahActivity.class));
        bind.cardViewTempatMustajab.setOnClickListener(v -> startsActivity( TampatMustajabActivity.class));
        bind.cardViewWaktuMustajab.setOnClickListener(v -> startsActivity( WaktuMustajabActivity.class));
        bind.cardViewAdabBerdoa.setOnClickListener(v -> startsActivity( AdabBerdoaActivity.class));
        bind.cardViewDoaRamadhan.setOnClickListener(v -> startsActivity( DoaRamadhanActivity.class));
        bind.cardViewTasbih.setOnClickListener(v -> startsActivity( TasbihActivity.class));
        bind.cardViewAnekaSholawat.setOnClickListener(v -> startsActivity( AnekaSholawatActivity.class));
        bind.cardViewTembangSholawat.setOnClickListener(v -> startsActivity( TembangSholawatActivity.class));
    }

    @Override
    protected View getContentView() {
        return bind.getRoot();
    }
}
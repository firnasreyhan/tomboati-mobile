package com.tomboati.tour.view.activity.doa_dzikir;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tomboati.tour.R;
import com.tomboati.tour.utils.Utility;

public class DoaDzikirActivity extends AppCompatActivity {

    private final int[] arrId = {
            R.id.cardViewAsmaulHusna, R.id.cardViewIstighosah, R.id.cardViewYasinTahlil,
            R.id.cardViewDoaHarian, R.id.cardViewDoaUmrah, R.id.cardViewDoaHaji, R.id.cardViewDOaZiarah,
            R.id.cardViewTempatMustajab, R.id.cardViewWaktuMustajab, R.id.cardViewAdabBerdoa,
            R.id.cardViewDoaRamadhan, R.id.cardViewTasbih, R.id.cardViewAnekaSholawat, R.id.cardViewTembangSholawat
    };

    private final AppCompatActivity[] arrActivity = {
            new NewAsmaulHusnaActivity(), new IstighosahActivity(), new YasinTahlilActivity(),
            new DoaSehariHariActivity(), new DoaUmrahActivity(), new DoaHajiActivity(),
            new DoaZiarahActivity(), new TampatMustajabActivity(), new WaktuMustajabActivity(),
            new AdabBerdoaActivity(), new DoaRamadhanActivity(), new TasbihActivity(),
            new AnekaSholawatActivity(), new TembangSholawatActivity()
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_doa_dzikir);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Do’a & Dzikir");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Utility.addValue();

        for(int i = 0; i < arrId.length; i++) {
            int j = i;
            findViewById(arrId[i]).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(v.getContext(), arrActivity[j].getClass()));
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
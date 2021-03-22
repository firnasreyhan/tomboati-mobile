package com.android.tomboati.view.activity.sholat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.tomboati.R;

public class SholatActivity extends AppCompatActivity {
    private Toolbar toolbar;

    private final int[] arrId = {
            R.id.cardViewSholatWajib, R.id.cardViewWudhu, R.id.cardViewArahKiblat, R.id.cardViewJadwalSholat, 
            R.id.cardViewMasjidTerdekat, R.id.cardViewSyaratSholat, R.id.cardViewDoaSesudahSholat, R.id.cardViewTayamum,
            R.id.cardViewSholatSunnah, R.id.cardViewAdabSholat, R.id.cardViewSholatJenazah, R.id.cardViewSholatDiperjalanan
    };

    private final AppCompatActivity[] arrActivity = {
            new SholatWajibActivity(), new WudhuActivity(), new ArahKiblatActivity(),
            new JadwalSholatActivity(), new MasjidTerdekatActivity(), new SyaratSholatActivity(),
            new DoaSesudahSholatActivity(), new TayamumActivity(), new SholatSunnahActivity(),
            new AdabSholatActivity(), new SholatJenazahActivity(), new SholatDiperjalananActivity()
    };

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
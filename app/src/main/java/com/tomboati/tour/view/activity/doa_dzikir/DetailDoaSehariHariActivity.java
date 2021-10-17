package com.tomboati.tour.view.activity.doa_dzikir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tomboati.tour.R;
import com.tomboati.tour.databinding.ActivityDetailDoaSehariHariBinding;


public class DetailDoaSehariHariActivity extends AppCompatActivity {

    private ActivityDetailDoaSehariHariBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        bind = ActivityDetailDoaSehariHariBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        setTitle("Detail Doa");
        setSupportActionBar(bind.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent passing = this.getIntent();

        bind.textJudulDoa.setText(passing.getStringExtra("TITLE"));
        bind.textArabic.setText(passing.getStringExtra("ARABIC"));
        bind.textTranslate.setText(passing.getStringExtra("TRANSLATE"));
        bind.textArti.setText(passing.getStringExtra("ARTI"));

        if(passing.getBooleanExtra("IS_KETERANGAN_ACTIVE", false)) {
            bind.garis2.setVisibility(View.VISIBLE);
            bind.a2.setVisibility(View.VISIBLE);
            bind.textKeterangan.setVisibility(View.VISIBLE);

            bind.textKeterangan.setText(passing.getStringExtra("KETERANGAN"));
        }
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
package com.tomboati.tour.view.activity.doa_dzikir;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.tomboati.tour.R;
import com.tomboati.tour.databinding.ActivityDetailAnekaSholawatBinding;
import com.tomboati.tour.model.DoaModel;


public class DetailAnekaSholawatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        ActivityDetailAnekaSholawatBinding bind = ActivityDetailAnekaSholawatBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        setTitle("Detail Sholawat");
        setSupportActionBar(bind.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        bind.setDoa(
            new DoaModel(
                getIntent().getStringExtra("TITLE"),
                getIntent().getStringExtra("ARABIC"),
                getIntent().getStringExtra("TRANSLATE"),
                getIntent().getStringExtra("ARTI"),
                getIntent().getStringExtra("KETERANGAN"),
                getIntent().getBooleanExtra("IS_KETERANGAN_ACTIVE", false)
            )
        );
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
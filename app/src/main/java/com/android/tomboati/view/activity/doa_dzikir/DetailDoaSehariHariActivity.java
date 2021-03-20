package com.android.tomboati.view.activity.doa_dzikir;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.tomboati.R;
import com.codesgood.views.JustifiedTextView;


public class DetailDoaSehariHariActivity extends AppCompatActivity {

    private View garis2;
    private TextView judul, header_keterangan, ayat;
    private JustifiedTextView translate, arti, keterangan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_detail_doa_sehari_hari);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setTitle("Detail Doa");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        garis2 = findViewById(R.id.garis2);
        header_keterangan = findViewById(R.id.a2);
        keterangan = findViewById(R.id.text_keterangan);
        ayat = findViewById(R.id.text_arabic);
        judul = findViewById(R.id.text_judul_doa);
        translate = findViewById(R.id.text_translate);
        arti = findViewById(R.id.text_arti);

        Intent passing = this.getIntent();

        judul.setText(passing.getStringExtra("TITLE"));
        ayat.setText(passing.getStringExtra("ARABIC"));
        translate.setText(passing.getStringExtra("TRANSLATE"));
        arti.setText(passing.getStringExtra("ARTI"));

        if(passing.getBooleanExtra("IS_KETERANGAN_ACTIVE", false)) {
            garis2.setVisibility(View.VISIBLE);
            header_keterangan.setVisibility(View.VISIBLE);
            keterangan.setVisibility(View.VISIBLE);

            keterangan.setText(passing.getStringExtra("KETERANGAN"));
        }
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
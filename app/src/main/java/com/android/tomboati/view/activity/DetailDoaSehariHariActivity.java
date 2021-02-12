package com.android.tomboati.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.android.tomboati.R;
import com.codesgood.views.JustifiedTextView;


public class DetailDoaSehariHariActivity extends AppCompatActivity {

    private TextView ayat, judul;
    private JustifiedTextView translate, arti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_detail_doa_sehari_hari);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setTitle("Detail Doa Harian");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ayat = (TextView) findViewById(R.id.text_arabic);
        judul = (TextView) findViewById(R.id.text_judul_doa);
        translate = (JustifiedTextView) findViewById(R.id.text_translate);
        arti = (JustifiedTextView) findViewById(R.id.text_arti);

        Intent passing = this.getIntent();

        judul.setText(passing.getStringExtra("TITLE"));
        ayat.setText(passing.getStringExtra("ARABIC"));
        translate.setText(passing.getStringExtra("TRANSLATE"));
        arti.setText(passing.getStringExtra("ARTI"));
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
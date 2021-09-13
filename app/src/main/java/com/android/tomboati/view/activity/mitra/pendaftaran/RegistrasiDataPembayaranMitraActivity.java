package com.android.tomboati.view.activity.mitra.pendaftaran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.android.tomboati.R;
import com.android.tomboati.model.AkunModel;

public class RegistrasiDataPembayaranMitraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_registrasi_data_pembayaran_mitra);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Registrasi Data Lanjutan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        final AkunModel OBJECT = (AkunModel) getIntent().getSerializableExtra("OBJECT");





    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
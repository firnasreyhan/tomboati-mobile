package com.android.tomboati.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.tomboati.R;
import com.android.tomboati.view.activity.pendaftaran.PendaftaranDataDiriActivity;
import com.google.android.material.button.MaterialButton;

public class SyaratActivity extends AppCompatActivity {
    private MaterialButton materialButtonSetujuDanLanjutkan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syarat);

        String idPaket = getIntent().getStringExtra("ID_PAKET");
        String tanggalKeberangkatan = getIntent().getStringExtra("TANGGAL_BERANGKAT");
        String sheet = getIntent().getStringExtra("SHEET");
        String sheetHarga = getIntent().getStringExtra("SHEET_HARGA");

        materialButtonSetujuDanLanjutkan = findViewById(R.id.materialButtonSetujuDanLanjutkan);

        materialButtonSetujuDanLanjutkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PendaftaranDataDiriActivity.class);
                intent.putExtra("ID_PAKET", idPaket);
                intent.putExtra("TANGGAL_BERANGKAT", tanggalKeberangkatan);
                intent.putExtra("SHEET", sheet);
                intent.putExtra("SHEET_HARGA", sheetHarga);
                startActivity(intent);
            }
        });
    }
}
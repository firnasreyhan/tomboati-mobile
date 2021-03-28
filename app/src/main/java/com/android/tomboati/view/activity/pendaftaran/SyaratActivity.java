package com.android.tomboati.view.activity.pendaftaran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.tomboati.R;
import com.android.tomboati.model.PesananaModel;
import com.android.tomboati.view.activity.pendaftaran.PendaftaranDataDiriActivity;
import com.google.android.material.button.MaterialButton;

import java.io.Serializable;

public class SyaratActivity extends AppCompatActivity {

    private MaterialButton materialButtonSetujuDanLanjutkan;
    private PesananaModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syarat);

        String idPaket = getIntent().getStringExtra("ID_PAKET");
        String tanggalKeberangkatan = getIntent().getStringExtra("TANGGAL_BERANGKAT");
        String sheet = getIntent().getStringExtra("SHEET");
        String sheetHarga = getIntent().getStringExtra("SHEET_HARGA");

        model = new PesananaModel();

        materialButtonSetujuDanLanjutkan = findViewById(R.id.materialButtonSetujuDanLanjutkan);

        materialButtonSetujuDanLanjutkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.setIdPaket(idPaket);
                model.setTanggalBerangkat(tanggalKeberangkatan);
                model.setSheet(sheet);
                model.setSheetHarga(sheetHarga);

                Intent intent = new Intent(v.getContext(), PendaftaranDataDiriActivity.class);
                intent.putExtra("OBJECT", (Serializable) model);
                startActivity(intent);
            }
        });
    }
}
package com.tomboati.tour.view.activity.pendaftaran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tomboati.tour.R;
import com.tomboati.tour.model.PesananaModel;
import com.tomboati.tour.view.activity.pembayaran.FormPembayaranActivity;
import com.google.android.material.button.MaterialButton;

import java.io.Serializable;

public class Syarat2Activity extends AppCompatActivity {

    private PesananaModel model;
    private MaterialButton materialButtonSetujuDanLanjutkan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_syarat2);

        model = (PesananaModel) getIntent().getSerializableExtra("OBJECT");
        materialButtonSetujuDanLanjutkan = findViewById(R.id.materialButtonSetujuDanLanjutkan);

        final String ID_TRANSAKSI = getIntent().getStringExtra("ID_TRANSAKSI");

        materialButtonSetujuDanLanjutkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                if(ID_TRANSAKSI == null) {
                    intent = new Intent(v.getContext(), DrawTandaTanganPendaftaran.class);
                    intent.putExtra("OBJECT", (Serializable) model);
                    startActivity(intent);
                } else {
                    intent = new Intent(v.getContext(), FormPembayaranActivity.class);
                    intent.putExtra("ID_TRANSAKSI", ID_TRANSAKSI);
                }
                startActivity(intent);
            }
        });



















    }

}
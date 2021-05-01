package com.android.tomboati.view.activity.pendaftaran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.tomboati.R;
import com.android.tomboati.model.PesananaModel;
import com.android.tomboati.view.activity.pembayaran.FormPembayaranActivity;
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
                finish();
                startActivity(intent);
            }
        });



















    }
}
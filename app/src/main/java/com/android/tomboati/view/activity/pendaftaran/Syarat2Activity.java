package com.android.tomboati.view.activity.pendaftaran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.tomboati.R;
import com.android.tomboati.model.PesananaModel;
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

        materialButtonSetujuDanLanjutkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DrawTandaTanganPendaftaran.class);
                intent.putExtra("OBJECT", (Serializable) model);
                startActivity(intent);
            }
        });



















    }
}
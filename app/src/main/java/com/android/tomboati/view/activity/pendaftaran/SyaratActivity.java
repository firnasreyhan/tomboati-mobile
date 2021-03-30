package com.android.tomboati.view.activity.pendaftaran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.tomboati.R;
import com.android.tomboati.model.DataHajiBadalModel;
import com.android.tomboati.model.PesananaModel;
import com.android.tomboati.preference.AppPreference;
import com.android.tomboati.view.activity.pendaftaran.PendaftaranDataDiriActivity;
import com.google.android.material.button.MaterialButton;

import java.io.Serializable;

public class SyaratActivity extends AppCompatActivity {

    private MaterialButton materialButtonSetujuDanLanjutkan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_syarat);

        String idPaket = getIntent().getStringExtra("ID_PAKET");
        String tanggalKeberangkatan = getIntent().getStringExtra("TANGGAL_BERANGKAT");
        String sheet = getIntent().getStringExtra("SHEET");
        String sheetHarga = getIntent().getStringExtra("SHEET_HARGA");
        boolean isPaketHaji = getIntent().getBooleanExtra("IS_PAKET_HAJI", false);
        boolean isPaketHajiBadal = getIntent().getBooleanExtra("IS_PAKET_HAJI_BADAL", false);


        materialButtonSetujuDanLanjutkan = findViewById(R.id.materialButtonSetujuDanLanjutkan);

        materialButtonSetujuDanLanjutkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPaketHaji) {
                    if(AppPreference.getUser(v.getContext()) != null) {
                        if(isPaketHajiBadal) {
                            final DataHajiBadalModel model = new DataHajiBadalModel();
                            model.setIdPaket(idPaket);
                            Intent intent = new Intent(v.getContext(), PendaftaranBadalHajiActivity.class);
                            intent.putExtra("OBJECT", (Serializable) model);
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(v.getContext(), PendaftaranHajiRegulerActivity.class);
                            intent.putExtra("NAMA_PAKET", getIntent().getStringExtra("NAMA_PAKET"));
                            startActivity(intent);
                        }
                    } else {
                        Toast.makeText(v.getContext(), "Mohon login terlebih dahulu", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    final PesananaModel model = new PesananaModel();
                    model.setIdPaket(idPaket);
                    model.setTanggalBerangkat(tanggalKeberangkatan);
                    model.setSheet(sheet);
                    model.setSheetHarga(sheetHarga);

                    Intent intent = new Intent(v.getContext(), PendaftaranDataDiriActivity.class);
                    intent.putExtra("OBJECT", (Serializable) model);
                    startActivity(intent);
                }
            }
        });
    }
}
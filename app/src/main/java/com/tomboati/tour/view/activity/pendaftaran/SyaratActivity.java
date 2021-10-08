package com.tomboati.tour.view.activity.pendaftaran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.tomboati.tour.R;
import com.tomboati.tour.model.DataHajiBadalModel;
import com.tomboati.tour.model.PesananaModel;
import com.tomboati.tour.preference.PreferenceAkun;
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
        String idPaketWisata = getIntent().getStringExtra("ID_PAKET_WISATA");
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
                    if(PreferenceAkun.getAkun(v.getContext()).isFieldFilled()) {
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
                        finish();
                    } else {
                        Toast.makeText(v.getContext(), "Mohon lengkapi data diri terlebih dahulu", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    final PesananaModel model = new PesananaModel();
                    model.setPaketWisata(idPaketWisata != null);
                    model.setIdPaket((idPaket != null) ? idPaket : idPaketWisata);
                    model.setTanggalBerangkat(tanggalKeberangkatan);
                    model.setSheet(sheet);
                    model.setSheetHarga(sheetHarga);

                    Intent intent = new Intent(v.getContext(), PendaftaranDataDiriActivity.class);
                    intent.putExtra("OBJECT", (Serializable) model);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
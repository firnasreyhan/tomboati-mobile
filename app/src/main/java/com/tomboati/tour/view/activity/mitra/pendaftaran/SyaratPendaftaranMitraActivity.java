package com.tomboati.tour.view.activity.mitra.pendaftaran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.tomboati.tour.R;
import com.tomboati.tour.preference.PreferenceAkun;
import com.google.android.material.button.MaterialButton;

public class SyaratPendaftaranMitraActivity extends AppCompatActivity {

    private MaterialButton materialButtonSetujuDanLanjutkan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syarat_pendaftaran_mitra);

        final boolean isDataDiriFilled = PreferenceAkun.getAkun(this).isFieldFilled();

        materialButtonSetujuDanLanjutkan = findViewById(R.id.materialButtonSetujuDanLanjutkan);

        materialButtonSetujuDanLanjutkan.setOnClickListener(v ->{
            final Class classIntent = isDataDiriFilled ?
                RegistrasiDataPembayaranMitraActivity.class
                    :
                RegistrasiDataDiriMitraActivity.class
            ;

            startActivity(new Intent(v.getContext(), classIntent));
        });

    }
}
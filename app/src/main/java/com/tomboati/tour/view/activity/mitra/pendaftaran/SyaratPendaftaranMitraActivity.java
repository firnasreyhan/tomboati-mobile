package com.tomboati.tour.view.activity.mitra.pendaftaran;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tomboati.tour.databinding.ActivitySyaratPendaftaranMitraBinding;
import com.tomboati.tour.preference.PreferenceAkun;
import com.tomboati.tour.view.activity.base.BaseNonToolbarActivity;

public class SyaratPendaftaranMitraActivity extends BaseNonToolbarActivity {

    private ActivitySyaratPendaftaranMitraBinding bind;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        bind = ActivitySyaratPendaftaranMitraBinding.inflate(getLayoutInflater());
        final boolean isDataDiriFilled = PreferenceAkun.getAkun(this).isFieldFilled();
        bind.materialButtonSetujuDanLanjutkan.setOnClickListener(v ->{
            final Class<?> classIntent = isDataDiriFilled ?
                RegistrasiDataPembayaranMitraActivity.class
                    :
                RegistrasiDataDiriMitraActivity.class;
            startsActivity(classIntent);
        });
    }

    @Override
    protected View getContentView() {
        return bind.getRoot();
    }
}
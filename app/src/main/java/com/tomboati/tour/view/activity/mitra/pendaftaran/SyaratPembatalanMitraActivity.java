package com.tomboati.tour.view.activity.mitra.pendaftaran;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tomboati.tour.databinding.ActivitySyaratPembatalanMitraBinding;
import com.tomboati.tour.model.AkunModel;
import com.tomboati.tour.preference.PreferenceAkun;
import com.tomboati.tour.utils.AlertInfo;
import com.tomboati.tour.view.activity.base.BaseNonToolbarActivity;
import com.tomboati.tour.view.activity.homepage.MainActivity;
import com.tomboati.tour.viewmodel.tomboati.mitra.RegisterAkunMitraViewModel;

public class SyaratPembatalanMitraActivity extends BaseNonToolbarActivity {

    private ActivitySyaratPembatalanMitraBinding bind;
    private RegisterAkunMitraViewModel viewModel;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        bind = ActivitySyaratPembatalanMitraBinding.inflate(getLayoutInflater());
        viewModel = ViewModelProviders.of(this).get(RegisterAkunMitraViewModel.class);

        final AkunModel akunModel = PreferenceAkun.getAkun(this);

        bind.materialButtonSetujuDanDaftar.setOnClickListener(v -> {
            showProgressDialog("Sedang mengirimkan data...");
            viewModel.registerMitra(akunModel).observe(getOwner(), baseResponse -> {
                if (baseResponse != null) {
                    if (baseResponse.isError()) {
                        dismissProgressDialog();
                        AlertInfo info = new AlertInfo(v, baseResponse.getMessage());
                        info.setDialogError();
                        info.showDialog();
                    } else {
                        viewModel.registerDataDiri(akunModel).observe(getOwner(), baseResponse1 -> {
                            AlertInfo info1 = null;
                            dismissProgressDialog();
                            if (baseResponse1 != null) {
                                if (baseResponse1.isError()) {
                                    info1 = new AlertInfo(v, baseResponse1.getMessage());
                                    info1.setDialogError();
                                } else {
                                    akunModel.setSuksesDaftarMitra(true);
                                    PreferenceAkun.removeAkun(v.getContext());
                                    PreferenceAkun.setAkun(v.getContext(), akunModel);

                                    Intent intent1 = new Intent(v.getContext(), MainActivity.class);
                                    intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    info1 = new AlertInfo(SyaratPembatalanMitraActivity.this,
                                            "Berhasil mengirimkan data, " +
                                                    "mohon tunggu validasi dari admin, dan selalu cek email anda untuk " +
                                                    "melihat informasi akun yang admin kirimkan kepada email anda", intent1);
                                }
                            } else {
                                info1 = new AlertInfo(v, "Connection Timeout!!, Cek " +
                                        "koneksi anda dan silahkan " +
                                        "mengulangi pendaftaran kembali");
                                info1.setDialogError();
                            }
                            info1.showDialog();
                        });
                    }
                } else {
                    dismissProgressDialog();
                    AlertInfo info = new AlertInfo(v, "Connection Timeout!!, Cek " +
                            "koneksi anda dan silahkan " +
                            "mengulangi pendaftaran kembali");
                    info.setDialogError();
                    info.showDialog();
                }

            });
        });

    }

    @Override
    protected View getContentView() {
        return bind.getRoot();
    }
}
package com.android.tomboati.view.activity.mitra.pendaftaran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import com.android.tomboati.R;
import com.android.tomboati.api.response.BaseResponse;
import com.android.tomboati.model.AkunModel;
import com.android.tomboati.preference.PreferenceAkun;
import com.android.tomboati.utils.AlertInfo;
import com.android.tomboati.utils.AlertProgress;
import com.android.tomboati.view.activity.homepage.MainActivity;
import com.android.tomboati.viewmodel.tomboati.mitra.RegisterAkunMitraViewModel;
import com.google.android.material.button.MaterialButton;

public class SyaratPembatalanMitraActivity extends AppCompatActivity {

    private MaterialButton materialButtonSetujuDanDaftar;
    private RegisterAkunMitraViewModel viewModel;
    private final LifecycleOwner OWNER = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syarat_pembatalan_mitra);

        viewModel = ViewModelProviders.of(this).get(RegisterAkunMitraViewModel.class);

        final AkunModel akunModel = PreferenceAkun.getAkun(this);
        
        final AlertProgress progress = new AlertProgress(this, "Sedang mengirimkan data");


        materialButtonSetujuDanDaftar = findViewById(R.id.materialButtonSetujuDanDaftar);
        materialButtonSetujuDanDaftar.setOnClickListener(v -> {
            progress.showDialog();
            viewModel.registerMitra(akunModel).observe(OWNER, baseResponse -> {
                if(progress.isDialogShowing()) {
                    progress.dismissDialog();
                }
                if(baseResponse.isError()) {
                    AlertInfo info = new AlertInfo(v, baseResponse.getMessage());
                    info.setDialogError();
                    info.showDialog();
                } else {
                    akunModel.setSuksesDaftarMitra(true);
                    PreferenceAkun.removeAkun(v.getContext());
                    PreferenceAkun.setAkun(v.getContext(), akunModel);
                    viewModel.registerDataDiri(akunModel).observe(OWNER, new Observer<BaseResponse>() {
                        @Override
                        public void onChanged(BaseResponse baseResponse) {
                            final AlertInfo info;
                            if(baseResponse.isError()) {
                                info = new AlertInfo(v, baseResponse.getMessage());
                                info.setDialogError();
                                info.showDialog();
                            } else {
                                Intent intent = new Intent(v.getContext(), MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                info = new AlertInfo(SyaratPembatalanMitraActivity.this, "Berhasil mengirimkan data, " +
                                        "mohon tunggu validasi dari admin, dan cek selalu email anda untuk " +
                                        "melihat informasi akun yang admin kirimkan kepada email anda", intent);
                            }
                            info.showDialog();
                        }
                    });
                }
            });
        });
    }
}
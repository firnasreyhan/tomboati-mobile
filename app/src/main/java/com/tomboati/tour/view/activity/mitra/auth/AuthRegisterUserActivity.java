package com.tomboati.tour.view.activity.mitra.auth;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.installations.remote.TokenResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.tomboati.tour.R;
import com.tomboati.tour.api.response.AkunResponse;
import com.tomboati.tour.databinding.ActivityAuthRegisterUserBinding;
import com.tomboati.tour.model.AkunModel;
import com.tomboati.tour.model.LoginModel;
import com.tomboati.tour.preference.AppPreference;
import com.tomboati.tour.preference.PreferenceAkun;
import com.tomboati.tour.utils.AlertInfo;
import com.tomboati.tour.view.activity.base.BaseNonToolbarActivity;
import com.tomboati.tour.view.activity.homepage.MainActivity;
import com.tomboati.tour.viewmodel.tomboati.mitra.RegisterAkunMitraViewModel;

public class AuthRegisterUserActivity extends BaseNonToolbarActivity implements OnCompleteListener<TokenResult> {

    private ActivityAuthRegisterUserBinding bind;
    private RegisterAkunMitraViewModel viewModel;
    private String token = null;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        bind = ActivityAuthRegisterUserBinding.inflate(getLayoutInflater());
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(this::onComplete);
        viewModel = ViewModelProviders.of(this).get(RegisterAkunMitraViewModel.class);

        final LoginModel LOGIN = new LoginModel();
        bind.setLogin(LOGIN);

        final String REFERRAL = intent.getStringExtra("REFERRAL");
        if(REFERRAL != null) {
            bind.editTextDaftarKodeReferral.setText(REFERRAL);
            bind.editTextDaftarKodeReferral.setBackgroundColor(getResources().getColor(R.color.background));
            bind.editTextDaftarKodeReferral.setEnabled(false);
        }

        bind.materialButtonLanjutkan.setOnClickListener(v -> {
            if(LOGIN.getUsername().isEmpty() && LOGIN.getPassword().isEmpty()) {
                bind.editTextDaftarNomorTelepon.setError("Wajib diisi!");
                bind.editTextDaftarKodeReferral.setError("Wajib diisi!");
                showToast("Mohon isi nomor telepon dan referral terlebih dahulu");
            } else if(LOGIN.getUsername().isEmpty()) {
                showToast("Nomor telepon masih kosong!");
                bind.editTextDaftarNomorTelepon.setError("Wajib diisi!");
            } else if(LOGIN.getPassword().isEmpty()) {
                showToast("Kode referral masih kosong!");
                bind.editTextDaftarKodeReferral.setError("Wajib diisi!");
            }else if(LOGIN.getUsername().length() > 13) {
                showToast("Nomor telepon tidak boleh lebih dari 13 angka!");
                bind.editTextDaftarNomorTelepon.requestFocus();
            } else {
                if(token != null) {
                    showProgressDialog("Sedang meregistrasi data...");

                    String noTelp = LOGIN.getUsername();
                    if (noTelp.charAt(0) == '0') {
                        noTelp = noTelp.replaceFirst("0", "62");
                    } else if (noTelp.charAt(0) == '+') {
                        noTelp = noTelp.replaceFirst("\\+", "");
                    }

                    viewModel.registerAkun(
                            noTelp, LOGIN.getPassword(), token
                    ).observe(getOwner(), akunResponse -> {
                        dismissProgressDialog();
                        if (!akunResponse.isError()) {

                            AppPreference.removeNotif(v.getContext());
                            final AkunResponse.DataDbDashTombo dataDashTombo = akunResponse.getDataDbDashTombo();
                            final AkunResponse.DataTomboati dataTomboAti = akunResponse.getDataTomboati();

                            final AkunModel akunModel = new AkunModel();
                            akunModel.setId(dataTomboAti.getIDUSERREGISTER());
                            akunModel.setPaket(dataDashTombo.getPaket());
                            akunModel.setHphone(dataDashTombo.getHphone());
                            akunModel.setReferral(dataDashTombo.getSponsor());
                            akunModel.setIdChatRoom(dataTomboAti.getIDCHATROOM());

                            if(dataDashTombo.getName() != null) {
                                akunModel.setName(dataDashTombo.getName().toString());
                                akunModel.setPropinsi(dataDashTombo.getPropinsi().toString());
                                akunModel.setKota(dataDashTombo.getKota().toString());
                                akunModel.setKecamatan(dataDashTombo.getKecamatan().toString());
                                akunModel.setAddress(dataDashTombo.getAddress().toString());
                                akunModel.setKodePos(dataDashTombo.getKodePos().toString());
                            }

                            if(dataDashTombo.getPhoto() != null && dataDashTombo.getBuktiBayar() != null) {
                                akunModel.setPhoto(dataDashTombo.getPhoto().toString());
                                akunModel.setSuksesDaftarMitra(true);
                            }

                            PreferenceAkun.setAkun(v.getContext(), akunModel);

                            Intent intents = new Intent(v.getContext(), MainActivity.class);
                            intents.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intents);
                            showToast(akunResponse.getMessage());
                        } else {
                            AlertInfo info = new AlertInfo(v, akunResponse.getMessage());
                            info.setDialogError();
                            info.showDialog();
                        }
                    });
                } else {
                    showToast("Tunggu sebentar, token masih dimuat!");
                }
            }
        });

        bind.textViewMasuk.setOnClickListener(v -> startsActivity(AuthLoginMitraActivity.class));

    }

    @Override
    protected View getContentView() {
        return bind.getRoot();
    }

    @Override
    public void onComplete(@NonNull Task task) {
        this.token = task.getResult().toString();
    }
}
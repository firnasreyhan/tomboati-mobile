package com.tomboati.tour.view.activity.mitra.auth;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.installations.remote.TokenResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.tomboati.tour.R;
import com.tomboati.tour.api.response.AkunMitraResponse;
import com.tomboati.tour.databinding.ActivityAuthLoginMitraBinding;
import com.tomboati.tour.model.AkunModel;
import com.tomboati.tour.model.LoginModel;
import com.tomboati.tour.preference.AppPreference;
import com.tomboati.tour.preference.PreferenceAkun;
import com.tomboati.tour.utils.AlertInfo;
import com.tomboati.tour.view.activity.base.BaseNonToolbarActivity;
import com.tomboati.tour.view.activity.homepage.MainActivity;
import com.tomboati.tour.viewmodel.tomboati.mitra.LoginAkunMitraViewModel;

public class AuthLoginMitraActivity extends BaseNonToolbarActivity implements OnCompleteListener<TokenResult> {

    private ActivityAuthLoginMitraBinding bind;
    private LoginAkunMitraViewModel viewModel;
    private boolean isEyePassword;
    private String token = null;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        bind = ActivityAuthLoginMitraBinding.inflate(getLayoutInflater());
        viewModel = ViewModelProviders.of(this).get(LoginAkunMitraViewModel.class);
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(this::onComplete);

        final LoginModel LOGIN = new LoginModel();
        bind.setLogin(LOGIN);

        bind.eyePassword.setOnClickListener(v -> {
            setEye(bind.editTextLoginPassword, bind.eyePassword, isEyePassword);
            isEyePassword = !isEyePassword;
        });

        if(PreferenceAkun.getAkun(this) == null){
            bind.textViewBack.setText("Daftar");
            bind.textViewPrefix.setVisibility(View.VISIBLE);
        }

        bind.materialButtonMasuk.setOnClickListener(v -> {
            if(LOGIN.getUsername().isEmpty()) {
                bind.editTextLoginUsername.setError("Wajib diisi");
                showToast("Kolom username masih kosong!");
            } else if(LOGIN.getPassword().isEmpty()) {
                bind.editTextLoginPassword.setError("Wajib diisi");
                showToast("Kolom password masih kosong!");
            } else {
                if(token != null) {
                    Log.d("TOKEN", "onClick: " + token);
                    showProgressDialog("Sedang mengautentikasi data...");
                    viewModel.loginMitra(
                            LOGIN.getUsername(), LOGIN.getPassword(), token
                    ).observe(getOwner(), akunMitraResponse -> {
                        dismissProgressDialog();
                        if (akunMitraResponse.isError()) {
                            AlertInfo info = new AlertInfo(v, akunMitraResponse.getMessage());
                            info.setDialogError();
                            info.showDialog();
                        } else {
                            AppPreference.removeNotif(v.getContext());
                            AkunMitraResponse.Data data = akunMitraResponse.getData();
                            AkunModel akunModel = new AkunModel();
                            akunModel.setId(data.getIDUSERREGISTER());
                            akunModel.setPaket(data.getSTATUSUSER());
                            akunModel.setKtp(data.getNOMORKTP());
                            akunModel.setEmail(data.getEMAIL());
                            akunModel.setName(data.getNAMALENGKAP());
                            akunModel.setReferral(data.getKODEREFERRAL());
                            akunModel.setHphone(data.getNOMORHP());
                            akunModel.setFotoKTP(data.getFILEKTP());
                            akunModel.setPhoto(data.getFOTO());
                            akunModel.setUserId(data.getUSERNAME());
                            akunModel.setKecamatan(data.getKECAMATAN());
                            akunModel.setAddress(data.getALAMAT());
                            akunModel.setPropinsi(data.getPROVINSI());
                            akunModel.setKodePos(data.getKODEPOS());
                            akunModel.setKota(data.getKOTA());
                            akunModel.setIdChatRoom(data.getIDCHATROOM());
                            PreferenceAkun.removeAkun(v.getContext());
                            PreferenceAkun.setAkun(v.getContext(), akunModel);

                            Intent intents = new Intent(v.getContext(), MainActivity.class);
                            intents.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intents);
                            showToast("Selamat datang..");
                        }
                    });
                } else {
                    showToast("Tunggu sebentar, token masih dimuat!");
                }
            }
        });

        bind.textViewBack.setOnClickListener(v -> finish());

    }

    @Override
    protected View getContentView() {
        return bind.getRoot();
    }

    private void setEye(EditText editText, ImageView eyeImageView, boolean isEyeClicked) {
        if(!isEyeClicked) {
            editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            eyeImageView.setImageResource(R.drawable.ic_eye_invisible);
        } else {
            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            eyeImageView.setImageResource(R.drawable.ic_eye_visible);
        }
    }

    @Override
    public void onComplete(@NonNull Task task) {
        this.token = task.getResult().toString();
    }
}
package com.tomboati.tour.view.activity.mitra.auth;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.tomboati.tour.R;
import com.tomboati.tour.databinding.ActivityUbahPasswordBinding;
import com.tomboati.tour.model.UbahPasswordModel;
import com.tomboati.tour.utils.AlertInfo;
import com.tomboati.tour.view.activity.base.BaseNonToolbarActivity;
import com.tomboati.tour.view.activity.homepage.MainActivity;
import com.tomboati.tour.viewmodel.tomboati.mitra.UbahPasswordViewModel;

public class UbahPasswordActivity extends BaseNonToolbarActivity {

    private ActivityUbahPasswordBinding bind;
    private boolean isEyePasswordLama, isEyePasswordBaru, isEyeUlangiPasswordBaru;
    private UbahPasswordViewModel viewModel;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        bind = ActivityUbahPasswordBinding.inflate(getLayoutInflater());
        viewModel = ViewModelProviders.of(this).get(UbahPasswordViewModel.class);

        final UbahPasswordModel PASS = new UbahPasswordModel();
        bind.setPass(PASS);

        bind.eyePasswordLama.setOnClickListener(v -> {
            setEye(bind.editTextPasswordLama, bind.eyePasswordLama, isEyePasswordLama);
            isEyePasswordLama = !isEyePasswordLama;
        });

        bind.eyePasswordBaru.setOnClickListener(v -> {
            setEye(bind.editTextPasswordBaru, bind.eyePasswordBaru, isEyePasswordBaru);
            isEyePasswordBaru = !isEyePasswordBaru;
        });

        bind.eyeUlangiPasswordBaru.setOnClickListener(v -> {
            setEye(bind.editTextUlangiPasswordBaru, bind.eyeUlangiPasswordBaru, isEyeUlangiPasswordBaru);
            isEyeUlangiPasswordBaru = !isEyeUlangiPasswordBaru;
        });

        bind.textKembali.setOnClickListener(v -> finish());

        bind.materialButtonUbahPassword.setOnClickListener(v -> {
            if(PASS.getPasswordNow().isEmpty()) {
                showToast("Password lama masih kosong!");
            } else if(PASS.getPasswordNew().isEmpty()) {
                showToast("Password baru masih kosong!");
            } else if(PASS.getPasswordNewRepeat().isEmpty()) {
                showToast("Ulangi password baru masih kosong!");
            } else {
                showProgressDialog("Sedang menyimpan data..");
                viewModel.gantiPassword(PASS).observe(getOwner(), baseResponse -> {
                    dismissProgressDialog();
                    AlertInfo info;
                    if(baseResponse.isError()) {
                        info = new AlertInfo(v, baseResponse.getMessage());
                        info.setDialogError();
                    } else {
                        Intent intents = new Intent(v.getContext(), MainActivity.class);
                        intents.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        info = new AlertInfo(UbahPasswordActivity.this, baseResponse.getMessage(), intents);
                    }
                    info.showDialog();
                });
            }
        });
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
}
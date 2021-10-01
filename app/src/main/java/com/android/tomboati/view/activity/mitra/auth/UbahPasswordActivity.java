package com.android.tomboati.view.activity.mitra.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.tomboati.R;
import com.android.tomboati.api.response.BaseResponse;
import com.android.tomboati.utils.AlertInfo;
import com.android.tomboati.utils.AlertProgress;
import com.android.tomboati.view.activity.homepage.MainActivity;
import com.android.tomboati.viewmodel.tomboati.mitra.UbahPasswordViewModel;
import com.google.android.material.button.MaterialButton;

public class UbahPasswordActivity extends AppCompatActivity {

    private EditText editTextPasswordLama, editTextPasswordBaru, editTextUlangiPasswordBaru;
    private ImageView eyePasswordLama, eyePasswordBaru, eyePasswordUlangiPasswordBaru;
    private TextView textKembali;
    private MaterialButton materialButtonUbahPassword;
    private boolean isEyePasswordLama, isEyePasswordBaru, isEyeUlangiPasswordBaru;

    private UbahPasswordViewModel viewModel;
    private final LifecycleOwner OWNER = this;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_ubah_password);

        viewModel = ViewModelProviders.of(this).get(UbahPasswordViewModel.class);

        editTextPasswordLama = findViewById(R.id.editTextPasswordLama);
        editTextPasswordBaru = findViewById(R.id.editTextPasswordBaru);
        editTextUlangiPasswordBaru = findViewById(R.id.editTextUlangiPasswordBaru);
        eyePasswordLama = findViewById(R.id.eyePasswordLama);
        eyePasswordBaru = findViewById(R.id.eyePasswordBaru);
        eyePasswordUlangiPasswordBaru = findViewById(R.id.eyeUlangiPasswordBaru);
        textKembali = findViewById(R.id.textKembali);
        materialButtonUbahPassword = findViewById(R.id.materialButtonUbahPassword);

        eyePasswordLama.setOnClickListener(v -> {
            setEye(editTextPasswordLama, eyePasswordLama, isEyePasswordLama);
            isEyePasswordLama = !isEyePasswordLama;
        });

        eyePasswordBaru.setOnClickListener(v -> {
            setEye(editTextPasswordBaru, eyePasswordBaru, isEyePasswordBaru);
            isEyePasswordBaru = !isEyePasswordBaru;
        });

        eyePasswordUlangiPasswordBaru.setOnClickListener(v -> {
            setEye(editTextUlangiPasswordBaru, eyePasswordUlangiPasswordBaru, isEyeUlangiPasswordBaru);
            isEyeUlangiPasswordBaru = !isEyeUlangiPasswordBaru;
        });

        textKembali.setOnClickListener(v -> {
            finish();
        });

        materialButtonUbahPassword.setOnClickListener(v -> {
            if(editTextPasswordLama.getText().toString().isEmpty()) {
                Toast.makeText(v.getContext(), "Password lama masih kosong!", Toast.LENGTH_SHORT).show();
            } else if(editTextPasswordBaru.getText().toString().isEmpty()) {
                Toast.makeText(v.getContext(), "Password baru masih kosong!", Toast.LENGTH_SHORT).show();
            } else if(editTextUlangiPasswordBaru.getText().toString().isEmpty()) {
                Toast.makeText(v.getContext(), "Ulangi password baru masih kosong!", Toast.LENGTH_SHORT).show();
            } else {
                AlertProgress progress = new AlertProgress(v, "Sedang menyimpan data..");
                progress.showDialog();
                viewModel.gantiPassword(
                        editTextPasswordLama.getText().toString(),
                        editTextPasswordBaru.getText().toString(),
                        editTextUlangiPasswordBaru.getText().toString()
                ).observe(OWNER, new Observer<BaseResponse>() {
                    @Override
                    public void onChanged(BaseResponse baseResponse) {
                        progress.dismissDialog();
                        AlertInfo info;
                        if(baseResponse.isError()) {
                            info = new AlertInfo(v, baseResponse.getMessage());
                            info.setDialogError();
                        } else {
                            Intent intent = new Intent(v.getContext(), MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            info = new AlertInfo(UbahPasswordActivity.this, baseResponse.getMessage(), intent);
                        }
                        info.showDialog();
                    }
                });
            }
        });

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
package com.tomboati.tour.view.activity.mitra.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.tomboati.tour.R;
import com.tomboati.tour.api.response.AkunMitraResponse;
import com.tomboati.tour.model.AkunModel;
import com.tomboati.tour.preference.PreferenceAkun;
import com.tomboati.tour.utils.AlertInfo;
import com.tomboati.tour.utils.AlertProgress;
import com.tomboati.tour.view.activity.homepage.MainActivity;
import com.tomboati.tour.viewmodel.tomboati.mitra.LoginAkunMitraViewModel;
import com.google.android.material.button.MaterialButton;

public class AuthLoginMitraActivity extends AppCompatActivity implements OnCompleteListener {

    private TextView textViewBack, textViewPrefix;
    private EditText editTextLoginUsername, editTextLoginPassword;
    private MaterialButton materialButtonMasuk;
    private LoginAkunMitraViewModel viewModel;
    private ImageView eyePassword;
    private boolean isEyePassword;

    private final LifecycleOwner OWNER = this;
    private String token = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_auth_login_mitra);

        viewModel = ViewModelProviders.of(this).get(LoginAkunMitraViewModel.class);

        textViewBack = findViewById(R.id.textViewBack);
        textViewPrefix = findViewById(R.id.textViewPrefix);
        materialButtonMasuk = findViewById(R.id.materialButtonMasuk);
        editTextLoginUsername = findViewById(R.id.editTextLoginUsername);
        editTextLoginPassword = findViewById(R.id.editTextLoginPassword);
        eyePassword = findViewById(R.id.eyePassword);
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(this);
        eyePassword.setOnClickListener(v -> {
            setEye(editTextLoginPassword, eyePassword, isEyePassword);
            isEyePassword = !isEyePassword;
        });

        if(PreferenceAkun.getAkun(this) == null){
            textViewBack.setText("Daftar");
            textViewPrefix.setVisibility(View.VISIBLE);
        }

        materialButtonMasuk.setOnClickListener(v -> {
            if(editTextLoginUsername.getText().toString().isEmpty()) {
                editTextLoginUsername.setError("Wajib diisi");
                Toast.makeText(v.getContext(), "Kolom email masih kosong!", Toast.LENGTH_SHORT).show();
            } else if(editTextLoginPassword.getText().toString().isEmpty()) {
                editTextLoginPassword.setError("Wajib diisi");
                Toast.makeText(v.getContext(), "Kolom password masih kosong!", Toast.LENGTH_SHORT).show();
            } else {
                if(token != null) {
                    Log.d("TOKEN", "onClick: " + token);
                    AlertProgress progress = new AlertProgress(v, "Sedang mengautentikasi data..");
                    progress.showDialog();
                    viewModel.loginMitra(
                            editTextLoginUsername.getText().toString(),
                            editTextLoginPassword.getText().toString(),
                            token
                    ).observe(OWNER, akunMitraResponse -> {
                        progress.dismissDialog();
                        if (akunMitraResponse.getError()) {
                            AlertInfo info = new AlertInfo(v, akunMitraResponse.getMessage());
                            info.setDialogError();
                            info.showDialog();
                        } else {
                            AkunMitraResponse.Datum data = akunMitraResponse.getData().get(0);
                            AkunModel akunModel = new AkunModel();
                            akunModel.setPoin(data.getPoin().toString());
                            akunModel.setId(data.getIduserregister());
                            akunModel.setPaket(data.getStatusUser());
                            akunModel.setKtp(data.getNomorktp());
                            akunModel.setEmail(data.getEmail());
                            akunModel.setName(data.getNamalengkap());
                            akunModel.setReferral(data.getKodereferral());
                            akunModel.setHphone(data.getNomorhp());
                            akunModel.setFotoKTP(data.getFilektp());
                            akunModel.setPhoto(data.getFoto());
                            akunModel.setUserId(data.getUsername());
                            akunModel.setKecamatan(data.getKecamatan());
                            akunModel.setAddress(data.getAlamat());
                            akunModel.setPropinsi(data.getProvinsi());
                            akunModel.setKodePos(data.getKodepos());
                            akunModel.setKota(data.getKota());
                            akunModel.setIdChatRoom(data.getIdChatRoom());
                            PreferenceAkun.removeAkun(v.getContext());
                            PreferenceAkun.setAkun(v.getContext(), akunModel);


                            Intent intent = new Intent(v.getContext(), MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);

                            Toast.makeText(v.getContext(), "Selamat Datang!", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(v.getContext(), "Tunggu sebentar, token masih dimuat!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        textViewBack.setOnClickListener(v -> finish());






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
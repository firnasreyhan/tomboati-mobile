package com.android.tomboati.view.activity.mitra.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.tomboati.R;
import com.android.tomboati.api.response.AkunMitraResponse;
import com.android.tomboati.model.AkunModel;
import com.android.tomboati.preference.PreferenceAkun;
import com.android.tomboati.utils.AlertInfo;
import com.android.tomboati.utils.AlertProgress;
import com.android.tomboati.view.activity.homepage.MainActivity;
import com.android.tomboati.viewmodel.tomboati.mitra.LoginAkunMitraViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class AuthLoginMitraActivity extends AppCompatActivity {

    private TextView textViewBack, textViewPrefix;
    private EditText editTextLoginUsername, editTextLoginPassword;
    private MaterialButton materialButtonMasuk;
    private LoginAkunMitraViewModel viewModel;

    private final LifecycleOwner OWNER = this;

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

        if(PreferenceAkun.getAkun(this) == null){
            textViewBack.setText("Daftar");
            textViewPrefix.setVisibility(View.VISIBLE);
        }

        materialButtonMasuk.setOnClickListener(v -> {
            AlertProgress progress = new AlertProgress(v, "Sedang mengautentikasi data..");
            progress.showDialog();
            if(editTextLoginUsername.getText().toString().isEmpty()) {
                editTextLoginUsername.setError("Wajib diisi");
                Toast.makeText(v.getContext(), "Kolom email masih kosong!", Toast.LENGTH_SHORT).show();
            } else if(editTextLoginPassword.getText().toString().isEmpty()) {
                editTextLoginPassword.setError("Wajib diisi");
                Toast.makeText(v.getContext(), "Kolom password masih kosong!", Toast.LENGTH_SHORT).show();
            } else {
                viewModel.loginMitra(
                        editTextLoginUsername.getText().toString(),
                        editTextLoginPassword.getText().toString()
                ).observe(OWNER, akunMitraResponse -> {
                    progress.dismissDialog();
                    if(akunMitraResponse.getError()) {
                        AlertInfo info = new AlertInfo(v, akunMitraResponse.getMessage());
                        info.setDialogError();
                        info.showDialog();
                    } else {
                        AkunMitraResponse.Datum data = akunMitraResponse.getData().get(0);
                        AkunModel akunModel = new AkunModel();
                        akunModel.setId(data.getIduserregister());
                        akunModel.setPaket(data.getStatusUser());
                        akunModel.setKtp(data.getNomorktp());
                        akunModel.setEmail(data.getEmail());
                        akunModel.setName(data.getNamalengkap());
                        akunModel.setReferral(data.getKodereferral());
                        akunModel.setHphone(data.getNomorhp());
                        akunModel.setFotoKTP(data.getFilektp());
                        akunModel.setPhoto(data.getFoto());
                        akunModel.setBuktiBayar(data.getBuktibayar());
                        akunModel.setUserId(data.getUsername());
                        akunModel.setKecamatan(data.getKecamatan());
                        akunModel.setAddress(data.getAlamat());
                        akunModel.setPropinsi(data.getProvinsi());
                        akunModel.setKodePos(data.getKodepos());
                        akunModel.setCabang(data.getCabang());
                        akunModel.setAtasNama(data.getAtasnama());
                        akunModel.setRekening(data.getRekening());
                        akunModel.setBank(data.getBank());
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
            }
        });

        textViewBack.setOnClickListener(v -> finish());






    }
}
package com.android.tomboati.view.activity.mitra.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.tomboati.R;
import com.android.tomboati.api.response.AkunResponse;
import com.android.tomboati.model.AkunModel;
import com.android.tomboati.preference.PreferenceAkun;
import com.android.tomboati.utils.AlertInfo;
import com.android.tomboati.utils.AlertProgress;
import com.android.tomboati.utils.notif.TokenFMS;
import com.android.tomboati.view.activity.homepage.MainActivity;
import com.android.tomboati.viewmodel.tomboati.mitra.RegisterAkunMitraViewModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

public class AuthRegisterUserActivity extends AppCompatActivity {

    private EditText editTextDaftarNomorTelepon, editTextDaftarKodeReferral;
    private TextView textViewMasuk;
    private MaterialButton materialButtonLanjutkan;
    private RegisterAkunMitraViewModel viewModel;
    private final LifecycleOwner OWNER = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_auth_register_user);

        editTextDaftarNomorTelepon = findViewById(R.id.editTextDaftarNomorTelepon);
        editTextDaftarKodeReferral = findViewById(R.id.editTextDaftarKodeReferral);
        materialButtonLanjutkan = findViewById(R.id.materialButtonLanjutkan);
        textViewMasuk = findViewById(R.id.textViewMasuk);

        final String REFERRAL = getIntent().getStringExtra("REFERRAL");
        if(REFERRAL != null) {
            editTextDaftarKodeReferral.setText(REFERRAL);
            editTextDaftarKodeReferral.setBackgroundColor(getResources().getColor(R.color.background));
            editTextDaftarKodeReferral.setEnabled(false);
        }

        viewModel = ViewModelProviders.of(this).get(RegisterAkunMitraViewModel.class);

        textViewMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AuthLoginMitraActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        materialButtonLanjutkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextDaftarNomorTelepon.getText().toString().isEmpty()) {
                    Toast.makeText(v.getContext(), "Nomor telepon masih kosong!", Toast.LENGTH_SHORT).show();
                    editTextDaftarNomorTelepon.requestFocus();
                } else if(editTextDaftarKodeReferral.getText().toString().isEmpty()) {
                    Toast.makeText(v.getContext(), "Kode referral masih kosong!", Toast.LENGTH_SHORT).show();
                    editTextDaftarKodeReferral.requestFocus();
                }else if(editTextDaftarNomorTelepon.getText().toString().length() > 13) {
                    Toast.makeText(v.getContext(), "Nomor telepon tidak boleh lebih dari 13 angka!", Toast.LENGTH_SHORT).show();
                    editTextDaftarNomorTelepon.requestFocus();
                } else {
                    AlertProgress progress = new AlertProgress(v, "Sedang meregistrasi data");
                    progress.showDialog();
                    viewModel.registerAkun(
                            editTextDaftarNomorTelepon.getText().toString(),
                            editTextDaftarKodeReferral.getText().toString().toUpperCase()
                    ).observe(OWNER, new Observer<AkunResponse>() {
                        @Override
                        public void onChanged(AkunResponse akunResponse) {
                            progress.dismissDialog();
                            if(!akunResponse.getError()) {
                                final AkunResponse.DataDbDashTombo dataDashTombo = akunResponse.getDataDbDashTombo().get(0);
                                final AkunResponse.DataTomboatus dataTomboAti = akunResponse.getDataTomboati().get(0);

                                final AkunModel akunModel = new AkunModel();
                                akunModel.setId(dataTomboAti.getIduserregister());
                                akunModel.setPaket(dataDashTombo.getPaket());
                                akunModel.setHphone(dataDashTombo.getHphone());
                                akunModel.setReferral(dataDashTombo.getSponsor());
                                akunModel.setIdChatRoom(dataTomboAti.getIdChatRoom());

                                PreferenceAkun.setAkun(
                                        v.getContext(),
                                        akunModel
                                );

                                Intent intent = new Intent(v.getContext(), MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);

                                Toast.makeText(v.getContext(), "Berhasil mendaftar!", Toast.LENGTH_SHORT).show();
                            } else {
                                AlertInfo info = new AlertInfo(v, akunResponse.getMessage());
                                info.showDialog();
                            }
                        }
                    });
                }
            }
        });


    }

}
package com.tomboati.tour.view.activity.mitra.auth;

import androidx.annotation.NonNull;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.tomboati.tour.R;
import com.tomboati.tour.api.response.AkunResponse;
import com.tomboati.tour.model.AkunModel;
import com.tomboati.tour.preference.AppPreference;
import com.tomboati.tour.preference.PreferenceAkun;
import com.tomboati.tour.utils.AlertInfo;
import com.tomboati.tour.utils.AlertProgress;
import com.tomboati.tour.view.activity.homepage.MainActivity;
import com.tomboati.tour.viewmodel.tomboati.mitra.RegisterAkunMitraViewModel;
import com.google.android.material.button.MaterialButton;

public class AuthRegisterUserActivity extends AppCompatActivity implements OnCompleteListener {

    private EditText editTextDaftarNomorTelepon, editTextDaftarKodeReferral;
    private TextView textViewMasuk;
    private MaterialButton materialButtonLanjutkan;
    private RegisterAkunMitraViewModel viewModel;
    private final LifecycleOwner OWNER = this;

    private String token = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_auth_register_user);

        editTextDaftarNomorTelepon = findViewById(R.id.editTextDaftarNomorTelepon);
        editTextDaftarKodeReferral = findViewById(R.id.editTextDaftarKodeReferral);
        materialButtonLanjutkan = findViewById(R.id.materialButtonLanjutkan);
        textViewMasuk = findViewById(R.id.textViewMasuk);
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(this);

        final String REFERRAL = getIntent().getStringExtra("REFERRAL");
        if(REFERRAL != null) {
            editTextDaftarKodeReferral.setText(REFERRAL);
            editTextDaftarKodeReferral.setBackgroundColor(getResources().getColor(R.color.background));
            editTextDaftarKodeReferral.setEnabled(false);
        }

        viewModel = ViewModelProviders.of(this).get(RegisterAkunMitraViewModel.class);

        textViewMasuk.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), AuthLoginMitraActivity.class);
            startActivity(intent);
        });

        materialButtonLanjutkan.setOnClickListener(v -> {
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
                if(token != null) {
                    Log.d("TOKEN", "onClick: " + token);
                    AlertProgress progress = new AlertProgress(v, "Sedang meregistrasi data");
                    progress.showDialog();

                    String noTelp = editTextDaftarNomorTelepon.getText().toString();
                    if (noTelp.charAt(0) == '0') {
                        noTelp = noTelp.replaceFirst("0", "62");
                    } else if (noTelp.charAt(0) == '+') {
                        noTelp = noTelp.replaceFirst("\\+", "");
                    }

                    viewModel.registerAkun(
                            noTelp,
                            editTextDaftarKodeReferral.getText().toString(),
                            token
                    ).observe(OWNER, akunResponse -> {
                        progress.dismissDialog();
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

                            PreferenceAkun.setAkun(
                                    v.getContext(),
                                    akunModel
                            );

                            Intent intent = new Intent(v.getContext(), MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);

                            Toast.makeText(v.getContext(), akunResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            AlertInfo info = new AlertInfo(v, akunResponse.getMessage());
                            info.setDialogError();
                            info.showDialog();
                        }
                    });
                } else {
                    Toast.makeText(v.getContext(), "Tunggu sebentar, token masih dimuat!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public void onComplete(@NonNull Task task) {
        this.token = task.getResult().toString();
    }
}
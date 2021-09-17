package com.android.tomboati.view.activity.mitra.pendaftaran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.tomboati.R;
import com.android.tomboati.api.response.LokasiResponse;
import com.android.tomboati.model.AkunModel;
import com.android.tomboati.preference.PreferenceAkun;
import com.android.tomboati.utils.AlertInfo;
import com.android.tomboati.view.activity.homepage.MainActivity;
import com.android.tomboati.view.activity.pendaftaran.PendaftaranDataDiriActivity;
import com.android.tomboati.viewmodel.tomboati.mitra.RegisterDataDiriViewModel;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class RegistrasiDataDiriMitraActivity extends AppCompatActivity {

    private Spinner spinnerProvinsi, spinnerKotaKabupaten, spinnerKecamatan, spinnerKelurahan;
    private EditText editTextRegistrasiNama, editTextRegistrasiRincianAlamat, editTextRegistrasiKodePos;
    private MaterialButton materialButtonDaftarkanDataDiri;
    private RegisterDataDiriViewModel viewModel;
    private final LifecycleOwner OWNER = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_registrasi_data_diri_mitra);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Registrasi Data Diri");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        viewModel = ViewModelProviders.of(this).get(RegisterDataDiriViewModel.class);
        spinnerProvinsi = findViewById(R.id.spinnerProvinsi);
        spinnerKotaKabupaten = findViewById(R.id.spinnerKotaKabupaten);
        spinnerKecamatan = findViewById(R.id.spinnerKecamatan);
        spinnerKelurahan = findViewById(R.id.spinnerKelurahan);
        materialButtonDaftarkanDataDiri = findViewById(R.id.materialButtonDaftarkanDataDiri);
        editTextRegistrasiNama = findViewById(R.id.editTextRegistrasiNama);
        editTextRegistrasiRincianAlamat = findViewById(R.id.editTextRegistrasiRincianAlamat);
        editTextRegistrasiKodePos = findViewById(R.id.editTextRegistrasiKodePos);

        final boolean IS_DAFTAR_MITRA = getIntent().getBooleanExtra("IS_DAFTAR_MITRA", true);
        if(IS_DAFTAR_MITRA) {
            materialButtonDaftarkanDataDiri.setText("LANJUTKAN");
        }

        getProvinsi();

        materialButtonDaftarkanDataDiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkData()) {
                    final AkunModel akunModel = PreferenceAkun.getAkun(v.getContext());
                    akunModel.setName(editTextRegistrasiNama.getText().toString());
                    akunModel.setPropinsi(spinnerProvinsi.getSelectedItem().toString());
                    akunModel.setKota(spinnerKotaKabupaten.getSelectedItem().toString());
                    akunModel.setKecamatan(spinnerKecamatan.getSelectedItem().toString());
                    akunModel.setAddress(
                            editTextRegistrasiRincianAlamat.getText().toString() + ", " +
                            spinnerKelurahan.getSelectedItem().toString()
                    );
                    akunModel.setKodePos(editTextRegistrasiKodePos.getText().toString());

                    PreferenceAkun.removeAkun(v.getContext());
                    PreferenceAkun.setAkun(v.getContext(), akunModel);

                    final Class CLASS_LANJUTAN = IS_DAFTAR_MITRA ?
                            RegistrasiDataPembayaranMitraActivity.class
                        :
                            MainActivity.class
                    ;

                    final Intent intent = new Intent(v.getContext(), CLASS_LANJUTAN);
                    if(!IS_DAFTAR_MITRA) {

                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        final AlertInfo alertInfo = new AlertInfo(RegistrasiDataDiriMitraActivity.this, "Data berhasil disimpan!", intent);
                        alertInfo.showDialog();
                    }  else {
                        startActivity(intent);
                    }
                }
            }
        });

    }

    private boolean checkData() {
        int count = 0;
        final EditText[] arrEditText = {
                editTextRegistrasiNama, editTextRegistrasiRincianAlamat, editTextRegistrasiKodePos
        };
        for(EditText editText : arrEditText) {
            if(editText.getText().toString().isEmpty()) {
                editText.setError("Mohon isi kolom berikut!");
                count++;
            }
        }
        return (count == 0);
    }

    private void getProvinsi() {
        viewModel.getProvinsi().observe(OWNER, new Observer<List<LokasiResponse>>() {
            @Override
            public void onChanged(List<LokasiResponse> lokasiResponses) {
                if (lokasiResponses != null) {
                    ArrayAdapter<LokasiResponse> adapter = new ArrayAdapter<LokasiResponse>(getApplicationContext(), R.layout.item_spinner, lokasiResponses);
                    spinnerProvinsi.setAdapter(adapter);

                    spinnerProvinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            getKotaKabupaten(lokasiResponses.get(position).getId());
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }
        });
    }

    private void getKotaKabupaten(String idLokasi) {
        viewModel.getKotaKabupaten(
                idLokasi
        ).observe(OWNER, new Observer<List<LokasiResponse>>() {
            @Override
            public void onChanged(List<LokasiResponse> lokasiResponses) {
                if (lokasiResponses != null) {
                    ArrayAdapter<LokasiResponse> adapter = new ArrayAdapter<LokasiResponse>(getApplicationContext(), R.layout.item_spinner, lokasiResponses);
                    spinnerKotaKabupaten.setAdapter(adapter);

                    spinnerKotaKabupaten.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            getKecamatan(lokasiResponses.get(position).getId());
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }
        });
    }

    private void getKecamatan(String idLokasi) {
        viewModel.getKecamatan(
                idLokasi
        ).observe(OWNER, new Observer<List<LokasiResponse>>() {
            @Override
            public void onChanged(List<LokasiResponse> lokasiResponses) {
                if (lokasiResponses != null) {
                    ArrayAdapter<LokasiResponse> adapter = new ArrayAdapter<LokasiResponse>(getApplicationContext(), R.layout.item_spinner, lokasiResponses);
                    spinnerKecamatan.setAdapter(adapter);

                    spinnerKecamatan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            getKelurahan(lokasiResponses.get(position).getId());
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }
        });
    }

    private void getKelurahan(String idLokasi) {
        viewModel.getKelurahan(
                idLokasi
        ).observe(OWNER, new Observer<List<LokasiResponse>>() {
            @Override
            public void onChanged(List<LokasiResponse> lokasiResponses) {
                if (lokasiResponses != null) {
                    ArrayAdapter<LokasiResponse> adapter = new ArrayAdapter<LokasiResponse>(getApplicationContext(), R.layout.item_spinner, lokasiResponses);
                    spinnerKelurahan.setAdapter(adapter);
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
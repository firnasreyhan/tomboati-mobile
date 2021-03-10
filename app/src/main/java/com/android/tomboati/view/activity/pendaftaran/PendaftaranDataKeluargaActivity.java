package com.android.tomboati.view.activity.pendaftaran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.tomboati.R;
import com.android.tomboati.api.response.BaseResponse;
import com.android.tomboati.api.response.LokasiResponse;
import com.android.tomboati.model.PesananaModel;
import com.android.tomboati.viewmodel.PendaftaranDataKeluargaViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class PendaftaranDataKeluargaActivity extends AppCompatActivity {
    private PendaftaranDataKeluargaViewModel viewModel;

    private Toolbar toolbar;
    private Spinner spinnerProvinsi, spinnerKotaKabupaten, spinnerKecamatan, spinnerKelurahan;
    private TextInputEditText textInputEditTextNamaLengkap, textInputEditTextNomorHandphone, textInputEditTextKodePos, textInputEditTextRincianAlamat;
    private MaterialButton materialButtonPesanSekarang;

    private PesananaModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_pendaftaran_data_keluarga);

        viewModel = ViewModelProviders.of(this).get(PendaftaranDataKeluargaViewModel.class);
        model = (PesananaModel) getIntent().getSerializableExtra("OBJECT");

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Pendaftaran");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        spinnerProvinsi = findViewById(R.id.spinnerProvinsi);
        spinnerKotaKabupaten = findViewById(R.id.spinnerKotaKabupaten);
        spinnerKecamatan = findViewById(R.id.spinnerKecamatan);
        spinnerKelurahan = findViewById(R.id.spinnerKelurahan);

        textInputEditTextNamaLengkap = findViewById(R.id.textInputEditTextNamaLengkap);
        textInputEditTextNomorHandphone = findViewById(R.id.textInputEditTextNomorHandphone);
        textInputEditTextKodePos = findViewById(R.id.textInputEditTextKodePos);
        textInputEditTextRincianAlamat = findViewById(R.id.textInputEditTextRincianAlamat);
        materialButtonPesanSekarang = findViewById(R.id.materialButtonPesanSekarang);

        getProvinsi();

        materialButtonPesanSekarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkData()) {
                    model.setNamaLengkapKeluarga(textInputEditTextNamaLengkap.getText().toString());
                    model.setNomorHPKeluarga(textInputEditTextNomorHandphone.getText().toString());
                    model.setProvinsiKeluarga(spinnerProvinsi.getSelectedItem().toString());
                    model.setKotakabupatenKeluarga(spinnerKotaKabupaten.getSelectedItem().toString());
                    model.setKecamatanKeluarga(spinnerKecamatan.getSelectedItem().toString());
                    model.setKelurahanKeluarga(spinnerKelurahan.getSelectedItem().toString());
                    model.setKodePOSKeluarga(textInputEditTextKodePos.getText().toString());
                    model.setAlamatKeluarga(textInputEditTextRincianAlamat.getText().toString());

                    viewModel.pendaftaran(model).observe(PendaftaranDataKeluargaActivity.this, new Observer<BaseResponse>() {
                        @Override
                        public void onChanged(BaseResponse baseResponse) {

                        }
                    });
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void getProvinsi() {
        viewModel.getProvinsi().observe(this, new Observer<List<LokasiResponse>>() {
            @Override
            public void onChanged(List<LokasiResponse> lokasiResponses) {
                if (lokasiResponses != null) {
                    ArrayAdapter<LokasiResponse> adapter = new ArrayAdapter<LokasiResponse>(PendaftaranDataKeluargaActivity.this, R.layout.item_spinner, lokasiResponses);
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

    public void getKotaKabupaten(String id) {
        viewModel.getKotaKabupaten(
                id
        ).observe(this, new Observer<List<LokasiResponse>>() {
            @Override
            public void onChanged(List<LokasiResponse> lokasiResponses) {
                if (lokasiResponses != null) {
                    ArrayAdapter<LokasiResponse> adapter = new ArrayAdapter<LokasiResponse>(PendaftaranDataKeluargaActivity.this, R.layout.item_spinner, lokasiResponses);
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

    public void getKecamatan(String id) {
        viewModel.getKecamatan(
                id
        ).observe(this, new Observer<List<LokasiResponse>>() {
            @Override
            public void onChanged(List<LokasiResponse> lokasiResponses) {
                if (lokasiResponses != null) {
                    ArrayAdapter<LokasiResponse> adapter = new ArrayAdapter<LokasiResponse>(PendaftaranDataKeluargaActivity.this, R.layout.item_spinner, lokasiResponses);
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

    public void getKelurahan(String id) {
        viewModel.getKelurahan(
                id
        ).observe(this, new Observer<List<LokasiResponse>>() {
            @Override
            public void onChanged(List<LokasiResponse> lokasiResponses) {
                if (lokasiResponses != null) {
                    ArrayAdapter<LokasiResponse> adapter = new ArrayAdapter<LokasiResponse>(PendaftaranDataKeluargaActivity.this, R.layout.item_spinner, lokasiResponses);
                    spinnerKelurahan.setAdapter(adapter);

                    spinnerKelurahan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }
        });
    }

    private boolean checkData() {
        boolean cek1 = true;
        boolean cek2 = true;
        boolean cek3 = true;
        boolean cek4 = true;

        if (textInputEditTextNamaLengkap.getText().toString().isEmpty()) {
            textInputEditTextNamaLengkap.setError("Mohon isi data berikut");
            cek1 = false;
        }

        if (textInputEditTextNomorHandphone.getText().toString().isEmpty()) {
            textInputEditTextNomorHandphone.setError("Mohon isi data berikut");
            cek2 = false;
        }

        if (textInputEditTextKodePos.getText().toString().isEmpty()) {
            textInputEditTextKodePos.setError("Mohon isi data berikut");
            cek3 = false;
        }

        if (textInputEditTextRincianAlamat.getText().toString().isEmpty()) {
            textInputEditTextRincianAlamat.setError("Mohon isi data berikut");
            cek4 = false;
        }

        return cek1 && cek2 && cek3 && cek4;
    }
}
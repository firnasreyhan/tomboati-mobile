package com.tomboati.tour.view.activity.pendaftaran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.tomboati.tour.R;
import com.tomboati.tour.api.response.LokasiResponse;
import com.tomboati.tour.model.PesananaModel;
import com.tomboati.tour.viewmodel.tomboati.pendaftaran.PendaftaranDataKeluargaViewModel;
import com.google.android.material.button.MaterialButton;

import java.io.Serializable;
import java.util.List;

public class PendaftaranDataKeluargaActivity extends AppCompatActivity {
    private PendaftaranDataKeluargaViewModel viewModel;

    private Spinner spinnerProvinsi, spinnerKotaKabupaten, spinnerKecamatan, spinnerKelurahan;
    private EditText textInputEditTextNamaLengkap, textInputEditTextNomorHandphone, textInputEditTextKodePos, textInputEditTextRincianAlamat;
    private MaterialButton materialButtonLanjutkan;

    private PesananaModel model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_pendaftaran_data_keluarga);

        viewModel = ViewModelProviders.of(this).get(PendaftaranDataKeluargaViewModel.class);
        model = (PesananaModel) getIntent().getSerializableExtra("OBJECT");

        Toolbar toolbar = findViewById(R.id.toolbar);
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
        materialButtonLanjutkan = findViewById(R.id.materialButtonLanjutkan);

        getProvinsi();

        materialButtonLanjutkan.setOnClickListener(new View.OnClickListener() {
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

                    Intent intent = new Intent(v.getContext(), Syarat2Activity.class);
                    intent.putExtra("OBJECT", (Serializable) model);
                    startActivity(intent);
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
                        } @Override public void onNothingSelected(AdapterView<?> parent) { }
                    });
                }
            }
        });
    }

    public void getKotaKabupaten(String id) {
        viewModel.getKotaKabupaten(id).observe(this, new Observer<List<LokasiResponse>>() {
            @Override
            public void onChanged(List<LokasiResponse> lokasiResponses) {
                if (lokasiResponses != null) {
                    ArrayAdapter<LokasiResponse> adapter = new ArrayAdapter<LokasiResponse>(PendaftaranDataKeluargaActivity.this, R.layout.item_spinner, lokasiResponses);
                    spinnerKotaKabupaten.setAdapter(adapter);

                    spinnerKotaKabupaten.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            getKecamatan(lokasiResponses.get(position).getId());
                        } @Override public void onNothingSelected(AdapterView<?> parent) { }
                    });
                }
            }
        });
    }

    public void getKecamatan(String id) {
        viewModel.getKecamatan(id).observe(this, new Observer<List<LokasiResponse>>() {
            @Override
            public void onChanged(List<LokasiResponse> lokasiResponses) {
                if (lokasiResponses != null) {
                    ArrayAdapter<LokasiResponse> adapter = new ArrayAdapter<LokasiResponse>(PendaftaranDataKeluargaActivity.this, R.layout.item_spinner, lokasiResponses);
                    spinnerKecamatan.setAdapter(adapter);
                    spinnerKecamatan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            getKelurahan(lokasiResponses.get(position).getId());
                        }  @Override public void onNothingSelected(AdapterView<?> parent) { }
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
                        @Override public void onItemSelected(AdapterView<?> parent, View view, int position, long id) { }
                        @Override  public void onNothingSelected(AdapterView<?> parent) {  }
                    });
                }
            }
        });
    }

    private boolean checkData() {

        int countError = 0;

        final EditText[] editText = {
                textInputEditTextNamaLengkap, textInputEditTextNomorHandphone, textInputEditTextKodePos, textInputEditTextRincianAlamat
        };

        for (EditText textInputEditText : editText) {
            if (textInputEditText.getText().toString().isEmpty()) {
                textInputEditText.setError("Mohon isi data berikut");
                countError++;
            }
        }

        return (countError == 0);
    }

}
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

import com.android.tomboati.R;
import com.android.tomboati.api.response.LokasiResponse;
import com.android.tomboati.viewmodel.PendaftaranDataDiriViewModel;

import java.util.ArrayList;
import java.util.List;

public class PendaftaranDataDiriActivity extends AppCompatActivity {
    private PendaftaranDataDiriViewModel viewModel;
    private Toolbar toolbar;
    private Spinner spinnerJenisKelamin, spinnerStatusPerkawinan, spinnerKewarganegaraan, spinnerProvinsi, spinnerKotaKabupaten, spinnerKecamatan, spinnerKelurahan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_pendaftaran_data_diri);

        viewModel = ViewModelProviders.of(this).get(PendaftaranDataDiriViewModel.class);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Pendaftaran");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        spinnerProvinsi = findViewById(R.id.spinnerProvinsi);
        spinnerKotaKabupaten = findViewById(R.id.spinnerKotaKabupaten);
        spinnerKecamatan = findViewById(R.id.spinnerKecamatan);
        spinnerKelurahan = findViewById(R.id.spinnerKelurahan);
        spinnerJenisKelamin = findViewById(R.id.spinnerJenisKelamin);
        spinnerStatusPerkawinan = findViewById(R.id.spinnerStatusPerkawinan);
        spinnerKewarganegaraan = findViewById(R.id.spinnerKewarganegaraan);

        String[] jenisKelamin = new String[] {"Laki-laki", "Perempuan"};
        ArrayAdapter<String> jenisKelaminAdapter = new ArrayAdapter<>(this, R.layout.item_spinner, jenisKelamin);
        spinnerJenisKelamin.setAdapter(jenisKelaminAdapter);

        String[] statusPerkawinan = new String[] {"Belum Kawin", "Kawin", "Cerai Hidup", "Cerai Mati"};
        ArrayAdapter<String> statusPerkawinanAdapter = new ArrayAdapter<>(this, R.layout.item_spinner, statusPerkawinan);
        spinnerStatusPerkawinan.setAdapter(statusPerkawinanAdapter);

        String[] kewarganegaraan = new String[] {"WNI", "WNA"};
        ArrayAdapter<String> kewarganegaraanAdapter = new ArrayAdapter<>(this, R.layout.item_spinner, kewarganegaraan);
        spinnerKewarganegaraan.setAdapter(kewarganegaraanAdapter);

        getProvinsi();
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
                    ArrayAdapter<LokasiResponse> adapter = new ArrayAdapter<LokasiResponse>(PendaftaranDataDiriActivity.this, R.layout.item_spinner, lokasiResponses);
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
                    ArrayAdapter<LokasiResponse> adapter = new ArrayAdapter<LokasiResponse>(PendaftaranDataDiriActivity.this, R.layout.item_spinner, lokasiResponses);
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
                    ArrayAdapter<LokasiResponse> adapter = new ArrayAdapter<LokasiResponse>(PendaftaranDataDiriActivity.this, R.layout.item_spinner, lokasiResponses);
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
                    ArrayAdapter<LokasiResponse> adapter = new ArrayAdapter<LokasiResponse>(PendaftaranDataDiriActivity.this, R.layout.item_spinner, lokasiResponses);
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
}
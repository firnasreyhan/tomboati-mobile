package com.android.tomboati.view.activity.pendaftaran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.tomboati.R;
import com.android.tomboati.api.response.LokasiResponse;
import com.android.tomboati.model.PesananaModel;
import com.android.tomboati.preference.AppPreference;
import com.android.tomboati.preference.PreferenceAkun;
import com.android.tomboati.utils.Utility;
import com.android.tomboati.viewmodel.tomboati.pendaftaran.PendaftaranDataDiriViewModel;
import com.google.android.material.button.MaterialButton;

import java.io.Serializable;
import java.util.List;

public class PendaftaranDataDiriActivity extends AppCompatActivity {
    private PendaftaranDataDiriViewModel viewModel;
    private Spinner spinnerJenisKelamin, spinnerStatusPerkawinan, spinnerKewarganegaraan, spinnerProvinsi, spinnerKotaKabupaten, spinnerKecamatan, spinnerKelurahan;
    private EditText textInputEditEmail, textInputEditTextNomorKTP, textInputEditTextNamaLengkap,
            textInputEditTextNomorHandphone, textInputEditTextTempatLahir, textInputEditTextPekerjaan, textInputEditRiwayatPenyakit, textInputEditTextKodePos, textInputEditTextRincianAlamat, textInputEditTextNomorPaspor, textInputEditTextTempatDikeluarkan;

    private MaterialButton materialButtonLanjutkan;
    private TextView textInputEditTanggalLahir, textInputEditTextTanggalPenerbitanPaspor, textInputEditTextTanggalBerakhirPaspor;

    private String[] bulan = {
            "Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September","Oktober", "November", "Desember"
    };

    private PesananaModel model;

    private String tanggalLahir = "", tanggalPenerbitanPaspor = "", tanggalBerakhirPaspor = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_pendaftaran_data_diri);

        viewModel = ViewModelProviders.of(this).get(PendaftaranDataDiriViewModel.class);

        model = (PesananaModel) getIntent().getSerializableExtra("OBJECT");

        final Toolbar toolbar = findViewById(R.id.toolbar);
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

        textInputEditTextNomorKTP = findViewById(R.id.textInputEditTextNomorKTP);
        textInputEditTextNamaLengkap = findViewById(R.id.textInputEditTextNamaLengkap);
        textInputEditTextNomorHandphone = findViewById(R.id.textInputEditTextNomorHandphone);
        textInputEditTextTempatLahir = findViewById(R.id.textInputEditTextTempatLahir);
        textInputEditTextPekerjaan = findViewById(R.id.textInputEditTextPekerjaan);
        textInputEditRiwayatPenyakit = findViewById(R.id.textInputEditRiwayatPenyakit);
        textInputEditTextKodePos = findViewById(R.id.textInputEditTextKodePos);
        textInputEditTextRincianAlamat = findViewById(R.id.textInputEditTextRincianAlamat);
        textInputEditTextNomorPaspor = findViewById(R.id.textInputEditTextNomorPaspor);
        textInputEditTextTempatDikeluarkan = findViewById(R.id.textInputEditTextTempatDikeluarkan);
        textInputEditEmail = findViewById(R.id.textInputEditEmail);

        textInputEditTanggalLahir = findViewById(R.id.textViewTanggalLahir); //=
        textInputEditTextTanggalPenerbitanPaspor = findViewById(R.id.textViewTanggalPenerbitanPaspor); //=
        textInputEditTextTanggalBerakhirPaspor = findViewById(R.id.textViewTanggalBerakhirPaspor); //=

        materialButtonLanjutkan = findViewById(R.id.materialButtonLanjutkan);

        String[] jenisKelamin = new String[] {"Perempuan", "Laki-laki"};
        ArrayAdapter<String> jenisKelaminAdapter = new ArrayAdapter<>(this, R.layout.item_spinner, jenisKelamin);
        spinnerJenisKelamin.setAdapter(jenisKelaminAdapter);

        String[] statusPerkawinan = new String[] {"Belum Kawin", "Kawin", "Cerai Hidup", "Cerai Mati"};
        ArrayAdapter<String> statusPerkawinanAdapter = new ArrayAdapter<>(this, R.layout.item_spinner, statusPerkawinan);
        spinnerStatusPerkawinan.setAdapter(statusPerkawinanAdapter);

        String[] kewarganegaraan = new String[] {"WNI", "WNA"};
        ArrayAdapter<String> kewarganegaraanAdapter = new ArrayAdapter<>(this, R.layout.item_spinner, kewarganegaraan);
        spinnerKewarganegaraan.setAdapter(kewarganegaraanAdapter);

        getProvinsi();

        textInputEditTanggalLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tanggalLahir = "" + year + "-" + (month + 1) + "-" + dayOfMonth;
                        textInputEditTanggalLahir.setText(String.format("%02d ", dayOfMonth) + bulan[month] + " " + year);
                    }
                }, Utility.getYear(), Utility.getMonth(), Utility.getDay()).show();
            }
        });

        textInputEditTextTanggalPenerbitanPaspor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tanggalPenerbitanPaspor = "" + year + "-" + (month + 1) + "-" + dayOfMonth;
                        textInputEditTextTanggalPenerbitanPaspor.setText(String.format("%02d ", dayOfMonth) + bulan[month] + " " + year);
                    }
                }, Utility.getYear(), Utility.getMonth(), Utility.getDay()).show();
            }
        });

        textInputEditTextTanggalBerakhirPaspor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tanggalBerakhirPaspor = "" + year + "-" + (month + 1) + "-" + dayOfMonth;
                        textInputEditTextTanggalBerakhirPaspor.setText(String.format("%02d ", dayOfMonth) + bulan[month] + " " + year);
                    }
                }, Utility.getYear(), Utility.getMonth(), Utility.getDay()).show();
            }
        });

        materialButtonLanjutkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkData()) {

                    String noTelp = textInputEditTextNomorHandphone.getText().toString();
                    if(noTelp.charAt(0) == '0') {
                        noTelp = noTelp.replaceFirst("0", "62");
                    } else if(noTelp.charAt(0) == '+') {
                        noTelp = noTelp.replaceFirst("\\+", "");
                    }

                    model.setIdUserRegister(PreferenceAkun.getAkun(v.getContext()).getId());
                    model.setNomorKTP(textInputEditTextNomorKTP.getText().toString());
                    model.setEmail(textInputEditEmail.getText().toString());
                    model.setNamaLengkap(textInputEditTextNamaLengkap.getText().toString());
                    model.setNomorHP(noTelp);
                    model.setTempatLahir(textInputEditTextTempatLahir.getText().toString());
                    model.setTanggalLahir(tanggalLahir);
                    model.setJenisKelamin("" + spinnerJenisKelamin.getSelectedItemPosition());
                    model.setStatusPerkawinan(spinnerStatusPerkawinan.getSelectedItem().toString());
                    model.setKewarganegaraan(spinnerKewarganegaraan.getSelectedItem().toString());
                    model.setPekerjaan(textInputEditTextPekerjaan.getText().toString());
                    model.setRiwayatPenyakit(textInputEditRiwayatPenyakit.getText().toString());
                    model.setProvinsi(spinnerProvinsi.getSelectedItem().toString());
                    model.setKotaKabupaten(spinnerKotaKabupaten.getSelectedItem().toString());
                    model.setKecamatan(spinnerKecamatan.getSelectedItem().toString());
                    model.setKelurahan(spinnerKelurahan.getSelectedItem().toString());
                    model.setKodePOS(textInputEditTextKodePos.getText().toString());
                    model.setAlamat(textInputEditTextRincianAlamat.getText().toString());
                    model.setNomorPaspor(textInputEditTextNomorPaspor.getText().toString());
                    model.setTempatDikeluarkan(textInputEditTextTempatDikeluarkan.getText().toString());
                    model.setTanggalPenerbitanPaspor(tanggalPenerbitanPaspor);
                    model.setTanggalBerakhirPaspor(tanggalBerakhirPaspor);

                    Intent intent = new Intent(v.getContext(), PendaftaranDokumenDataDiriActivity.class);
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

    private boolean checkData() {

        int countError = 0;

        final EditText[] editText = {
                textInputEditTextNomorKTP, textInputEditTextNamaLengkap, textInputEditTextNomorHandphone,
                textInputEditTextTempatLahir, textInputEditTextPekerjaan,
                textInputEditRiwayatPenyakit, textInputEditTextKodePos, textInputEditTextRincianAlamat,
                textInputEditTextNomorPaspor, textInputEditTextTempatDikeluarkan,
        };

        final String[] tanggal = {
                tanggalLahir, tanggalPenerbitanPaspor, tanggalBerakhirPaspor
        };

        final String[] prefix = {
                "Tanggal Lahir", "Tanggal Penerbitan Paspor", "Tanggal Berakhir Paspor"
        };

        for (int i = 0; i <editText.length ; i++) {
            if (editText[i].getText().toString().isEmpty()) {
                editText[i].setError("Mohon isi data berikut");
                countError++;
            }

            if(i < tanggal.length) {
                if(tanggal[i].isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Mohon Pilih " + prefix[i] + " Terlebih dahulu", Toast.LENGTH_SHORT).show();
                    countError++;
                }
            }
        }

        return (countError == 0);
    }
}
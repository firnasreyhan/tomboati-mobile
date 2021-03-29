package com.android.tomboati.view.activity.pendaftaran;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.tomboati.R;
import com.android.tomboati.api.response.LokasiResponse;
import com.android.tomboati.model.PesananaModel;
import com.android.tomboati.preference.AppPreference;
import com.android.tomboati.view.activity.SignUpActivity;
import com.android.tomboati.viewmodel.PendaftaranDataDiriViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PendaftaranDataDiriActivity extends AppCompatActivity {
    private PendaftaranDataDiriViewModel viewModel;
    private Toolbar toolbar;
    private Spinner spinnerJenisKelamin, spinnerStatusPerkawinan, spinnerKewarganegaraan, spinnerProvinsi, spinnerKotaKabupaten, spinnerKecamatan, spinnerKelurahan;
    private TextInputEditText textInputEditTextNomorKTP, textInputEditTextNamaLengkap, textInputEditTextNomorHandphone, textInputEditTextTempatLahir, textInputEditTanggalLahir, textInputEditTextPekerjaan, textInputEditRiwayatPenyakit, textInputEditTextKodePos, textInputEditTextRincianAlamat, textInputEditTextNomorPaspor, textInputEditTextTempatDikeluarkan, textInputEditTextTanggalPenerbitanPaspor, textInputEditTextTanggalBerakhirPaspor;
    private CardView cardViewFotoKTP, cardViewFotoAkteKelahiran, cardViewFotoKartuKeluarga, cardViewFotoPaspor, cardViewFotoBukuNikah;
    private ImageView imageViewKTP, imageViewAkteKelahiran, imageViewKartuKeluarga, imageViewPaspor, imageViewBukuNikah;
    private MaterialButton materialButtonLanjutkan;

    private Uri uriKTP, uriAkteKelahiran, uriKartuKeluarga, uriFotoPaspor, uriFotoBukuNikah;
    private int uriNumber;


    private PesananaModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_pendaftaran_data_diri);

        viewModel = ViewModelProviders.of(this).get(PendaftaranDataDiriViewModel.class);

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
        spinnerJenisKelamin = findViewById(R.id.spinnerJenisKelamin);
        spinnerStatusPerkawinan = findViewById(R.id.spinnerStatusPerkawinan);
        spinnerKewarganegaraan = findViewById(R.id.spinnerKewarganegaraan);

        textInputEditTextNomorKTP = findViewById(R.id.textInputEditTextNomorKTP);
        textInputEditTextNamaLengkap = findViewById(R.id.textInputEditTextNamaLengkap);
        textInputEditTextNomorHandphone = findViewById(R.id.textInputEditTextNomorHandphone);
        textInputEditTextTempatLahir = findViewById(R.id.textInputEditTextTempatLahir);
        textInputEditTanggalLahir = findViewById(R.id.textInputEditTanggalLahir);
        textInputEditTextPekerjaan = findViewById(R.id.textInputEditTextPekerjaan);
        textInputEditRiwayatPenyakit = findViewById(R.id.textInputEditRiwayatPenyakit);
        textInputEditTextKodePos = findViewById(R.id.textInputEditTextKodePos);
        textInputEditTextRincianAlamat = findViewById(R.id.textInputEditTextRincianAlamat);
        textInputEditTextNomorPaspor = findViewById(R.id.textInputEditTextNomorPaspor);
        textInputEditTextTempatDikeluarkan = findViewById(R.id.textInputEditTextTempatDikeluarkan);
        textInputEditTextTanggalPenerbitanPaspor = findViewById(R.id.textInputEditTextTanggalPenerbitanPaspor);
        textInputEditTextTanggalBerakhirPaspor = findViewById(R.id.textInputEditTextTanggalBerakhirPaspor);

        cardViewFotoKTP = findViewById(R.id.cardViewFotoKTP);
        cardViewFotoAkteKelahiran = findViewById(R.id.cardViewFotoAkteKelahiran);
        cardViewFotoKartuKeluarga = findViewById(R.id.cardViewFotoKartuKeluarga);
        cardViewFotoPaspor = findViewById(R.id.cardViewFotoPaspor);
        cardViewFotoBukuNikah = findViewById(R.id.cardViewFotoBukuNikah);

        imageViewKTP = findViewById(R.id.imageViewKTP);
        imageViewAkteKelahiran = findViewById(R.id.imageViewAkteKelahiran);
        imageViewKartuKeluarga = findViewById(R.id.imageViewKartuKeluarga);
        imageViewPaspor = findViewById(R.id.imageViewPaspor);
        imageViewBukuNikah = findViewById(R.id.imageViewBukuNikah);

        materialButtonLanjutkan = findViewById(R.id.materialButtonLanjutkan);

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

        final CardView[] arrCardView = {
            cardViewFotoKTP, cardViewFotoAkteKelahiran, cardViewFotoKartuKeluarga, cardViewFotoPaspor, cardViewFotoBukuNikah
        };

        for(int i = 0; i < arrCardView.length; i++) {
            int j = i;
            arrCardView[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CropImage.activity().setGuidelines(CropImageView.Guidelines.OFF).start(PendaftaranDataDiriActivity.this);
                    uriNumber = j + 1;
                }
            });
        }
//
//        .setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                CropImage.activity().setGuidelines(CropImageView.Guidelines.OFF).start(PendaftaranDataDiriActivity.this);
//                uriNumber = 1;
//            }
//        });
//
//        .setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                CropImage.activity().setGuidelines(CropImageView.Guidelines.OFF).start(PendaftaranDataDiriActivity.this);
//                uriNumber = 2;
//            }
//        });
//
//        .setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                CropImage.activity().setGuidelines(CropImageView.Guidelines.OFF).start(PendaftaranDataDiriActivity.this);
//                uriNumber = 3;
//            }
//        });
//
//        .setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                CropImage.activity().setGuidelines(CropImageView.Guidelines.OFF).start(PendaftaranDataDiriActivity.this);
//                uriNumber = 4;
//            }
//        });
//
//        .setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                CropImage.activity().setGuidelines(CropImageView.Guidelines.OFF).start(PendaftaranDataDiriActivity.this);
//                uriNumber = 5;
//            }
//        });

        materialButtonLanjutkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkData()) {
                    model.setIdUserRegister(AppPreference.getUser(v.getContext()).getIdUserRegister());
                    model.setEmail(AppPreference.getUser(v.getContext()).getEmail());
                    model.setNamaLengkap(textInputEditTextNamaLengkap.getText().toString());
                    model.setNomorHP(textInputEditTextNomorHandphone.getText().toString());
                    model.setTempatLahir(textInputEditTextTempatLahir.getText().toString());
                    model.setTanggalLahir(textInputEditTanggalLahir.getText().toString());
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
                    model.setTanggalPenerbitanPaspor(textInputEditTextTanggalPenerbitanPaspor.getText().toString());
                    model.setTanggalBerakhirPaspor(textInputEditTextTanggalBerakhirPaspor.getText().toString());
                    model.setFileKTP(uriKTP.toString());
                    model.setFileAkteKelahiran(uriAkteKelahiran.toString());
                    model.setFileKK(uriKartuKeluarga.toString());
                    model.setFilePaspor(uriFotoPaspor.toString());
                    model.setFileBukuNikah(uriFotoBukuNikah.toString());

                    Intent intent = new Intent(v.getContext(), PendaftaranDataKeluargaActivity.class);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                switch (uriNumber) {
                    case 1:
                        uriKTP = result.getUri();
                        imageViewKTP.setImageURI(uriKTP);
                        break;
                    case 2:
                        uriAkteKelahiran = result.getUri();
                        imageViewAkteKelahiran.setImageURI(uriAkteKelahiran);
                        break;
                    case 3:
                        uriKartuKeluarga = result.getUri();
                        imageViewKartuKeluarga.setImageURI(uriKartuKeluarga);
                        break;
                    case 4:
                        uriFotoPaspor = result.getUri();
                        imageViewPaspor.setImageURI(uriFotoPaspor);
                        break;
                    case 5:
                        uriFotoBukuNikah = result.getUri();
                        imageViewBukuNikah.setImageURI(uriFotoBukuNikah);
                        break;
                }
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                String error = result.getError().getMessage();
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
            }
        }
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

        final TextInputEditText[] editText = {
                textInputEditTextNomorKTP, textInputEditTextNamaLengkap, textInputEditTextNomorHandphone,
                textInputEditTextTempatLahir, textInputEditTanggalLahir, textInputEditTextPekerjaan,
                textInputEditRiwayatPenyakit, textInputEditTextKodePos, textInputEditTextRincianAlamat,
                textInputEditTextNomorPaspor, textInputEditTextTempatDikeluarkan, textInputEditTextTanggalPenerbitanPaspor,
                textInputEditTextTanggalBerakhirPaspor
        };

        final Uri[] uriImg = {
                uriKTP, uriAkteKelahiran, uriKartuKeluarga, uriFotoPaspor, uriFotoBukuNikah
        };

        final String[] prefix = {
                "KTP", "akte kelahiran", "kartu keluarga", "paspor", "buku nikah"
        };

        for (int i = 0; i < editText.length; i++) {
            if (editText[i].getText().toString().isEmpty()) {
                editText[i].setError("Mohon isi data berikut");
                countError++;
            }

            if(i < uriImg.length) {
                if(uriImg[i] == null) {
                    Toast.makeText(this, "Harap upload foto " + prefix[i] + " anda", Toast.LENGTH_SHORT).show();
                    countError++;
                }
            }

        }

        return (countError == 0);
    }
}
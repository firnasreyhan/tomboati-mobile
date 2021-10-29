package com.tomboati.tour.view.activity.homepage;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.tomboati.tour.R;
import com.tomboati.tour.api.response.LokasiResponse;
import com.tomboati.tour.model.AkunModel;
import com.tomboati.tour.preference.PreferenceAkun;
import com.tomboati.tour.utils.AlertProgress;
import com.tomboati.tour.viewmodel.tomboati.mitra.UbahProfileViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import de.hdodenhof.circleimageview.CircleImageView;

public class UbahProfilActivity extends AppCompatActivity {

    private CircleImageView imageViewRegistrasiFotoProfile;
    private FloatingActionButton floatingActionButtonFotoProfile;
    private EditText editTextRegistrasiNama, editTextRegistrasiRincianAlamat,
            editTextRegistrasiKodePos, editTextRegistrasiEmail;
    private Spinner spinnerProvinsi, spinnerKotaKabupaten, spinnerKecamatan;
    private MaterialButton materialButtonUbahProfil;

    private UbahProfileViewModel viewModel;
    private AkunModel MODEL;
    private final LifecycleOwner OWNER = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_ubah_profil);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Ubah Profil");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        imageViewRegistrasiFotoProfile = findViewById(R.id.imageViewRegistrasiFotoProfile);
        floatingActionButtonFotoProfile = findViewById(R.id.floatingActionButtonFotoProfile);
        editTextRegistrasiNama = findViewById(R.id.editTextRegistrasiNama);
        editTextRegistrasiRincianAlamat = findViewById(R.id.editTextRegistrasiRincianAlamat);
        editTextRegistrasiKodePos = findViewById(R.id.editTextRegistrasiKodePos);
        editTextRegistrasiEmail = findViewById(R.id.editTextRegistrasiEmail);
        spinnerProvinsi = findViewById(R.id.spinnerProvinsi);
        spinnerKotaKabupaten = findViewById(R.id.spinnerKotaKabupaten);
        spinnerKecamatan = findViewById(R.id.spinnerKecamatan);
        materialButtonUbahProfil = findViewById(R.id.materialButtonUbahProfil);

        viewModel = ViewModelProviders.of(this).get(UbahProfileViewModel.class);

        getProvinsi();

        MODEL = PreferenceAkun.getAkun(this);
        editTextRegistrasiNama.setText(MODEL.getName());
        editTextRegistrasiKodePos.setText(MODEL.getKodePos());
        editTextRegistrasiRincianAlamat.setText(MODEL.getAddress());
        editTextRegistrasiEmail.setText(MODEL.getEmail());
        picassoLoad(MODEL.getPhoto(), imageViewRegistrasiFotoProfile);


        floatingActionButtonFotoProfile.setOnClickListener(v -> CropImage.activity().setGuidelines(CropImageView.Guidelines.OFF).start(UbahProfilActivity.this));

        materialButtonUbahProfil.setOnClickListener(v-> {
            if(checkData()) {

                final String NAMA = editTextRegistrasiNama.getText().toString();
                final String ADDRESS = editTextRegistrasiRincianAlamat.getText().toString();
                final String KODEPOS = editTextRegistrasiKodePos.getText().toString();
                final String EMAIL = editTextRegistrasiEmail.getText().toString();
                final String PROVINSI = spinnerProvinsi.getSelectedItem().toString();
                final String KOTA = spinnerKotaKabupaten.getSelectedItem().toString();
                final String KECAMATAN = spinnerKecamatan.getSelectedItem().toString();

                AlertProgress progress = new AlertProgress(v, "Sedang memperbaruhi data profil");
                progress.showDialog();
                viewModel.gantiDataProfil(
                        NAMA,
                        PROVINSI,
                        KOTA,
                        KECAMATAN,
                        ADDRESS,
                        KODEPOS,
                        MODEL.getCountry(),
                        EMAIL
                ).observe(OWNER, baseResponse -> {
                    progress.dismissDialog();
                    if(baseResponse != null) {
                        Toast.makeText(getApplicationContext(), baseResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        if(!baseResponse.isError()) {
                            MODEL.setName(NAMA);
                            MODEL.setPropinsi(PROVINSI);
                            MODEL.setKota(KOTA);
                            MODEL.setKecamatan(KECAMATAN);
                            MODEL.setAddress(ADDRESS);
                            MODEL.setEmail(EMAIL);
                            PreferenceAkun.removeAkun(v.getContext());
                            PreferenceAkun.setAkun(v.getContext(), MODEL);
                            finish();
                        }
                    }
                });
            }
        });

    }

    private boolean checkData() {
        int count = 0;
        final EditText[] arrEditText = {
                editTextRegistrasiNama, editTextRegistrasiRincianAlamat,
                editTextRegistrasiKodePos, editTextRegistrasiEmail
        };
        for(EditText editText : arrEditText) {
            if(editText.getText().toString().isEmpty()) {
                editText.setError("Mohon isi kolom berikut!");
                count++;
            }
        }
        return (count == 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                imageViewRegistrasiFotoProfile.setImageURI(result.getUri());
                AlertProgress progress = new AlertProgress(this, "Sedang memperbaruhi foto profil");
                progress.showDialog();
                viewModel.gantiFotoProfil(result.getUri().toString()).observe(OWNER, ubahFotoProfileResponse -> {
                    progress.dismissDialog();
                    if(ubahFotoProfileResponse != null) {
                        if(!ubahFotoProfileResponse.isError()) {
                            picassoLoad(ubahFotoProfileResponse.getFoto(), imageViewRegistrasiFotoProfile);
                            MODEL.setPhoto(ubahFotoProfileResponse.getFoto());
                            PreferenceAkun.removeAkun(getApplicationContext());
                            PreferenceAkun.setAkun(getApplicationContext(), MODEL);
                            MODEL = PreferenceAkun.getAkun(getApplicationContext());
                        }
                        Toast.makeText(getApplicationContext(), ubahFotoProfileResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                String error = result.getError().getMessage();
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void picassoLoad(String uri, ImageView imageView) {
        Picasso.get().load(uri).priority(Picasso.Priority.HIGH).placeholder(R.drawable.ic_logo).into(imageView);
    }

    private void getProvinsi() {
        viewModel.getProvinsi().observe(OWNER, lokasiResponses -> {
            if (lokasiResponses != null) {
                ArrayAdapter<LokasiResponse> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.item_spinner, lokasiResponses);
                spinnerProvinsi.setAdapter(adapter);

                for (int i = 0; i < lokasiResponses.size(); i++) {
                    if (MODEL.getPropinsi().equals(lokasiResponses.get(i).getNama())) {
                        spinnerProvinsi.setSelection(i);
                    }
                }

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
        });
    }

    private void getKotaKabupaten(String idLokasi) {
        viewModel.getKotaKabupaten(idLokasi).observe(OWNER, lokasiResponses -> {
            if (lokasiResponses != null) {
                ArrayAdapter<LokasiResponse> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.item_spinner, lokasiResponses);
                spinnerKotaKabupaten.setAdapter(adapter);

                for (int i = 0; i < lokasiResponses.size(); i++) {
                    if (MODEL.getKota().equals(lokasiResponses.get(i).getNama())) {
                        spinnerKotaKabupaten.setSelection(i);
                    }
                }

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
        });
    }

    private void getKecamatan(String idLokasi) {
        viewModel.getKecamatan(idLokasi).observe(OWNER, lokasiResponses -> {
            if (lokasiResponses != null) {
                ArrayAdapter<LokasiResponse> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.item_spinner, lokasiResponses);
                spinnerKecamatan.setAdapter(adapter);
                for (int i = 0; i < lokasiResponses.size(); i++) {
                    if (MODEL.getKecamatan().equals(lokasiResponses.get(i).getNama())) {
                        spinnerKecamatan.setSelection(i);
                    }
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
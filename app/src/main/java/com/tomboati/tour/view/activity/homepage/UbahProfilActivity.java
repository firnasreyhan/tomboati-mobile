package com.tomboati.tour.view.activity.homepage;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import com.tomboati.tour.R;
import com.tomboati.tour.api.response.LokasiResponse;
import com.tomboati.tour.databinding.ActivityUbahProfilBinding;
import com.tomboati.tour.model.AkunModel;
import com.tomboati.tour.preference.PreferenceAkun;
import com.tomboati.tour.view.activity.base.BaseToolbarActivity;
import com.tomboati.tour.viewmodel.tomboati.mitra.UbahProfileViewModel;

import com.theartofdev.edmodo.cropper.CropImage;

public class UbahProfilActivity extends BaseToolbarActivity {

    private ActivityUbahProfilBinding bind;
    private UbahProfileViewModel viewModel;
    private AkunModel MODEL;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        bind = ActivityUbahProfilBinding.inflate(getLayoutInflater());
        setToolbar(bind.toolbar, "Ubah Profil");
        viewModel = ViewModelProviders.of(this).get(UbahProfileViewModel.class);

        MODEL = PreferenceAkun.getAkun(this);
        getProvinsi();
        bind.setAkun(MODEL);
        picassoLoad(MODEL.getPhoto(), bind.imageViewUbahFotoProfile);

        bind.floatingActionButtonFotoProfile.setOnClickListener(v -> cropImages());
        bind.materialButtonUbahProfil.setOnClickListener(v-> {
            if(checkData()) {
                showProgressDialog("Sedang memperbaruhi data profil");
                MODEL = bind.getAkun();
                MODEL.setPropinsi(bind.spinnerProvinsi.getSelectedItem().toString());
                MODEL.setKota(bind.spinnerKotaKabupaten.getSelectedItem().toString());
                MODEL.setKecamatan(bind.spinnerKecamatan.getSelectedItem().toString());
                viewModel.gantiDataProfil(
                        MODEL.getName(),
                        MODEL.getPropinsi(),
                        MODEL.getKota(),
                        MODEL.getKecamatan(),
                        MODEL.getAddress(),
                        MODEL.getKodePos(),
                        MODEL.getCountry(),
                        MODEL.getEmail()
                ).observe(getOwner(), baseResponse -> {
                    dismissProgressDialog();
                    if(baseResponse != null) {
                        showToast(baseResponse.getMessage());
                        if(!baseResponse.isError()) {
                            PreferenceAkun.removeAkun(v.getContext());
                            PreferenceAkun.setAkun(v.getContext(), MODEL);
                            finish();
                        }
                    }
                });
            }
        });
    }

    @Override
    protected View getContentView() {
        return bind.getRoot();
    }

    private boolean checkData() {
        int count = 0;
        final EditText[] arrEditText = {
                bind.editTextRegistrasiNama, bind.editTextRegistrasiRincianAlamat,
                bind.editTextRegistrasiKodePos, bind.editTextRegistrasiEmail
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
                bind.imageViewUbahFotoProfile.setImageURI(result.getUri());
                showProgressDialog("Sedang memperbaruhi foto profil");
                viewModel.gantiFotoProfil(result.getUri().toString()).observe(getOwner(), profileResponse -> {
                    dismissProgressDialog();
                    if(profileResponse != null) {
                        if(!profileResponse.isError()) {
                            picassoLoad(profileResponse.getFoto(), bind.imageViewUbahFotoProfile);
                            MODEL.setPhoto(profileResponse.getFoto());
                            PreferenceAkun.removeAkun(getApplicationContext());
                            PreferenceAkun.setAkun(getApplicationContext(), MODEL);
                        }
                        showToast(profileResponse.getMessage());
                    }
                });
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                showToast(result.getError().getMessage());
            }
        }
    }

    private void getProvinsi() {
        viewModel.getProvinsi().observe(getOwner(), lokasiResponses -> {
            if (lokasiResponses != null) {
                ArrayAdapter<LokasiResponse> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.item_spinner, lokasiResponses);
                bind.spinnerProvinsi.setAdapter(adapter);

                for (int i = 0; i < lokasiResponses.size(); i++) {
                    if (MODEL.getPropinsi().equals(lokasiResponses.get(i).getNama())) {
                        bind.spinnerProvinsi.setSelection(i);
                    }
                }

                bind.spinnerProvinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        viewModel.getKotaKabupaten(idLokasi).observe(getOwner(), lokasiResponses -> {
            if (lokasiResponses != null) {
                ArrayAdapter<LokasiResponse> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.item_spinner, lokasiResponses);
                bind.spinnerKotaKabupaten.setAdapter(adapter);

                for (int i = 0; i < lokasiResponses.size(); i++) {
                    if (MODEL.getKota().equals(lokasiResponses.get(i).getNama())) {
                        bind.spinnerKotaKabupaten.setSelection(i);
                    }
                }

                bind.spinnerKotaKabupaten.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        viewModel.getKecamatan(idLokasi).observe(getOwner(), lokasiResponses -> {
            if (lokasiResponses != null) {
                ArrayAdapter<LokasiResponse> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.item_spinner, lokasiResponses);
                bind.spinnerKecamatan.setAdapter(adapter);
                for (int i = 0; i < lokasiResponses.size(); i++) {
                    if (MODEL.getKecamatan().equals(lokasiResponses.get(i).getNama())) {
                        bind.spinnerKecamatan.setSelection(i);
                    }
                }
            }
        });
    }

}
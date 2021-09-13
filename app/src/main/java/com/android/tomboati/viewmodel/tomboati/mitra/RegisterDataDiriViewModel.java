package com.android.tomboati.viewmodel.tomboati.mitra;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.android.tomboati.api.response.LokasiResponse;
import com.android.tomboati.repository.Repository;

import java.util.List;

public class RegisterDataDiriViewModel extends AndroidViewModel {

    private final Repository repository;

    public RegisterDataDiriViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository();
    }



    public MutableLiveData<List<LokasiResponse>> getProvinsi() {
        return repository.getPropinsi();
    }

    public MutableLiveData<List<LokasiResponse>> getKotaKabupaten(String id) {
        return repository.getKabupaten(id);
    }

    public MutableLiveData<List<LokasiResponse>> getKecamatan(String id) {
        return repository.getKecamatan(id);
    }

    public MutableLiveData<List<LokasiResponse>> getKelurahan(String id) {
        return repository.getKelurahan(id);
    }

}

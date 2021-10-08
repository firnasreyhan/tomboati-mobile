package com.tomboati.tour.viewmodel.tomboati.pendaftaran;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.tomboati.tour.api.response.LokasiResponse;
import com.tomboati.tour.repository.Repository;

import java.util.List;

public class PendaftaranDataDiriViewModel extends AndroidViewModel {
    private Repository repository;

    public PendaftaranDataDiriViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
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

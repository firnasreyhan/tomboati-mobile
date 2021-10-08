package com.tomboati.tour.viewmodel.tomboati.mitra;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.tomboati.tour.api.response.BaseResponse;
import com.tomboati.tour.api.response.LokasiResponse;
import com.tomboati.tour.model.AkunModel;
import com.tomboati.tour.repository.Repository;

import java.util.List;

public class RegisterDataDiriViewModel extends AndroidViewModel {

    private final Repository repository;

    public RegisterDataDiriViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository();
    }

    public MutableLiveData<BaseResponse> registerDataDiri(AkunModel akunModel) {
        return this.repository.registerDataDiri(
                "registerDataDiri_post",
                akunModel.getId(),
                akunModel.getName(),
                akunModel.getPropinsi(),
                akunModel.getKota(),
                akunModel.getKecamatan(),
                akunModel.getAddress(),
                akunModel.getKodePos(),
                akunModel.getCountry()
        );
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

}

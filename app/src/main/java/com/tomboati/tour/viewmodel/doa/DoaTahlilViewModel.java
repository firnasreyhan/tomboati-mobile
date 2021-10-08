package com.tomboati.tour.viewmodel.doa;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.tomboati.tour.api.response.BacaanSholatResponse;
import com.tomboati.tour.api.response.TahlilResponse;
import com.tomboati.tour.repository.Repository;

import java.util.List;

public class DoaTahlilViewModel extends AndroidViewModel {
    Repository repository;

    public DoaTahlilViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository();
    }

    public MutableLiveData<TahlilResponse> getDoaTahlil() {
        return repository.getDoaTahlil();
    }

    public MutableLiveData<List<BacaanSholatResponse>> getBacaanSholat() {
        return repository.getBacaanSholat();
    }

    public MutableLiveData<List<BacaanSholatResponse>> getNiatSholat() {
        return repository.getNiatSholat();
    }
}

package com.android.tomboati.viewmodel.tomboati.homepage;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.android.tomboati.api.response.ListPaketVerifyRespone;
import com.android.tomboati.repository.Repository;

public class RiwayatViewModel extends AndroidViewModel {

    private final Repository repository;

    public RiwayatViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
    }

    public MutableLiveData<ListPaketVerifyRespone> getRiwayatPaketWisataHalalVerif(String id) {
        return repository.getRiwayatPaketWisataHalalVerif(id);
    }

    public MutableLiveData<ListPaketVerifyRespone> getRiwayatPaketHajiUmrahVerif(String id) {
        return repository.getRiwayatPaketHajiUmrahVerif(id);
    }








}

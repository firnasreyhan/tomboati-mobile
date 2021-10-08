package com.tomboati.tour.viewmodel.tomboati.homepage;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.tomboati.tour.api.response.ListPaketVerifyRespone;
import com.tomboati.tour.repository.Repository;

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

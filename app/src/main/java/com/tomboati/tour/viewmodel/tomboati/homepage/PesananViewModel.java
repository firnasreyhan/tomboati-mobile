package com.tomboati.tour.viewmodel.tomboati.homepage;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.tomboati.tour.api.response.ListPaketVerifyRespone;
import com.tomboati.tour.repository.Repository;

public class PesananViewModel extends AndroidViewModel {

    private final Repository repository;

    public PesananViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
    }

    public MutableLiveData<ListPaketVerifyRespone> getPaketWisataHalalVerif(String id) {
        return repository.getPaketWisataHalalVerif(id);
    }

    public MutableLiveData<ListPaketVerifyRespone> getPaketHajiUmrahVerif(String id) {
        return repository.getPaketHajiUmrahVerif(id);
    }








}

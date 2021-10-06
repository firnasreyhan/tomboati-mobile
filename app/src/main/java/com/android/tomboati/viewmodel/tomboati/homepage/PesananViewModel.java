package com.android.tomboati.viewmodel.tomboati.homepage;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.android.tomboati.api.response.ListPaketVerifyRespone;
import com.android.tomboati.repository.Repository;

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

package com.tomboati.tour.viewmodel.tomboati.homepage;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.tomboati.tour.api.response.ListPaketVerifyRespone;
import com.tomboati.tour.repository.Repository;

public class PesananViewModel extends AndroidViewModel {

    private MutableLiveData<ListPaketVerifyRespone> getPaketWisataHalalVerifData;
    private MutableLiveData<ListPaketVerifyRespone> getPaketHajiUmrahVerifData;

    private final Repository repository;

    public PesananViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
    }

    public void clearDataPesanan() {
        getPaketWisataHalalVerifData = null;
        getPaketHajiUmrahVerifData = null;
    }

    public MutableLiveData<ListPaketVerifyRespone> getPaketWisataHalalVerif(String id) {
        if(getPaketWisataHalalVerifData == null) {
            getPaketWisataHalalVerifData = repository.getPaketWisataHalalVerif(id);
        }
        return getPaketWisataHalalVerifData;
    }

    public MutableLiveData<ListPaketVerifyRespone> getPaketHajiUmrahVerif(String id) {
        if(getPaketHajiUmrahVerifData == null) {
            getPaketHajiUmrahVerifData = repository.getPaketHajiUmrahVerif(id);
        }
        return getPaketHajiUmrahVerifData;
    }








}

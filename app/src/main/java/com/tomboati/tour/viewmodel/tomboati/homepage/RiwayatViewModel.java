package com.tomboati.tour.viewmodel.tomboati.homepage;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.tomboati.tour.api.response.ListPaketVerifyRespone;
import com.tomboati.tour.repository.Repository;

public class RiwayatViewModel extends AndroidViewModel {

    private MutableLiveData<ListPaketVerifyRespone> getRiwayatPaketWisataHalalVerifData;
    private MutableLiveData<ListPaketVerifyRespone> getRiwayatPaketHajiUmrahVerifData;

    private final Repository repository;

    public RiwayatViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
    }

    public void clearDataRiwayat() {
        getRiwayatPaketHajiUmrahVerifData = null;
        getRiwayatPaketWisataHalalVerifData = null;
    }

    public MutableLiveData<ListPaketVerifyRespone> getRiwayatPaketWisataHalalVerif(String id) {
        if(getRiwayatPaketWisataHalalVerifData == null) {
            getRiwayatPaketWisataHalalVerifData = repository.getRiwayatPaketWisataHalalVerif(id);
        }
        return getRiwayatPaketWisataHalalVerifData;
    }

    public MutableLiveData<ListPaketVerifyRespone> getRiwayatPaketHajiUmrahVerif(String id) {
        if(getRiwayatPaketHajiUmrahVerifData == null) {
            getRiwayatPaketHajiUmrahVerifData = repository.getRiwayatPaketHajiUmrahVerif(id);
        }
        return getRiwayatPaketHajiUmrahVerifData;
    }








}

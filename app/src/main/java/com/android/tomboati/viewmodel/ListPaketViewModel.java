package com.android.tomboati.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.android.tomboati.api.response.PaketMonthResponse;
import com.android.tomboati.api.response.PaketResponse;
import com.android.tomboati.api.response.PaketWisataResponse;
import com.android.tomboati.repository.Repository;

public class ListPaketViewModel extends AndroidViewModel {
    private Repository repository;

    public ListPaketViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
    }

    public MutableLiveData<PaketResponse> getPaket(String paket, String bulan) {
        return repository.getPaket(paket, bulan);
    }

    public MutableLiveData<PaketResponse> getPaketHaji(String paket, String bulan) {
        return repository.getPaketHaji(paket, bulan);
    }

    public MutableLiveData<PaketWisataResponse> getPaketWisata(String paket, String bulan) {
        return repository.getPaketWisata(paket, bulan);
    }

    //==

    public MutableLiveData<PaketMonthResponse> getPaketMonth(String paket) {
        return repository.getPaketMonth(paket);
    }

    public MutableLiveData<PaketMonthResponse> getPaketHajiMonth(String paket) {
        return repository.getPaketHajiMonth(paket);
    }

    public MutableLiveData<PaketMonthResponse> getPaketWisataMonth(String paket) {
        return repository.getPaketWisataMonth(paket);
    }
}

package com.android.tomboati.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.android.tomboati.api.response.PaketMonthResponse;
import com.android.tomboati.api.response.PaketResponse;
import com.android.tomboati.repository.Repository;

public class ListPaketViewModel extends AndroidViewModel {
    private Repository repository;

    public ListPaketViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
    }

    public MutableLiveData<PaketResponse> getPaket(String paket) {
        return repository.getPaket(paket);
    }

    public MutableLiveData<PaketMonthResponse> getPaketMonth(String paket) {
        return repository.getPaketMonth(paket);
    }
}

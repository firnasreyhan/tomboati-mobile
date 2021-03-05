package com.android.tomboati.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.android.tomboati.api.response.KomunitasResponse;
import com.android.tomboati.api.response.TahlilResponse;
import com.android.tomboati.repository.Repository;

import java.util.List;

public class KomunitasViewModel extends AndroidViewModel {

    Repository repository;

    public KomunitasViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository();
    }

    public MutableLiveData<List<KomunitasResponse.Datum>> getKomunitas() {
        return repository.getKomunitas();
    }
}

package com.tomboati.tour.viewmodel.tomboati.homepage;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.tomboati.tour.api.response.KomunitasResponse;
import com.tomboati.tour.repository.Repository;

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

package com.android.tomboati.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.android.tomboati.api.response.TahlilResponse;
import com.android.tomboati.repository.Repository;

public class DoaTahlilViewModel extends AndroidViewModel {
    Repository repository;

    public DoaTahlilViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository();
    }

    public MutableLiveData<TahlilResponse> getDoaTahlil() {
        return repository.getDoaTahlil();
    }
}

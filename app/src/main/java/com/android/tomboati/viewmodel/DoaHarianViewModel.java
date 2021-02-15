package com.android.tomboati.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.android.tomboati.api.response.DoaHarianResponse;
import com.android.tomboati.repository.Repository;

public class DoaHarianViewModel extends AndroidViewModel {

    Repository repository;

    public DoaHarianViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository();
    }

    public MutableLiveData<DoaHarianResponse> getDoaHarian() {
        return repository.getDoaHarian();
    }
}

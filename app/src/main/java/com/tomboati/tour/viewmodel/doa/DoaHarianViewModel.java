package com.tomboati.tour.viewmodel.doa;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.tomboati.tour.api.response.DoaHarianResponse;
import com.tomboati.tour.repository.Repository;

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

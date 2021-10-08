package com.tomboati.tour.viewmodel.quran;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.tomboati.tour.api.response.QuranListResponse;
import com.tomboati.tour.repository.Repository;

public class AlQuranViewModel extends AndroidViewModel {
    private Repository repository;

    public AlQuranViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
    }

    public MutableLiveData<QuranListResponse> getSurahNew() {
        return repository.getListSurahNew();
    }

}

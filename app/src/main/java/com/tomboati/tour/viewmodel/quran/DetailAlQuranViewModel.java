package com.tomboati.tour.viewmodel.quran;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.tomboati.tour.api.response.QuranSurahResponse;
import com.tomboati.tour.repository.Repository;

public class DetailAlQuranViewModel extends AndroidViewModel {
    private Repository repository;

    public DetailAlQuranViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
    }

    public MutableLiveData<QuranSurahResponse> getAyatNew(String idSurah) {
        return repository.getAyatNew(idSurah);
    }
}

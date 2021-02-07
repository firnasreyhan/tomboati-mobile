package com.android.tomboati.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.android.tomboati.api.response.AyatResponse;
import com.android.tomboati.api.response.SurahResponse;
import com.android.tomboati.repository.Repository;

import java.util.List;

public class DetailAlQuranViewModel extends AndroidViewModel {
    private Repository repository;

    public DetailAlQuranViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
    }

    public MutableLiveData<AyatResponse> getAyat(String idSurah) {
        return repository.getAyat(idSurah);
    }
}

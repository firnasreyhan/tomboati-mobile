package com.android.tomboati.viewmodel.quran;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tomboati.api.response.AyatResponse;
import com.android.tomboati.api.response.QuranListResponse;
import com.android.tomboati.api.response.SurahResponse;
import com.android.tomboati.repository.Repository;

import java.util.List;

public class AlQuranViewModel extends AndroidViewModel {
    private Repository repository;

    public AlQuranViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
    }

    public MutableLiveData<List<SurahResponse>> getSurah() {
        return repository.getSurah();
    }

    public MutableLiveData<QuranListResponse> getSurahNew() {
        return repository.getListSurahNew();
    }

}

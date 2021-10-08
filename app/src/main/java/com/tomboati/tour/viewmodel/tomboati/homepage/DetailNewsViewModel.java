package com.tomboati.tour.viewmodel.tomboati.homepage;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.tomboati.tour.api.response.NewsResponse;
import com.tomboati.tour.repository.Repository;

public class DetailNewsViewModel extends AndroidViewModel {
    private Repository repository;

    public DetailNewsViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
    }

    public MutableLiveData<NewsResponse> getNews() {
        return repository.getNews();
    }
}

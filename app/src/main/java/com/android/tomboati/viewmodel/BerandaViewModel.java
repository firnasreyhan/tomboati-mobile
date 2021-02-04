package com.android.tomboati.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.android.tomboati.api.response.JadwalSholatResponse;
import com.android.tomboati.repository.Repository;

public class BerandaViewModel extends AndroidViewModel {
    private Repository repository;

    public BerandaViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
    }

    public MutableLiveData<JadwalSholatResponse> jadwalSholat(int year, int month, int day, double latitude, double longitude, int timezone) {
        return repository.jadwalSholat(
                year,
                (month + 1),
                day,
                latitude,
                longitude,
                timezone);
    }
}

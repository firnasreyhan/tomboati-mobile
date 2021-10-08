package com.tomboati.tour.viewmodel.sholat;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.tomboati.tour.api.response.JadwalSholatResponse;
import com.tomboati.tour.repository.Repository;

public class JadwalSholatViewModel extends AndroidViewModel {
    private Repository repository;

    public JadwalSholatViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository();
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

package com.android.tomboati.viewmodel.sholat;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.android.tomboati.api.response.JadwalSholatResponse;
import com.android.tomboati.api.response.SignInResponse;
import com.android.tomboati.repository.Repository;
import com.android.tomboati.utils.Constant;

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

package com.android.tomboati.viewmodel.tomboati.mitra;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.android.tomboati.api.response.BaseResponse;
import com.android.tomboati.preference.PreferenceAkun;
import com.android.tomboati.repository.Repository;

public class UbahPasswordViewModel extends AndroidViewModel {

    private final Repository repository;
    private final Context context;

    public UbahPasswordViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository();
        this.context = application.getApplicationContext();
    }

    public MutableLiveData<BaseResponse> gantiPassword(String oldPassword, String newPassword, String repeatPassword) {
        return this.repository.gantiPassword(
                "ganti_password",
                PreferenceAkun.getAkun(context).getId(),
                oldPassword,
                newPassword,
                repeatPassword
        );
    }

}

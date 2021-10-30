package com.tomboati.tour.viewmodel.tomboati.mitra;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.tomboati.tour.api.response.BaseResponse;
import com.tomboati.tour.model.UbahPasswordModel;
import com.tomboati.tour.preference.PreferenceAkun;
import com.tomboati.tour.repository.Repository;

public class UbahPasswordViewModel extends AndroidViewModel {

    private final Repository repository;
    private final Context context;

    public UbahPasswordViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository();
        this.context = application.getApplicationContext();
    }

    public MutableLiveData<BaseResponse> gantiPassword(UbahPasswordModel ubahPasswordModel) {
        return this.repository.gantiPassword(
                "ganti_password",
                PreferenceAkun.getAkun(context).getId(),
                ubahPasswordModel.getPasswordNow(),
                ubahPasswordModel.getPasswordNew(),
                ubahPasswordModel.getPasswordNewRepeat()
        );
    }

}

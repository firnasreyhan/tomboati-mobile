package com.tomboati.tour.viewmodel.tomboati.mitra;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.tomboati.tour.api.response.BaseResponse;
import com.tomboati.tour.api.response.PoinResponse;
import com.tomboati.tour.preference.PreferenceAkun;
import com.tomboati.tour.repository.Repository;

public class AkunMitraViewModel extends AndroidViewModel {

    @SuppressLint("StaticFieldLeak")
    private final Context context;
    private Repository repository;
    private MutableLiveData<PoinResponse> getPoinData;

    public AkunMitraViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository();
        this.context = application.getApplicationContext();
    }


    public MutableLiveData<PoinResponse> getPoin() {
        if(getPoinData == null) {
            getPoinData = this.repository.getPoin(
                    "poin",
                    PreferenceAkun.getAkun(context).getUserId()
            );
        }
        return getPoinData;
    }


    public MutableLiveData<BaseResponse> logout() {
        return this.repository.logout("logout", PreferenceAkun.getAkun(context).getUserId());
    }
}

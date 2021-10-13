package com.tomboati.tour.viewmodel.tomboati.mitra;

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

    private Repository repository;
    private MutableLiveData<PoinResponse> getPoinData;
    private Context context;

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
        return this.repository.logout("logout_post", PreferenceAkun.getAkun(context).getEmail());
    }
}

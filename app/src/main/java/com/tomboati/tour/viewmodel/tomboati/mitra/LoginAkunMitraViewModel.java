package com.tomboati.tour.viewmodel.tomboati.mitra;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.tomboati.tour.api.response.AkunMitraResponse;
import com.tomboati.tour.api.response.BaseResponse;
import com.tomboati.tour.api.response.PoinResponse;
import com.tomboati.tour.preference.PreferenceAkun;
import com.tomboati.tour.repository.Repository;

public class LoginAkunMitraViewModel extends AndroidViewModel{

    private final Repository repository;
    @SuppressLint("StaticFieldLeak")
    private final Context context;

    public LoginAkunMitraViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository();
        this.context = application.getApplicationContext();
    }

    public MutableLiveData<AkunMitraResponse> loginMitra(String username, String password, String token) {
        return this.repository.loginMitra("login_post", username, password, token);
    }

}

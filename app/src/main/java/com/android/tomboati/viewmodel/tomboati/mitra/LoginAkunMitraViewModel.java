package com.android.tomboati.viewmodel.tomboati.mitra;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.android.tomboati.api.response.AkunMitraResponse;
import com.android.tomboati.api.response.BaseResponse;
import com.android.tomboati.preference.PreferenceAkun;
import com.android.tomboati.repository.Repository;
import com.google.firebase.iid.FirebaseInstanceId;

public class LoginAkunMitraViewModel extends AndroidViewModel {

    private final Repository repository;
    @SuppressLint("StaticFieldLeak")
    private final Context context;

    public LoginAkunMitraViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository();
        this.context = application.getApplicationContext();
    }

    public MutableLiveData<AkunMitraResponse> loginMitra(String username, String password) {
        return this.repository.loginMitra(
                "login_post", username, password, FirebaseInstanceId.getInstance().getToken()
        );
    }

    public MutableLiveData<BaseResponse> logout() {
        return this.repository.logout(
                "logout_post",
                PreferenceAkun.getAkun(context).getEmail()
        );
    }

}

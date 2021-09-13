package com.android.tomboati.viewmodel.tomboati.mitra;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.android.tomboati.api.response.AkunResponse;
import com.android.tomboati.repository.Repository;
import com.google.firebase.iid.FirebaseInstanceId;

public class RegisterAkunMitraViewModel extends AndroidViewModel {

    private final Repository repository;

    public RegisterAkunMitraViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository();
    }

    public MutableLiveData<AkunResponse> registerAkun(String nomorHP, String referral) {
        return this.repository.registerAkun(
                "register",
                nomorHP,
                referral,
                FirebaseInstanceId.getInstance().getToken()
        );
    }

}

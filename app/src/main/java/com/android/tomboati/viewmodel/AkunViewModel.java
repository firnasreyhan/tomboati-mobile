package com.android.tomboati.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.android.tomboati.api.response.SignInResponse;
import com.android.tomboati.repository.Repository;
import com.android.tomboati.utils.Constant;

public class AkunViewModel extends AndroidViewModel {
    private Repository repository;

    public AkunViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository();
    }

    public MutableLiveData<SignInResponse> signIn(String email, String password) {
        return repository.signIn(email, password);
    }
}

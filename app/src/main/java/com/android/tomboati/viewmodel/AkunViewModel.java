package com.android.tomboati.viewmodel;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.android.tomboati.api.response.BaseResponse;
import com.android.tomboati.api.response.SignInResponse;
import com.android.tomboati.preference.AppPreference;
import com.android.tomboati.repository.Repository;
import com.android.tomboati.utils.Constant;
import com.android.tomboati.utils.notif.Token;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

public class AkunViewModel extends AndroidViewModel {
    private Repository repository;
    private Context context;
    private String email;

    public AkunViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository();
        context = application.getApplicationContext();
    }

    public MutableLiveData<SignInResponse> signIn(String email, String password) {
        this.email = email;
        return repository.signIn(email, password, updateToken());
    }

    public MutableLiveData<BaseResponse> signOut() {
        String userKey = AppPreference.getUser(context).getEmail().replaceAll("[-+.^:,]","");
        FirebaseDatabase.getInstance().getReference("TomboAti").child("Token").child(userKey).removeValue();
        return repository.signOut(AppPreference.getUser(context).getEmail());
    }

    private String updateToken() {
        String refreshToken = FirebaseInstanceId.getInstance().getToken();
        String userKey = this.email.replaceAll("[-+.^:,]","");
        Log.e("userKey", userKey);
        Token token = new Token(refreshToken);
        FirebaseDatabase.getInstance().getReference("TomboAti").child("Token").child(userKey).setValue(token);
        return refreshToken;
    }
}

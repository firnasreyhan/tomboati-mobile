package com.android.tomboati.viewmodel.tomboati.auth;

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

    public AkunViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository();
        context = application.getApplicationContext();
    }

    public MutableLiveData<SignInResponse> signIn(String email, String password) {
        Log.e("usertoken", updateToken(email));
        return repository.signIn(email, password, updateToken(email));
    }

    public MutableLiveData<BaseResponse> resetPassword(String idUserRegister) {
        return repository.resetPassword(idUserRegister);
    }

    public MutableLiveData<BaseResponse> signOut() {
        String userKey = AppPreference.getUser(context).getEmail().replaceAll("[-+.^:,]","");
        FirebaseDatabase.getInstance().getReference("TomboAti").child("Token").child(userKey).removeValue();
        return repository.signOut(AppPreference.getUser(context).getEmail());
    }

    private String updateToken(String email) {
        String refreshToken = FirebaseInstanceId.getInstance().getToken();
        String userKey = email.replaceAll("[-+.^:,]","");
        Log.e("userKey", userKey);
        Token token = new Token(refreshToken);
        FirebaseDatabase.getInstance().getReference("TomboAti").child("Token").child(userKey).setValue(token);
        return refreshToken;
    }
}

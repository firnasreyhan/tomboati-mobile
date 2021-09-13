package com.android.tomboati.viewmodel.tomboati.auth;

import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.android.tomboati.api.response.BaseResponse;
import com.android.tomboati.api.response.SignInResponse;
import com.android.tomboati.repository.Repository;
import com.android.tomboati.utils.ImageSaves;
import com.android.tomboati.utils.notif.Token;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class UpdateProfileViewModel extends AndroidViewModel {
    private Repository repository;
    private ImageSaves imageSaves;

    public UpdateProfileViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
        imageSaves = new ImageSaves(application.getApplicationContext());
    }

    public MutableLiveData<BaseResponse> updateProfile(String idUser, String noKTP_, String email_, String namaLengkap_, String noHP_) {
        return repository.updateProfile(
                idUser,
                RequestBody.create(MediaType.parse("text/plain"), noKTP_),
                RequestBody.create(MediaType.parse("text/plain"), email_),
                RequestBody.create(MediaType.parse("text/plain"), namaLengkap_),
                RequestBody.create(MediaType.parse("text/plain"), "62"+ noHP_.substring(1)));
    }

    public MutableLiveData<BaseResponse> updateFileKTP(String idUser, Uri fileKTP) {
        return repository.updateFileKTP(
                idUser,
                compressFile(imageSaves.saveToPictureFromUri(fileKTP), "fileKTP")
        );
    }

    public MutableLiveData<BaseResponse> updateFoto(String idUser, Uri foto) {
        return repository.updateFoto(
                idUser,
                compressFile(imageSaves.saveToPictureFromUri(foto), "foto")
        );
    }

    public MutableLiveData<SignInResponse> signIn(String email, String password) {
        return repository.signIn(email, password, updateToken(email));
    }

    private MultipartBody.Part compressFile(File file, String path) {
        return MultipartBody.Part.createFormData(path, file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
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

package com.android.tomboati.viewmodel;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.android.tomboati.api.response.BaseResponse;
import com.android.tomboati.api.response.SignInResponse;
import com.android.tomboati.preference.AppPreference;
import com.android.tomboati.repository.Repository;
import com.android.tomboati.utils.Constant;
import com.android.tomboati.utils.ImageSaves;
import com.android.tomboati.utils.notif.Token;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import id.zelory.compressor.Compressor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class SignUpViewModel extends AndroidViewModel {
    private Repository repository;
    private ImageSaves imageSaves;

    public SignUpViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository();
        imageSaves = new ImageSaves(application.getApplicationContext());
    }

    public MutableLiveData<BaseResponse> signUp(String noKTP_, String email_, String password_, String namaLengkap_, String noHP_, Uri fileKTP, Uri foto) {
        RequestBody noKTP = RequestBody.create(MediaType.parse("text/plain"), noKTP_);
        RequestBody email = RequestBody.create(MediaType.parse("text/plain"), email_);
        RequestBody password = RequestBody.create(MediaType.parse("text/plain"), password_);
        RequestBody namaLengkap = RequestBody.create(MediaType.parse("text/plain"), namaLengkap_);
        RequestBody noHP = RequestBody.create(MediaType.parse("text/plain"), "62"+ noHP_.substring(1));
        RequestBody token = RequestBody.create(MediaType.parse("text/plain"), updateToken(email_));
        return repository.signUp(
                noKTP,
                email,
                password,
                namaLengkap,
                noHP,
                token,
                compressFile(imageSaves.saveToPictureFromUri(fileKTP),"fileKTP"),
                compressFile(imageSaves.saveToPictureFromUri(foto),"foto")
        );
    }

    public MutableLiveData<SignInResponse> signIn(String email, String password) {
        return repository.signIn(email, password, updateToken(email));
    }

    private String updateToken(String email) {
        String refreshToken = FirebaseInstanceId.getInstance().getToken();
        String userKey = email.replaceAll("[-+.^:,]","");
        Log.e("userKey", userKey);
        Token token = new Token(refreshToken);
        FirebaseDatabase.getInstance().getReference("TomboAti").child("Token").child(userKey).setValue(token);
        return refreshToken;
    }

    private MultipartBody.Part compressFile(File file, String path) {
        return MultipartBody.Part.createFormData(path, file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
    }
}

package com.android.tomboati.viewmodel.tomboati.mitra;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.android.tomboati.api.response.AkunResponse;
import com.android.tomboati.api.response.BaseResponse;
import com.android.tomboati.model.AkunModel;
import com.android.tomboati.repository.Repository;
import com.android.tomboati.utils.ImageSaves;
import com.google.firebase.iid.FirebaseInstanceId;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class RegisterAkunMitraViewModel extends AndroidViewModel {

    private final Repository repository;
    private final ImageSaves imageSaves;

    public RegisterAkunMitraViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository();
        imageSaves = new ImageSaves(application.getApplicationContext());
    }

    public MutableLiveData<AkunResponse> registerAkun(String nomorHP, String referral) {
        return this.repository.registerAkun(
                "register",
                nomorHP,
                referral,
                FirebaseInstanceId.getInstance().getToken()
        );
    }

    public MutableLiveData<BaseResponse> registerMitra(AkunModel akunModel) {
        return this.repository.registerMitra(
                "registerMitra_post",
                RequestBody.create(MediaType.parse("text/plain"), akunModel.getKtp()),
                RequestBody.create(MediaType.parse("text/plain"), akunModel.getEmail()),
                RequestBody.create(MediaType.parse("text/plain"), akunModel.getName()),
                RequestBody.create(MediaType.parse("text/plain"), akunModel.getHphone()),
                RequestBody.create(MediaType.parse("text/plain"), akunModel.getAddress()),
                RequestBody.create(MediaType.parse("text/plain"), akunModel.getUserId()),
                RequestBody.create(MediaType.parse("text/plain"), akunModel.getKecamatan()),
                RequestBody.create(MediaType.parse("text/plain"), akunModel.getKota()),
                RequestBody.create(MediaType.parse("text/plain"), akunModel.getPropinsi()),
                RequestBody.create(MediaType.parse("text/plain"), akunModel.getKodePos()),
                RequestBody.create(MediaType.parse("text/plain"), akunModel.getCountry()),
                RequestBody.create(MediaType.parse("text/plain"), akunModel.getBank()),
                RequestBody.create(MediaType.parse("text/plain"), akunModel.getRekening()),
                RequestBody.create(MediaType.parse("text/plain"), akunModel.getAtasNama()),
                RequestBody.create(MediaType.parse("text/plain"), akunModel.getCabang()),
                RequestBody.create(MediaType.parse("text/plain"), akunModel.getReferral()),
                compressFile(imageSaves.saveToPictureFromUri(Uri.parse(akunModel.getFotoKTP())), "fotoktp"),
                compressFile(imageSaves.saveToPictureFromUri(Uri.parse(akunModel.getPhoto())), "fotoprofil"),
                compressFile(imageSaves.saveToPictureFromUri(Uri.parse(akunModel.getBuktiBayar())), "buktibayar")
        );
    }

    private MultipartBody.Part compressFile(File file, String path) {
        return MultipartBody.Part.createFormData(path, file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
    }

}

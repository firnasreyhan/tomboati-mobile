package com.tomboati.tour.viewmodel.tomboati.mitra;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.tomboati.tour.api.response.AkunResponse;
import com.tomboati.tour.api.response.BaseResponse;
import com.tomboati.tour.model.AkunModel;
import com.tomboati.tour.repository.Repository;
import com.tomboati.tour.utils.ImageSaves;

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

    public MutableLiveData<AkunResponse> registerAkun(String nomorHP, String referral, String token) {
        return this.repository.registerAkun(
                "register",
                nomorHP,
                referral,
                token
        );
    }

    public MutableLiveData<BaseResponse> registerDataDiri(AkunModel akunModel) {
        return this.repository.registerDataDiri(
                "registerDataDiri_post",
                akunModel.getId(),
                akunModel.getName(),
                akunModel.getPropinsi(),
                akunModel.getKota(),
                akunModel.getKecamatan(),
                akunModel.getAddress(),
                akunModel.getKodePos(),
                akunModel.getCountry()
        );
    }

    public MutableLiveData<BaseResponse> registerMitra(AkunModel akunModel) {
        return this.repository.registerMitra(
                "registerMitra_post",
                akunModel.getId(),
                RequestBody.create(MediaType.parse("text/plain"), akunModel.getKtp()),
                RequestBody.create(MediaType.parse("text/plain"), akunModel.getEmail()),
                RequestBody.create(MediaType.parse("text/plain"), akunModel.getUserId()),
                RequestBody.create(MediaType.parse("text/plain"), akunModel.getBank()),
                RequestBody.create(MediaType.parse("text/plain"), akunModel.getRekening()),
                RequestBody.create(MediaType.parse("text/plain"), akunModel.getAtasNama()),
                RequestBody.create(MediaType.parse("text/plain"), akunModel.getCabang()),
                compressFile(imageSaves.saveToPictureFromUri(Uri.parse(akunModel.getFotoKTP())), "fotoktp"),
                compressFile(imageSaves.saveToPictureFromUri(Uri.parse(akunModel.getPhoto())), "fotoprofil"),
                compressFile(imageSaves.saveToPictureFromUri(Uri.parse(akunModel.getBuktiBayar())), "buktibayar")
        );
    }

    private MultipartBody.Part compressFile(File file, String path) {
        return MultipartBody.Part.createFormData(path, file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
    }

}
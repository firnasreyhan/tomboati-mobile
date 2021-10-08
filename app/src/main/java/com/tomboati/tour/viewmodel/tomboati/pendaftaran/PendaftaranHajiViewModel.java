package com.tomboati.tour.viewmodel.tomboati.pendaftaran;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.tomboati.tour.api.response.BaseResponse;
import com.tomboati.tour.model.DataHajiBadalModel;
import com.tomboati.tour.repository.Repository;
import com.tomboati.tour.utils.ImageSaves;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class PendaftaranHajiViewModel  extends AndroidViewModel {

    private Repository repository;
    private ImageSaves imageSaves;

    public PendaftaranHajiViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
        imageSaves = new ImageSaves(application.getApplicationContext());
    }

    public MutableLiveData<BaseResponse> pendaftaranBadalHaji(DataHajiBadalModel model) {
        return repository.pendaftaranBadalHaji(
            RequestBody.create(MediaType.parse("text/plain"), model.getIdUserRegister()),
            RequestBody.create(MediaType.parse("text/plain"), model.getIdPaket()),
            compressFile(imageSaves.saveToPictureFromUri(Uri.parse(model.getFcKTPAlmarhum())),"fcKTPAlmarhum"),
            compressFile(imageSaves.saveToPictureFromUri(Uri.parse(model.getFcKKAlmarhum())),"fcKKAlmarhum"),
            compressFile(imageSaves.saveToPictureFromUri(Uri.parse(model.getFcFotoAlmarhum())),"fcFotoAlmarhum")
        );
    }

    private MultipartBody.Part compressFile(File file, String path) {
        return MultipartBody.Part.createFormData(path, file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
    }
}

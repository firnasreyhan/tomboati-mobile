package com.tomboati.tour.viewmodel.tomboati.homepage;

import android.app.Application;
import android.content.Context;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.tomboati.tour.api.response.BaseResponse;
import com.tomboati.tour.preference.PreferenceAkun;
import com.tomboati.tour.repository.Repository;
import com.tomboati.tour.utils.ImageSaves;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ImageChatViewModel extends AndroidViewModel {

    private Repository repository;
    private ImageSaves imageSaves;
    private final Context context;

    public ImageChatViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
        imageSaves = new ImageSaves(application.getApplicationContext());
        context = application.getApplicationContext();
    }

    public MutableLiveData<BaseResponse> sendChat(String message_, Uri img_) {
        return repository.sendChat(
                RequestBody.create(MediaType.parse("text/plain"), message_),
                RequestBody.create(MediaType.parse("text/plain"), PreferenceAkun.getAkun(context).getIdChatRoom()),
                compressFile(imageSaves.saveToPictureFromUri(img_), "img")
        );
    }
    private MultipartBody.Part compressFile(File file, String path) {
        return MultipartBody.Part.createFormData(path, file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
    }
}

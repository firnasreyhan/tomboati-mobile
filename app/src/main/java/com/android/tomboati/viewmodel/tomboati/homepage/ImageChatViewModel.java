package com.android.tomboati.viewmodel.tomboati.homepage;

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
import com.android.tomboati.preference.AppPreference;
import com.android.tomboati.repository.Repository;
import com.android.tomboati.utils.ImageSaves;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import id.zelory.compressor.Compressor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ImageChatViewModel extends AndroidViewModel {

    private Repository repository;
    private ImageSaves imageSaves;
    private Context context;

    public ImageChatViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
        imageSaves = new ImageSaves(application.getApplicationContext());
        context = application.getApplicationContext();
    }

    public MutableLiveData<BaseResponse> sendChat(String message_, Uri img_) {
        RequestBody message = RequestBody.create(MediaType.parse("text/plain"), message_);
        RequestBody idChatRoom = RequestBody.create(MediaType.parse("text/plain"), AppPreference.getUser(context).getIdChatRoom());
        MultipartBody.Part images = compressFile(imageSaves.saveToPictureFromUri(img_), "img");
        return repository.sendChat(message, idChatRoom, images);
    }
    private MultipartBody.Part compressFile(File file, String path) {
        return MultipartBody.Part.createFormData(path, file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
    }
}

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
import com.android.tomboati.preference.AppPreference;
import com.android.tomboati.repository.Repository;

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
    private Context context;

    public SignUpViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
        context = application.getApplicationContext();
    }

    public MutableLiveData<BaseResponse> signUp(String noKTP_, String email_, String password_, String namaLengkap_, String noHP_, Uri fileKTP, Uri foto) {
        RequestBody noKTP = RequestBody.create(MediaType.parse("text/plain"), noKTP_);
        RequestBody email = RequestBody.create(MediaType.parse("text/plain"), email_);
        RequestBody password = RequestBody.create(MediaType.parse("text/plain"), password_);
        RequestBody namaLengkap = RequestBody.create(MediaType.parse("text/plain"), namaLengkap_);
        RequestBody noHP = RequestBody.create(MediaType.parse("text/plain"), "+62"+ noHP_.substring(1));
        return repository.signUp(
                noKTP,
                email,
                password,
                namaLengkap,
                noHP,
                compressFile(fileKTP, "fileKTP"),
                compressFile(foto, "foto")
        );
    }

    private File createTempFile(Uri uri) {
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                , System.currentTimeMillis() +".JPEG");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        //write the bytes in file
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(byteArray);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    private MultipartBody.Part compressFile(Uri uri, String path) {
        File file = new File(uri.getPath());
        try {
            File fileCompress = new Compressor(context)
                    .setCompressFormat(Bitmap.CompressFormat.JPEG)
                    .compressToFile(file);
            File file1 = createTempFile(Uri.fromFile(fileCompress.getAbsoluteFile()));
            Log.e("path", file1.getAbsolutePath());
            return MultipartBody.Part.createFormData(path, file1.getName(), RequestBody.create(MediaType.parse("image/*"), file1));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

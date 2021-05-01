package com.android.tomboati.viewmodel;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.android.tomboati.api.response.BaseResponse;
import com.android.tomboati.api.response.DetailPembayaranResponse;
import com.android.tomboati.api.response.PembayaranResponse;
import com.android.tomboati.repository.Repository;
import com.android.tomboati.utils.ImageSaves;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class PembayaranViewModel extends AndroidViewModel {

    private final Repository repository;
    private final ImageSaves imageSaves;

    public PembayaranViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository();
        imageSaves = new ImageSaves(application.getApplicationContext());
    }

    public MutableLiveData<BaseResponse> postPembayaran(
        String idTransaksi,
        String jumlahPembayaran,
        String tanggalPembayaran,
        String deskripsi,
        String uri
    ) {
        return repository.postPembayaran(
                idTransaksi,
                RequestBody.create(MediaType.parse("text/plain"), jumlahPembayaran),
                RequestBody.create(MediaType.parse("text/plain"), tanggalPembayaran),
                RequestBody.create(MediaType.parse("text/plain"), deskripsi),
                compressFile(imageSaves.saveToPictureFromUri(Uri.parse(uri)), "buktiPembayaran")
        );
    };

    public MutableLiveData<PembayaranResponse> getPembayaran(String id) {
        return repository.getPembayaran(id);
    }

    public MutableLiveData<DetailPembayaranResponse> getDetailPembayaran(String idPembayaran) {
        return repository.getDetailPembayaran(idPembayaran);
    }

    private MultipartBody.Part compressFile(File file, String path) {
        return MultipartBody.Part.createFormData(path, file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
    }
}

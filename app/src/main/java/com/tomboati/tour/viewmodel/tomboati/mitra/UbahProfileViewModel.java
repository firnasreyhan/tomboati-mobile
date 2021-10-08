package com.tomboati.tour.viewmodel.tomboati.mitra;

import android.app.Application;
import android.content.Context;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.tomboati.tour.api.response.BaseResponse;
import com.tomboati.tour.api.response.LokasiResponse;
import com.tomboati.tour.api.response.UbahFotoProfileResponse;
import com.tomboati.tour.preference.PreferenceAkun;
import com.tomboati.tour.repository.Repository;
import com.tomboati.tour.utils.ImageSaves;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class UbahProfileViewModel extends AndroidViewModel {

    private Repository repository;
    private Context context;
    private final ImageSaves imageSaves;

    public UbahProfileViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository();
        context = application.getApplicationContext();
        imageSaves = new ImageSaves(application.getApplicationContext());
    }

    public MutableLiveData<UbahFotoProfileResponse> gantiFotoProfil(String fotoProfil) {
        return this.repository.gantiFotoProfil(
                "ubah_foto_profil",
                PreferenceAkun.getAkun(context).getId(),
                compressFile(imageSaves.saveToPictureFromUri(Uri.parse(fotoProfil)),"fotoprofil")
        );
    }

    public MutableLiveData<BaseResponse> gantiDataProfil(
            String nama,
            String provinsi,
            String kota,
            String kecamatan,
            String address,
            String kodePos,
            String country,
            String email
    ) {
        return this.repository.gantiDataProfil(
                "ubah_data_profil",
                PreferenceAkun.getAkun(context).getId(),
                nama,
                provinsi,
                kota,
                kecamatan,
                address,
                kodePos,
                country,
                email
        );
    }

    public MutableLiveData<List<LokasiResponse>> getProvinsi() {
        return repository.getPropinsi();
    }

    public MutableLiveData<List<LokasiResponse>> getKotaKabupaten(String id) {
        return repository.getKabupaten(id);
    }

    public MutableLiveData<List<LokasiResponse>> getKecamatan(String id) {
        return repository.getKecamatan(id);
    }

    private MultipartBody.Part compressFile(File file, String path) {
        return MultipartBody.Part.createFormData(path, file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
    }

}

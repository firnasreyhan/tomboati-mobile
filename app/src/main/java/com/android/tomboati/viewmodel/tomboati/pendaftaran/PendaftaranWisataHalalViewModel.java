package com.android.tomboati.viewmodel.tomboati.pendaftaran;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.android.tomboati.api.response.BaseResponse;
import com.android.tomboati.model.PesananaModel;
import com.android.tomboati.repository.Repository;
import com.android.tomboati.utils.ImageSaves;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class PendaftaranWisataHalalViewModel extends AndroidViewModel {

    private final Repository repository;
    private ImageSaves imageSaves;

    public PendaftaranWisataHalalViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository();
        imageSaves = new ImageSaves(application.getApplicationContext());
    }


    public MutableLiveData<BaseResponse> pendaftaranWisataHalal(PesananaModel model) {
        return repository.pendaftaranWisataHalal(
                RequestBody.create(MediaType.parse("text/plain"), model.getIdUserRegister()),
                RequestBody.create(MediaType.parse("text/plain"), model.getEmail()),
                RequestBody.create(MediaType.parse("text/plain"), model.getNomorPaspor()),
                RequestBody.create(MediaType.parse("text/plain"), model.getTempatDikeluarkan()),
                RequestBody.create(MediaType.parse("text/plain"), model.getTanggalPenerbitanPaspor()),
                RequestBody.create(MediaType.parse("text/plain"), model.getTanggalBerakhirPaspor()),
                RequestBody.create(MediaType.parse("text/plain"), model.getTempatLahir()),
                RequestBody.create(MediaType.parse("text/plain"), model.getTanggalLahir()),
                RequestBody.create(MediaType.parse("text/plain"), model.getJenisKelamin()),
                RequestBody.create(MediaType.parse("text/plain"), model.getStatusPerkawinan()),
                RequestBody.create(MediaType.parse("text/plain"), model.getKewarganegaraan()),
                RequestBody.create(MediaType.parse("text/plain"), model.getAlamat()),
                RequestBody.create(MediaType.parse("text/plain"), model.getKelurahan()),
                RequestBody.create(MediaType.parse("text/plain"), model.getKecamatan()),
                RequestBody.create(MediaType.parse("text/plain"), model.getKotaKabupaten()),
                RequestBody.create(MediaType.parse("text/plain"), model.getProvinsi()),
                RequestBody.create(MediaType.parse("text/plain"), model.getKodePOS()),
                RequestBody.create(MediaType.parse("text/plain"), model.getNomorHP()),
                RequestBody.create(MediaType.parse("text/plain"), model.getPekerjaan()),
                RequestBody.create(MediaType.parse("text/plain"), model.getRiwayatPenyakit()),
                RequestBody.create(MediaType.parse("text/plain"), model.getNamaLengkap()),
                compressFile(imageSaves.saveToPictureFromUri(Uri.parse(model.getFileKTP())),"fileKTP"),
                compressFile(imageSaves.saveToPictureFromUri(Uri.parse(model.getFileKK())), "fileKK"),
                compressFile(imageSaves.saveToPictureFromUri(Uri.parse(model.getFilePaspor())), "filePaspor"),
                compressFile(imageSaves.saveToPictureFromUri(Uri.parse(model.getFileBukuNikah())), "fileBukuNikah"),
                compressFile(imageSaves.saveToPictureFromUri(Uri.parse(model.getFileAkteKelahiran())), "fileAkteKelahiran"),
                compressFile(imageSaves.saveToPictureFromBitmap(model.getTtdPendaftar()), "ttdPendaftar"),
                RequestBody.create(MediaType.parse("text/plain"), model.getIdPaket()),
                RequestBody.create(MediaType.parse("text/plain"), model.getTanggalBerangkat()),
                RequestBody.create(MediaType.parse("text/plain"), model.getSheet()),
                RequestBody.create(MediaType.parse("text/plain"), model.getSheetHarga()),
                RequestBody.create(MediaType.parse("text/plain"), "14:00"),
                RequestBody.create(MediaType.parse("text/plain"), model.getNamaLengkapKeluarga()),
                RequestBody.create(MediaType.parse("text/plain"), model.getAlamatKeluarga()),
                RequestBody.create(MediaType.parse("text/plain"), model.getKelurahanKeluarga()),
                RequestBody.create(MediaType.parse("text/plain"), model.getKecamatanKeluarga()),
                RequestBody.create(MediaType.parse("text/plain"), model.getKotakabupatenKeluarga()),
                RequestBody.create(MediaType.parse("text/plain"), model.getProvinsiKeluarga()),
                RequestBody.create(MediaType.parse("text/plain"), model.getKodePOSKeluarga()),
                RequestBody.create(MediaType.parse("text/plain"), model.getNomorHPKeluarga())
        );
    }

    private MultipartBody.Part compressFile(File file, String path) {
        return MultipartBody.Part.createFormData(path, file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
    }




}

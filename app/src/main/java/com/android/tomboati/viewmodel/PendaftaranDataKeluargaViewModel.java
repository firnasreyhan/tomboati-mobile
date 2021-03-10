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
import com.android.tomboati.api.response.LokasiResponse;
import com.android.tomboati.api.response.SignInResponse;
import com.android.tomboati.model.PesananaModel;
import com.android.tomboati.preference.AppPreference;
import com.android.tomboati.repository.Repository;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import id.zelory.compressor.Compressor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class PendaftaranDataKeluargaViewModel extends AndroidViewModel {
    private Repository repository;
    private Context context;

    public PendaftaranDataKeluargaViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
        context = application.getApplicationContext();
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

    public MutableLiveData<List<LokasiResponse>> getKelurahan(String id) {
        return repository.getKelurahan(id);
    }

    public MutableLiveData<BaseResponse> pendaftaran(PesananaModel model) {
        return repository.pendaftaran(
                RequestBody.create(MediaType.parse("text/plain"), AppPreference.getUser(context).getIdUserRegister()),
                RequestBody.create(MediaType.parse("text/plain"), AppPreference.getUser(context).getEmail()),
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
                compressFile(model.getFileKTP(), "fileKTP"),
                compressFile(model.getFileKK(), "fileKTP"),
                compressFile(model.getFilePaspor(), "fileKTP"),
                compressFile(model.getFileBukuNikah(), "fileKTP"),
                compressFile(model.getFileAkteKelahiran(), "fileKTP"),
                compressFile(model.getTtdPendaftar(), "fileKTP"),
                null,
                null,
                null,
                RequestBody.create(MediaType.parse("text/plain"), model.getIdPaket()),
                RequestBody.create(MediaType.parse("text/plain"), model.getTanggalBerangkat()),
                RequestBody.create(MediaType.parse("text/plain"), model.getSheet()),
                RequestBody.create(MediaType.parse("text/plain"), model.getSheetHarga()),
                RequestBody.create(MediaType.parse("text/plain"), model.getWaktu()),
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

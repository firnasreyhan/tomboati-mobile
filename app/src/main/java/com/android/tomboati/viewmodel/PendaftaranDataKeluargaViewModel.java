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
import com.android.tomboati.model.PesananaModel;
import com.android.tomboati.repository.Repository;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

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
                compressFile(saveToPictureFromUri(Uri.parse(model.getFileKTP())), "fileKTP"),
                compressFile(saveToPictureFromUri(Uri.parse(model.getFileKK())), "fileKK"),
                compressFile(saveToPictureFromUri(Uri.parse(model.getFilePaspor())), "filePaspor"),
                compressFile(saveToPictureFromUri(Uri.parse(model.getFileBukuNikah())), "fileBukuNikah"),
                compressFile(saveToPictureFromUri(Uri.parse(model.getFileAkteKelahiran())), "fileAkteKelahiran"),

                compressFile(saveToPictureFromBitmap(model.getTtdPendaftar()), "ttdPendaftar"),
//                compressFile(saveToPictureFromUri(Uri.parse(model.getFileKTP())), "fcKTPAlmarhum"),
//                compressFile(saveToPictureFromUri(Uri.parse(model.getFileKTP())), "fcKKAlmarhum"),
//                compressFile(saveToPictureFromUri(Uri.parse(model.getFileKTP())), "fcFotoAlmarhum"),
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

    private File saveToPictureFromUri(Uri u) {
        Bitmap b = null;
        try {
            b = MediaStore.Images.Media.getBitmap(context.getContentResolver(), u);
        } catch (Exception e) {
            Log.e("Error save images : ", e.getMessage());
        }
        return saveToPictureFromBitmap(b);
    }

    private File saveToPictureFromBitmap(Bitmap b) {
        String rootPath = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES).toString();
        String nameTimeStamp = "" + System.currentTimeMillis();
        File file = new File(rootPath, nameTimeStamp + ".png");

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Bitmap scale = scaleDown(b, true);
        b.compress(Bitmap.CompressFormat.PNG, 100, out);
        final byte[] byteOut = out.toByteArray();

        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(byteOut);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            Log.e("Error save bitmap : ", e.getMessage());
        }

        return file;
    }

    private Bitmap scaleDown(Bitmap b, boolean filter) {
        final double ratio = Math.min((double) 600 / b.getWidth(), (double) 600 / b.getHeight());
        final double width = Math.round(ratio * b.getWidth());
        final double height = Math.round(ratio * b.getHeight());
        return Bitmap.createScaledBitmap(b, (int) width, (int) height, filter);
    }

    private MultipartBody.Part compressFile(File file, String path) {
        return MultipartBody.Part.createFormData(path, file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
    }


}

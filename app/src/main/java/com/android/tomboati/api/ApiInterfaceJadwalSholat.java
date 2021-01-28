package com.android.tomboati.api;

import com.android.tomboati.api.response.BaseResponse;
import com.android.tomboati.api.response.JadwalSholatResponse;
import com.android.tomboati.api.response.SignInResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiInterfaceJadwalSholat {
    //jadwal sholat
    @GET("api/APIH5tENuctWV/shalat/masehi/{tahun}/{bulan}/{tanggal}/{latitude}/{longitude}/{gmt}")
    Call<JadwalSholatResponse> jadwalSholat(
            @Path("tahun") int tahun,
            @Path("bulan") int bulan,
            @Path("tanggal") int tanggal,
            @Path("latitude") double latitude,
            @Path("longitude") double longitude,
            @Path("gmt") int gmt
    );
}

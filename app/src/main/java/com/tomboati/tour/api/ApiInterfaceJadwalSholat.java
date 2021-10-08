package com.tomboati.tour.api;

import com.tomboati.tour.api.response.JadwalSholatResponse;

import retrofit2.Call;
import retrofit2.http.GET;
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

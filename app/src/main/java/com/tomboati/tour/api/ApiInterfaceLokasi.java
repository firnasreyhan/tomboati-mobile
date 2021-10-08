package com.tomboati.tour.api;

import com.tomboati.tour.api.response.LokasiResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterfaceLokasi {
    @GET("propinsi.json")
    Call<List<LokasiResponse>> getPropinsi();

    @GET("kabupaten/{id}.json")
    Call<List<LokasiResponse>> getKabupaten(
            @Path("id") String id
    );

    @GET("kecamatan/{id}.json")
    Call<List<LokasiResponse>> getKecamatan(
            @Path("id") String id
    );

    @GET("kelurahan/{id}.json")
    Call<List<LokasiResponse>> getKelurahan(
            @Path("id") String id
    );
}

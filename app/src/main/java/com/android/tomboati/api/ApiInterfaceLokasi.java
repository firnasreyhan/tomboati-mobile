package com.android.tomboati.api;

import com.android.tomboati.api.response.LokasiResponse;
import com.android.tomboati.api.response.MasjidResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

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

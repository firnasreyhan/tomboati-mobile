package com.tomboati.tour.api;

import com.tomboati.tour.api.response.BacaanSholatResponse;
import com.tomboati.tour.api.response.DoaHarianResponse;
import com.tomboati.tour.api.response.TahlilResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterfaceTahlil {

    @GET("api/tahlil")
    Call<TahlilResponse> getDoaTahlil();

    @GET("api/doaharian")
    Call<DoaHarianResponse> getDoaHarian();

    @GET("api/bacaanshalat")
    Call<List<BacaanSholatResponse>> getBacaanSholat();

    @GET("api/niatshalat")
    Call<List<BacaanSholatResponse>> getNiatSholat();

}

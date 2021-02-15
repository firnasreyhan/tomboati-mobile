package com.android.tomboati.api;

import com.android.tomboati.api.response.DoaHarianResponse;
import com.android.tomboati.api.response.TahlilResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterfaceTahlil {

    @GET("api/tahlil")
    Call<TahlilResponse> getDoaTahlil();

    @GET("api/doaharian")
    Call<DoaHarianResponse> getDoaHarian();

}

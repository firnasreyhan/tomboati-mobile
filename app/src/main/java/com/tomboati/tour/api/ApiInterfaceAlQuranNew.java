package com.tomboati.tour.api;

import com.tomboati.tour.api.response.QuranListResponse;
import com.tomboati.tour.api.response.QuranSurahResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterfaceAlQuranNew {

    @GET("surah")
    Call<QuranListResponse> getListSurah();

    @GET("surah/{idSurah}")
    Call<QuranSurahResponse> getSurah(
            @Path("idSurah") String idSurah
    );
}

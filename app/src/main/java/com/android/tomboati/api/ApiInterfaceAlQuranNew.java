package com.android.tomboati.api;

import com.android.tomboati.api.response.QuranListResponse;
import com.android.tomboati.api.response.QuranSurahResponse;

import java.util.List;

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

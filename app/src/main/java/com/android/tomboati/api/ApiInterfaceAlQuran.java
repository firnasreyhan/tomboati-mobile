package com.android.tomboati.api;

import com.android.tomboati.api.response.AyatResponse;
import com.android.tomboati.api.response.BaseResponse;
import com.android.tomboati.api.response.ChatResponse;
import com.android.tomboati.api.response.MasjidResponse;
import com.android.tomboati.api.response.SignInResponse;
import com.android.tomboati.api.response.SurahResponse;

import java.util.List;

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
import retrofit2.http.Query;

public interface ApiInterfaceAlQuran {
    @GET("data")
    Call<List<SurahResponse>> getSurah();

    @GET("surat/{idSurah}")
    Call<List<AyatResponse>> getAyat(
            @Path("idSurah") String idSurah
    );
}

package com.android.tomboati.api;

import com.android.tomboati.api.response.BaseResponse;
import com.android.tomboati.api.response.MasjidResponse;
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
import retrofit2.http.Query;

public interface ApiInterfaceMasjid {
    @GET("geocoding/v5/mapbox.places/{query}.json")
    Call<MasjidResponse> getMasjidTerdekat(
            @Path("query") String query,
            @Query("proximity") String proximity,
            @Query("limit") int limit,
            @Query("access_token") String access_token
    );
}

package com.android.tomboati.api;

import com.android.tomboati.api.response.AkunResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiInterfaceTomboAtiMitra {

    @POST("api.php")
    @FormUrlEncoded
    Call<AkunResponse> registerAkun(
            @Query("function") String function,
            @Field("nomorHP") String nomorHP,
            @Field("referral") String referral,
            @Field("token") String token
    );

}

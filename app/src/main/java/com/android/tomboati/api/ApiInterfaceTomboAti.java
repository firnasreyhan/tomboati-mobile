package com.android.tomboati.api;

import com.android.tomboati.api.response.BaseResponse;
import com.android.tomboati.api.response.JadwalSholatResponse;
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

public interface ApiInterfaceTomboAti {
    @POST("user/login_post")
    @FormUrlEncoded
    Call<SignInResponse> signIn(
            @Field("email") String email,
            @Field("password") String password
    );

    @Multipart
    @POST("user/register_post")
    Call<BaseResponse> signUp(
            @Part("noKTP") RequestBody noKTP,
            @Part("email") RequestBody email,
            @Part("password") RequestBody password,
            @Part("namaLengkap") RequestBody namaLengkap,
            @Part("nomorHP") RequestBody noHP,
            @Part MultipartBody.Part fileKTP,
            @Part MultipartBody.Part foto
    );
}

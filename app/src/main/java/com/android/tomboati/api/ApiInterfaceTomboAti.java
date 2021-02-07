package com.android.tomboati.api;

import com.android.tomboati.api.response.BaseResponse;
import com.android.tomboati.api.response.ChatResponse;
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
            @Field("password") String password,
            @Field("token") String token
    );

    @POST("user/logout_post")
    @FormUrlEncoded
    Call<BaseResponse> signOut(
            @Field("email") String email
    );

    @Multipart
    @POST("user/register_post")
    Call<BaseResponse> signUp(
            @Part("noKTP") RequestBody noKTP,
            @Part("email") RequestBody email,
            @Part("password") RequestBody password,
            @Part("namaLengkap") RequestBody namaLengkap,
            @Part("nomorHP") RequestBody noHP,
            @Part("token") RequestBody token,
            @Part MultipartBody.Part fileKTP,
            @Part MultipartBody.Part foto
    );

    @Multipart
    @POST("chat/chat_post")
    Call<BaseResponse> sendChat(
            @Part("message") RequestBody message,
            @Part("idChatRoom") RequestBody idChatRoom,
            @Part MultipartBody.Part img
    );

    @POST("chat/chat_get")
    @FormUrlEncoded
    Call<ChatResponse> getChat(
            @Field("idChatRoom") String idChatRoom
    );
}

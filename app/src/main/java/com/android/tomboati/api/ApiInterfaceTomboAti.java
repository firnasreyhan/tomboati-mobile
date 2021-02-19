package com.android.tomboati.api;

import com.android.tomboati.api.response.BaseResponse;
import com.android.tomboati.api.response.ChatResponse;
import com.android.tomboati.api.response.JadwalSholatResponse;
import com.android.tomboati.api.response.KataMutiaraResponse;
import com.android.tomboati.api.response.NewsResponse;
import com.android.tomboati.api.response.PaketMonthResponse;
import com.android.tomboati.api.response.PaketResponse;
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

    @GET("paket/paket_get")
    Call<PaketResponse> getPaket(
            @Query("tipe") String tipe,
            @Query("bulan") String bulan
    );

    @GET("Paket/detailPaket")
    Call<PaketResponse> getDetailPaket(
            @Query("idPaket") String idPaket
    );

    @GET("paket/getpaketlimit")
    Call<PaketResponse> getPaketLimit();

    @GET("WisataHalal/getwisatahalallimit")
    Call<PaketResponse> getWisataHalalLimit();

    @Multipart
    @POST("user/updateProfil")
    Call<BaseResponse> updateProfile(
            @Query("idUser") String idUser,
            @Part("noKTP") RequestBody noKTP,
            @Part("email") RequestBody email,
            @Part("password") RequestBody password,
            @Part("namaLengkap") RequestBody namaLengkap,
            @Part("nomorHP") RequestBody noHP
    );

    @Multipart
    @POST("user/updateFileKTP")
    Call<BaseResponse> updateFileKTP(
            @Query("idUser") String idUser,
            @Part MultipartBody.Part fileKTP
    );

    @Multipart
    @POST("user/updateFoto")
    Call<BaseResponse> updateFoto(
            @Query("idUser") String idUser,
            @Part MultipartBody.Part foto
    );

    @GET("paket/getPaketMonth")
    Call<PaketMonthResponse> getPaketMonth(
            @Query("tipe") String tipe
    );

    @GET("News/getNewsLimit")
    Call<NewsResponse> getNews();

    @GET("KataMutiara/getKataMutiaraLimit")
    Call<KataMutiaraResponse> getKataMutiara();
}

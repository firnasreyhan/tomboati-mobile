package com.tomboati.tour.api;

import com.tomboati.tour.api.response.AkunMitraResponse;
import com.tomboati.tour.api.response.AkunResponse;
import com.tomboati.tour.api.response.BaseResponse;
import com.tomboati.tour.api.response.PoinResponse;
import com.tomboati.tour.api.response.UbahFotoProfileResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiInterfaceTomboAtiMitra {

    @FormUrlEncoded
    @POST("api.php")
    Call<AkunResponse> registerAkun(
            @Query("function") String function,
            @Field("nomorHP") String nomorHP,
            @Field("referral") String referral,
            @Field("token") String token
    );

    @FormUrlEncoded
    @POST("User.php")
    Call<BaseResponse> registerDataDiri(
        @Query("function") String function,
        @Query("idUserRegister") String idUserRegister,
        @Field("nama") String nama,
        @Field("provinsi") String provinsi,
        @Field("kota") String kota,
        @Field("kecamatan") String kecamatan,
        @Field("address") String address,
        @Field("kodePos") String kodePos,
        @Field("country") String country
    );

    @Multipart
    @POST("User.php")
    Call<BaseResponse> registerMitra(
            @Query("function") String function,
            @Query("idUserRegister") String idUserRegister,
            @Part("ktp") RequestBody nomorKTP,
            @Part("email") RequestBody email,
            @Part("username") RequestBody username,
            @Part("bank") RequestBody bank,
            @Part("rekening") RequestBody nomorRekening,
            @Part("atasnama") RequestBody atasNama,
            @Part("cabang") RequestBody cabang,
            @Part MultipartBody.Part fotoKtp,
            @Part MultipartBody.Part fotoProfil,
            @Part MultipartBody.Part buktiBayar
    );

    @FormUrlEncoded
    @POST("User.php")
    Call<AkunMitraResponse> loginMitra(
            @Query("function") String function,
            @Field("username") String username,
            @Field("password") String password,
            @Field("token") String token
    );

    @FormUrlEncoded
    @POST("User.php")
    Call<BaseResponse> gantiPassword(
            @Query("function") String function,
            @Query("idUserRegister") String idUserRegister,
            @Field("oldPassword") String oldPassword,
            @Field("newPassword") String newPassword,
            @Field("repeatPassword") String repeatPassword
    );

    @POST("User.php")
    Call<BaseResponse> logout(
            @Query("function") String function,
            @Query("email") String email
    );

    @Multipart
    @POST("User.php")
    Call<UbahFotoProfileResponse> gantiFotoProfil(
            @Query("function") String function,
            @Query("idUserRegister") String idUserRegister,
            @Part MultipartBody.Part fotoProfil
    );

    @FormUrlEncoded
    @POST("User.php")
    Call<BaseResponse> gantiDataProfil(
            @Query("function") String function,
            @Query("idUserRegister") String idUserRegister,
            @Field("nama") String nama,
            @Field("provinsi") String provinsi,
            @Field("kota") String kota,
            @Field("kecamatan") String kecamatan,
            @Field("address") String address,
            @Field("kodePos") String kodePos,
            @Field("country") String country,
            @Field("email") String email
    );

    @GET("User.php")
    Call<PoinResponse> getPoin(
            @Query("function") String function,
            @Query("userid") String userId
    );
}

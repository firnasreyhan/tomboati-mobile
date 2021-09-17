package com.android.tomboati.api;

import com.android.tomboati.api.response.AkunResponse;
import com.android.tomboati.api.response.BaseResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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

    @Multipart
    @POST("User.php")
    Call<BaseResponse> registerMitra(
            @Query("function") String function,
            @Part("ktp") RequestBody nomorKTP,
            @Part("email") RequestBody email,
            @Part("name") RequestBody name,
            @Part("nomorHP") RequestBody nomorHp,
            @Part("address") RequestBody address,
            @Part("username") RequestBody username,
            @Part("kecamatan") RequestBody kecamatan,
            @Part("kota") RequestBody kota,
            @Part("provinsi") RequestBody provinsi,
            @Part("kode_pos") RequestBody kodePos,
            @Part("country") RequestBody country,
            @Part("bank") RequestBody bank,
            @Part("rekening") RequestBody nomorRekening,
            @Part("atasnama") RequestBody atasNama,
            @Part("cabang") RequestBody cabang,
            @Part("referral") RequestBody referral,
            @Part MultipartBody.Part fotoKtp,
            @Part MultipartBody.Part fotoProfil,
            @Part MultipartBody.Part buktiBayar
            );

}

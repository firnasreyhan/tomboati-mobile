package com.android.tomboati.api;

import com.android.tomboati.api.response.BaseResponse;
import com.android.tomboati.api.response.ChatResponse;
import com.android.tomboati.api.response.ItteneraryResponse;
import com.android.tomboati.api.response.JadwalSholatResponse;
import com.android.tomboati.api.response.KataMutiaraResponse;
import com.android.tomboati.api.response.NewsResponse;
import com.android.tomboati.api.response.PaketMonthResponse;
import com.android.tomboati.api.response.PaketResponse;
import com.android.tomboati.api.response.PaketWisataResponse;
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

    @GET("WisataHalal/wisatahalal_get")
    Call<PaketWisataResponse> getPaketWisata(
            @Query("tipe") String tipe,
            @Query("bulan") String bulan
    );

    @GET("Paket/detailPaket")
    Call<PaketResponse> getDetailPaket(
            @Query("idPaket") String idPaket
    );

    @GET("WisataHalal/detailWisataHalal")
    Call<PaketWisataResponse> getDetailPaketWisata(
            @Query("idWisataHalal") String idWisataHalal
    );

    @GET("paket/getpaketlimit")
    Call<PaketResponse> getPaketLimit();

    @GET("WisataHalal/getwisatahalallimit")
    Call<PaketWisataResponse> getWisataHalalLimit();

    @Multipart
    @POST("user/updateProfil")
    Call<BaseResponse> updateProfile(
            @Query("idUser") String idUser,
            @Part("noKTP") RequestBody noKTP,
            @Part("email") RequestBody email,
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

    @GET("WisataHalal/getWisataHalalMonth")
    Call<PaketMonthResponse> getPaketWisataMonth(
            @Query("tipe") String tipe
    );

    @GET("News/getNewsLimit")
    Call<NewsResponse> getNews();

    @GET("KataMutiara/getKataMutiaraLimit")
    Call<KataMutiaraResponse> getKataMutiara();

    @GET("paket/detailItinerary")
    Call<ItteneraryResponse> getIttenerary(
            @Query("idPaket") String idPaket
    );

    @GET("WisataHalal/detailItinerary")
    Call<ItteneraryResponse> getItteneraryWisata(
            @Query("idWisataHalal") String idWisataHalal
    );

    @POST("user/gantiPassword")
    Call<BaseResponse> resetPassword(
            @Query("idUserRegister") String idUserRegister
    );

    @Multipart
    @POST("pendaftaran/pendaftaran_post")
    Call<BaseResponse> pendaftaran(
            @Part("idUserRegister") RequestBody idUserRegister,
            @Part("email") RequestBody email,
            @Part("nomorPaspor") RequestBody nomorPaspor,
            @Part("tempatDikeluarkan") RequestBody tempatDikeluarkan,
            @Part("tanggalPenerbitanPaspor") RequestBody tanggalPenerbitanPaspor,
            @Part("tanggalBerakhirPaspor") RequestBody tanggalBerakhirPaspor,
            @Part("tempatLahir") RequestBody tempatLahir,
            @Part("tanggalLahir") RequestBody tanggalLahir,
            @Part("jenisKelamin") RequestBody jenisKelamin,
            @Part("statusPerkawinan") RequestBody statusPerkawinan,
            @Part("kewarganegaraan") RequestBody kewarganegaraan,
            @Part("alamat") RequestBody alamat,
            @Part("kelurahan") RequestBody kelurahan,
            @Part("kecamatan") RequestBody kecamatan,
            @Part("kotaKabupaten") RequestBody kotaKabupaten,
            @Part("provinsi") RequestBody provinsi,
            @Part("kodePOS") RequestBody kodePOS,
            @Part("nomorHP") RequestBody nomorHP,
            @Part("pekerjaan") RequestBody pekerjaan,
            @Part("riwayatPenyakit") RequestBody riwayatPenyakit,
            @Part("namaLengkap") RequestBody namaLengkap,
            @Part MultipartBody.Part fileKTP,
            @Part MultipartBody.Part fileKK,
            @Part MultipartBody.Part filePaspor,
            @Part MultipartBody.Part fileBukuNikah,
            @Part MultipartBody.Part fileAkteKelahiran,
            @Part MultipartBody.Part ttdPendaftar,
            @Part MultipartBody.Part fcKTPAlmarhum,
            @Part MultipartBody.Part fcKKAlmarhum,
            @Part MultipartBody.Part fcFotoAlmarhum
    );
}

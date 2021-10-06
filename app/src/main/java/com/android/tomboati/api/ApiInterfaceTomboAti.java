package com.android.tomboati.api;

import com.android.tomboati.api.response.BaseResponse;
import com.android.tomboati.api.response.ChatResponse;
import com.android.tomboati.api.response.DetailPembayaranResponse;
import com.android.tomboati.api.response.ItteneraryResponse;
import com.android.tomboati.api.response.JadwalSholatResponse;
import com.android.tomboati.api.response.KomunitasResponse;
import com.android.tomboati.api.response.KataMutiaraResponse;
import com.android.tomboati.api.response.ListPaketVerifyRespone;
import com.android.tomboati.api.response.NewsResponse;
import com.android.tomboati.api.response.PaketMonthResponse;
import com.android.tomboati.api.response.PaketResponse;
import com.android.tomboati.api.response.PaketWisataResponse;
import com.android.tomboati.api.response.PembayaranResponse;
import com.android.tomboati.api.response.SignInResponse;

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

public interface ApiInterfaceTomboAti {

    // ACCOUNT API ===================
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

    @POST("user/gantiPassword")
    Call<BaseResponse> resetPassword(
            @Query("idUserRegister") String idUserRegister
    );











    // CHAT API ===================
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











    // PACKET API ===================
    @GET("paket/paket_get")
    Call<PaketResponse> getPaket(
            @Query("tipe") String tipe,
            @Query("bulan") String bulan
    );

    @GET("Paket/detailPaket")
    Call<PaketResponse> getDetailPaket(
            @Query("idPaket") String idPaket
    );

    @GET("paket/getPaketMonth")
    Call<PaketMonthResponse> getPaketMonth(
            @Query("tipe") String tipe
    );

    @GET("paket/detailItinerary")
    Call<ItteneraryResponse> getIttenerary(
            @Query("idPaket") String idPaket
    );

    @GET("paket/getpaketlimit")
    Call<PaketResponse> getPaketLimit();











    // PACKET HAJJ API  ===================
    @GET("paket/paketHaji")
    Call<PaketResponse> getPaketHaji(
            @Query("tipe") String tipe,
            @Query("bulan") String bulan
    );

    @GET("Paket/getPaketHajiMonth")
    Call<PaketMonthResponse> getPaketHajiMonth(
            @Query("tipe") String tipe
    );

    @Multipart
    @POST("pendaftaran/pendaftaranBadalHaji")
    Call<BaseResponse> pendaftaranBadalHaji(
            @Part("idUserRegister") RequestBody idUserRegister,
            @Part("idPaket") RequestBody idPaket,
            @Part MultipartBody.Part fcKTPAlmarhum,
            @Part MultipartBody.Part fcKKAlmarhum,
            @Part MultipartBody.Part fcFotoAlmarhum
    );

    @GET("transaksi/pesananUmrohHaji") // <= TRANSAKSI
    Call<ListPaketVerifyRespone> getPaketHajiUmrahVerif(
            @Query("idUserRegister") String idUserRegister
    );

    @GET("transaksi/riwayatUmrohHaji") // <= RIWAYAT
    Call<ListPaketVerifyRespone> getRiwayatPaketHajiUmrahVerif(
            @Query("idUserRegister") String idUserRegister
    );











    // PACKET HALAL TOURISM API ===================
    @GET("WisataHalal/wisatahalal_get")
    Call<PaketWisataResponse> getPaketWisata(
            @Query("tipe") String tipe,
            @Query("bulan") String bulan
    );

    @GET("WisataHalal/detailWisataHalal")
    Call<PaketWisataResponse> getDetailPaketWisata(
            @Query("idWisataHalal") String idWisataHalal
    );

    @GET("WisataHalal/getWisataHalalMonth")
    Call<PaketMonthResponse> getPaketWisataMonth(
            @Query("tipe") String tipe
    );

    @GET("WisataHalal/detailItinerary")
    Call<ItteneraryResponse> getItteneraryWisata(
            @Query("idWisataHalal") String idWisataHalal
    );

    @GET("WisataHalal/getwisatahalallimit")
    Call<PaketWisataResponse> getWisataHalalLimit();

    @GET("transaksi/pesananWisataHalal") // <= TRANSAKSI
    Call<ListPaketVerifyRespone> getPaketWisataHalalVerif(
            @Query("idUserRegister") String idUserRegister
    );

    @GET("transaksi/riwayatWisataHalal") // <= RIWAYAT
    Call<ListPaketVerifyRespone> getRiwayatPaketWisataHalalVerif(
            @Query("idUserRegister") String idUserRegister
    );











    // NEWS COMMUNITY API ===================
    @GET("Komunitas/komunitas_get")
    Call<KomunitasResponse> getKomunitas();

    @GET("News/getNewsLimit")
    Call<NewsResponse> getNews();

    @GET("KataMutiara/getKataMutiaraLimit")
    Call<KataMutiaraResponse> getKataMutiara();











    // PEMBAYARAN API ===================
    @Multipart
    @POST("pembayaran/pembayaran")
    Call<BaseResponse> postPembayaran(
        @Query("idTransaksi") String idTransaksi,
        @Part("jumlahPembayaran") RequestBody jumlahPembayaran,
        @Part("tanggalPembayaran") RequestBody tanggalPembayaran,
        @Part("deskripsi") RequestBody deskripsi,
        @Part MultipartBody.Part buktiPembayaran
    );

    @GET("pembayaran/getPembayaran")
    Call<PembayaranResponse> getPembayaran(
        @Query("idTransaksi") String idTransaksi
    );

    @GET("pembayaran/getDetailPembayaran")
    Call<DetailPembayaranResponse> getDetailPembayaran(
        @Query("idPembayaran") String idPembayaran
    );











    // REGISTER PACKET API  ===================
    @Multipart
    @POST("pendaftaran/pendaftaran_post")
    Call<BaseResponse> pendaftaran(
            @Part("idUserRegister") RequestBody idUserRegister,
            @Part("nomorKTP") RequestBody nomorKTP,
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
            @Part("idPaket") RequestBody idPaket,
            @Part("tanggalBerangkat") RequestBody tanggalBerangkat,
            @Part("sheet") RequestBody sheet,
            @Part("sheetHarga") RequestBody sheetHarga,
            @Part("waktu") RequestBody waktu,
            @Part("namaLengkapKeluarga") RequestBody namaLengkapKeluarga,
            @Part("alamatKeluarga") RequestBody alamatKeluarga,
            @Part("kelurahanKeluarga") RequestBody kelurahanKeluarga,
            @Part("kecamatanKeluarga") RequestBody kecamatanKeluarga,
            @Part("kotakabupatenKeluarga") RequestBody kotakabupatenKeluarga,
            @Part("provinsiKeluarga") RequestBody provinsiKeluarga,
            @Part("kodePOSKeluarga") RequestBody kodePOSKeluarga,
            @Part("nomorHPKeluarga") RequestBody nomorHPKeluarga
    );


    @Multipart
    @POST("pendaftaran/pendaftaranWisataHalal")
    Call<BaseResponse> pendaftaranWisataHalal(
            @Part("idUserRegister") RequestBody idUserRegister,
            @Part("nomorKTP") RequestBody nomorKTP,
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
            @Part("idWisataHalal") RequestBody idWisataHalal,
            @Part("tanggalBerangkat") RequestBody tanggalBerangkat,
            @Part("sheet") RequestBody sheet,
            @Part("sheetHarga") RequestBody sheetHarga,
            @Part("waktu") RequestBody waktu,
            @Part("namaLengkapKeluarga") RequestBody namaLengkapKeluarga,
            @Part("alamatKeluarga") RequestBody alamatKeluarga,
            @Part("kelurahanKeluarga") RequestBody kelurahanKeluarga,
            @Part("kecamatanKeluarga") RequestBody kecamatanKeluarga,
            @Part("kotakabupatenKeluarga") RequestBody kotakabupatenKeluarga,
            @Part("provinsiKeluarga") RequestBody provinsiKeluarga,
            @Part("kodePOSKeluarga") RequestBody kodePOSKeluarga,
            @Part("nomorHPKeluarga") RequestBody nomorHPKeluarga
    );


}

package com.tomboati.tour.api;

import com.tomboati.tour.helper.BaseUrl;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static ApiInterfaceTomboAti apiInterfaceTomboAti;
    private static ApiInterfaceTomboAtiMitra apiInterfaceTomboAtiMitra;
    private static ApiInterfaceJadwalSholat apiInterfaceJadwalSholat;
    private static ApiInterfaceMasjid apiInterfaceMasjid;
    private static ApiInterfaceAlQuranNew apiInterfaceAlQuranNew;
    private static ApiInterfaceTahlil apiInterfaceTahlil;
    private static ApiInterfaceLokasi apiInterfaceLokasi;

    private static OkHttpClient getOkHTTPClient() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .readTimeout(180, TimeUnit.SECONDS)
                .connectTimeout(180, TimeUnit.SECONDS)
                .writeTimeout(180, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    private static GsonConverterFactory getGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    public static ApiInterfaceTomboAti getClientTomboAti() {
        if (apiInterfaceTomboAti == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(getGsonConverterFactory())
                    .client(getOkHTTPClient())
                    .baseUrl(BaseUrl.baseURLTomboAti)
                    .build();
            apiInterfaceTomboAti = retrofit.create(ApiInterfaceTomboAti.class);
        }
        return apiInterfaceTomboAti;
    }

    public static ApiInterfaceTomboAtiMitra getClientTomboAtiMitra() {
        if (apiInterfaceTomboAtiMitra == null) {
            Retrofit retrofit= new Retrofit.Builder()
                    .addConverterFactory(getGsonConverterFactory())
                    .client(getOkHTTPClient())
                    .baseUrl(BaseUrl.baseURLTomboAtiMitra)
                    .build();
            apiInterfaceTomboAtiMitra = retrofit.create(ApiInterfaceTomboAtiMitra.class);
        }
        return apiInterfaceTomboAtiMitra;
    }

    public static ApiInterfaceJadwalSholat getClientJadwalSholat() {
        if (apiInterfaceJadwalSholat == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(getGsonConverterFactory())
                    .client(getOkHTTPClient())
                    .baseUrl(BaseUrl.baseURLJadwalSholat)
                    .build();
            apiInterfaceJadwalSholat = retrofit.create(ApiInterfaceJadwalSholat.class);
        }
        return apiInterfaceJadwalSholat;
    }

    public static ApiInterfaceMasjid getClientMasjid() {
        if (apiInterfaceMasjid == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(getGsonConverterFactory())
                    .client(getOkHTTPClient())
                    .baseUrl(BaseUrl.baseURLMasjid)
                    .build();
            apiInterfaceMasjid = retrofit.create(ApiInterfaceMasjid.class);
        }
        return apiInterfaceMasjid;
    }

    public static ApiInterfaceAlQuranNew getClientAlQuranNew() {
        if (apiInterfaceAlQuranNew == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(getGsonConverterFactory())
                    .client(getOkHTTPClient())
                    .baseUrl(BaseUrl.baseURLAlQuranNew)
                    .build();
            apiInterfaceAlQuranNew = retrofit.create(ApiInterfaceAlQuranNew.class);
        }
        return apiInterfaceAlQuranNew;
    }

    public static ApiInterfaceTahlil getClientDoaTahlil() {
        if (apiInterfaceTahlil == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(getGsonConverterFactory())
                    .client(getOkHTTPClient())
                    .baseUrl(BaseUrl.baseURLDoaTahlil)
                    .build();
            apiInterfaceTahlil = retrofit.create(ApiInterfaceTahlil.class);
        }
        return apiInterfaceTahlil;
    }

    public static ApiInterfaceLokasi getClientLokasi() {
        if (apiInterfaceLokasi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(getGsonConverterFactory())
                    .client(getOkHTTPClient())
                    .baseUrl(BaseUrl.baseURLLokasi)
                    .build();
            apiInterfaceLokasi = retrofit.create(ApiInterfaceLokasi.class);
        }
        return apiInterfaceLokasi;
    }
}

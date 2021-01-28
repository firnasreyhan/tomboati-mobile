package com.android.tomboati.api;

import com.android.tomboati.utils.Constant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static ApiInterfaceTomboAti apiInterfaceTomboAti;
    private static ApiInterfaceJadwalSholat apiInterfaceJadwalSholat;
    private static ApiInterfaceMasjid apiIinterfaceMasjid;

    public static ApiInterfaceTomboAti getClientTomboAti() {
        if (apiInterfaceTomboAti == null) {
            Retrofit retrofit;

            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .addInterceptor(httpLoggingInterceptor)
                    .build();

            Gson builder = new GsonBuilder().setLenient().create();

            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(builder))
                    .client(okHttpClient)
                    .baseUrl(Constant.baseURLTomboAti)
                    .build();

            apiInterfaceTomboAti = retrofit.create(ApiInterfaceTomboAti.class);
        }
        return apiInterfaceTomboAti;
    }

    public static ApiInterfaceJadwalSholat getClientJadwalSholat() {
        if (apiInterfaceJadwalSholat == null) {
            Retrofit retrofit;

            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .addInterceptor(httpLoggingInterceptor)
                    .build();

            Gson builder = new GsonBuilder().setLenient().create();

            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(builder))
                    .client(okHttpClient)
                    .baseUrl(Constant.baseURLJadwalSholat)
                    .build();

            apiInterfaceJadwalSholat = retrofit.create(ApiInterfaceJadwalSholat.class);
        }
        return apiInterfaceJadwalSholat;
    }

    public static ApiInterfaceMasjid getClientMasjid() {
        if (apiIinterfaceMasjid == null) {
            Retrofit retrofit;

            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .addInterceptor(httpLoggingInterceptor)
                    .build();

            Gson builder = new GsonBuilder().setLenient().create();

            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(builder))
                    .client(okHttpClient)
                    .baseUrl(Constant.baseURLMasjid)
                    .build();

            apiIinterfaceMasjid = retrofit.create(ApiInterfaceMasjid.class);
        }
        return apiIinterfaceMasjid;
    }
}

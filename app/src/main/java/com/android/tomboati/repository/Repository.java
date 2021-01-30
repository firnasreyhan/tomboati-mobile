package com.android.tomboati.repository;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.android.tomboati.api.ApiClient;
import com.android.tomboati.api.ApiInterfaceJadwalSholat;
import com.android.tomboati.api.ApiInterfaceMasjid;
import com.android.tomboati.api.ApiInterfaceTomboAti;
import com.android.tomboati.api.response.BaseResponse;
import com.android.tomboati.api.response.JadwalSholatResponse;
import com.android.tomboati.api.response.MasjidResponse;
import com.android.tomboati.api.response.SignInResponse;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {
    private ApiInterfaceTomboAti apiInterfaceTomboAti;
    private ApiInterfaceJadwalSholat apiInterfaceTomboAtiJadwalSholat;
    private ApiInterfaceMasjid apiInterfaceMasjid;

    public Repository() {
        this.apiInterfaceTomboAti = ApiClient.getClientTomboAti();
        this.apiInterfaceTomboAtiJadwalSholat = ApiClient.getClientJadwalSholat();
        this.apiInterfaceMasjid = ApiClient.getClientMasjid();
    }

    public MutableLiveData<SignInResponse> signIn(String email, String password) {
        MutableLiveData<SignInResponse> listMutableLiveData = new MutableLiveData<>();
        apiInterfaceTomboAti.signIn(
                email,
                password
        ).enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                if (response.code() == 200) {
                    listMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<SignInResponse> call, Throwable t) {
                listMutableLiveData.setValue(null);
                Log.e("signIn", t.getMessage());
            }
        });
        return listMutableLiveData;
    }

    public MutableLiveData<BaseResponse> signUp(RequestBody noKTP, RequestBody email, RequestBody password, RequestBody namaLengkap, RequestBody noHP, MultipartBody.Part fileKTP, MultipartBody.Part foto) {
        MutableLiveData<BaseResponse> baseResponseMutableLiveData = new MutableLiveData<>();
        apiInterfaceTomboAti.signUp(
                noKTP,
                email,
                password,
                namaLengkap,
                noHP,
                fileKTP,
                foto
        ).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.code() == 200) {
                    baseResponseMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                baseResponseMutableLiveData.setValue(null);
                Log.e("signUp", t.getMessage());
            }
        });
        return baseResponseMutableLiveData;
    }

    public MutableLiveData<JadwalSholatResponse> jadwalSholat(int year, int month, int day, double latitude, double longitude, int timezone) {
        MutableLiveData<JadwalSholatResponse> data = new MutableLiveData<>();
        apiInterfaceTomboAtiJadwalSholat.jadwalSholat(
                year,
                month,
                day,
                latitude,
                longitude,
                timezone
        ).enqueue(new Callback<JadwalSholatResponse>() {
            @Override
            public void onResponse(Call<JadwalSholatResponse> call, Response<JadwalSholatResponse> response) {
                if (response.code() == 200) {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<JadwalSholatResponse> call, Throwable t) {
                data.setValue(null);
                Log.e("signUp", t.getMessage());
            }
        });
        return data;
    }

    public MutableLiveData<List<MasjidResponse.Feature>> masjid(String query, String proximity, int limit, String accessToken) {
        MutableLiveData<List<MasjidResponse.Feature>> data = new MutableLiveData<>();
        apiInterfaceMasjid.getMasjidTerdekat(
                query,
                proximity,
                limit,
                accessToken
        ).enqueue(new Callback<MasjidResponse>() {
            @Override
            public void onResponse(Call<MasjidResponse> call, Response<MasjidResponse> response) {
                if (response.code() == 200) {
                    data.setValue(response.body().getFeatures());
                }
            }

            @Override
            public void onFailure(Call<MasjidResponse> call, Throwable t) {
                data.setValue(null);
                Log.e("signUp", t.getMessage());
            }
        });
        return data;
    }
}

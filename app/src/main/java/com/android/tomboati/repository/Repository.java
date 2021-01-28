package com.android.tomboati.repository;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.android.tomboati.api.ApiClient;
import com.android.tomboati.api.ApiInterface;
import com.android.tomboati.api.response.BaseResponse;
import com.android.tomboati.api.response.SignInResponse;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {
    private ApiInterface apiInterface;

    public Repository() {
        this.apiInterface = ApiClient.getClient();
    }

    public MutableLiveData<SignInResponse> signIn(String email, String password) {
        MutableLiveData<SignInResponse> listMutableLiveData = new MutableLiveData<>();
        apiInterface.signIn(
                email,
                password
        ).enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                if (response.code() == 200) {
                    if (!response.body().isError()) {
                        if (!response.body().getData().isEmpty()) {
                            listMutableLiveData.setValue(response.body());
                        }
                    }
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
        apiInterface.signUp(
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
}

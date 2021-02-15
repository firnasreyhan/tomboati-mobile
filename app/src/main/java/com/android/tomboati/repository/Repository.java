package com.android.tomboati.repository;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.android.tomboati.api.ApiClient;
import com.android.tomboati.api.ApiInterfaceAlQuran;
import com.android.tomboati.api.ApiInterfaceJadwalSholat;
import com.android.tomboati.api.ApiInterfaceMasjid;
import com.android.tomboati.api.ApiInterfaceTahlil;
import com.android.tomboati.api.ApiInterfaceTomboAti;
import com.android.tomboati.api.response.AyatResponse;
import com.android.tomboati.api.response.BaseResponse;
import com.android.tomboati.api.response.ChatResponse;
import com.android.tomboati.api.response.DoaHarianResponse;
import com.android.tomboati.api.response.JadwalSholatResponse;
import com.android.tomboati.api.response.MasjidResponse;
import com.android.tomboati.api.response.PaketMonthResponse;
import com.android.tomboati.api.response.PaketResponse;
import com.android.tomboati.api.response.SignInResponse;
import com.android.tomboati.api.response.SurahResponse;
import com.android.tomboati.api.response.TahlilResponse;

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
    private ApiInterfaceAlQuran apiInterfaceAlQuran;
    private ApiInterfaceTahlil apiInterfaceTahlil;

    public Repository() {
        this.apiInterfaceTomboAti = ApiClient.getClientTomboAti();
        this.apiInterfaceTomboAtiJadwalSholat = ApiClient.getClientJadwalSholat();
        this.apiInterfaceMasjid = ApiClient.getClientMasjid();
        this.apiInterfaceAlQuran = ApiClient.getClientAlQuran();
        this.apiInterfaceTahlil = ApiClient.getClientDoaTahlil();
    }

    public MutableLiveData<BaseResponse> signOut(String email) {
        MutableLiveData<BaseResponse> listMutableLiveData = new MutableLiveData<>();
        apiInterfaceTomboAti.signOut(
                email
        ).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.code() == 200) {
                    listMutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                listMutableLiveData.postValue(null);
                Log.e("signOut", t.getMessage());
            }
        });
        return listMutableLiveData;
    }

    public MutableLiveData<SignInResponse> signIn(String email, String password, String token) {
        MutableLiveData<SignInResponse> listMutableLiveData = new MutableLiveData<>();
        apiInterfaceTomboAti.signIn(
                email,
                password,
                token
        ).enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                if (response.code() == 200) {
                    listMutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<SignInResponse> call, Throwable t) {
                listMutableLiveData.postValue(null);
                Log.e("signIn", t.getMessage());
            }
        });
        return listMutableLiveData;
    }

    public MutableLiveData<BaseResponse> signUp(RequestBody noKTP, RequestBody email, RequestBody password, RequestBody namaLengkap, RequestBody noHP, RequestBody token, MultipartBody.Part fileKTP, MultipartBody.Part foto) {
        MutableLiveData<BaseResponse> baseResponseMutableLiveData = new MutableLiveData<>();
        apiInterfaceTomboAti.signUp(
                noKTP,
                email,
                password,
                namaLengkap,
                noHP,
                token,
                fileKTP,
                foto
        ).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.code() == 200) {
                    baseResponseMutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                baseResponseMutableLiveData.postValue(null);
                Log.e("signUp", t.getMessage());
            }
        });
        return baseResponseMutableLiveData;
    }

    public MutableLiveData<ChatResponse> getChat(String idChatRoom) {
        MutableLiveData<ChatResponse> mutableLiveData = new MutableLiveData<>();

        apiInterfaceTomboAti.getChat(
                idChatRoom
        ).enqueue(new Callback<ChatResponse>() {
            @Override
            public void onResponse(Call<ChatResponse> call, Response<ChatResponse> response) {
                if (response.code() == 200) {
                    mutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ChatResponse> call, Throwable t) {
                mutableLiveData.postValue(null);
                Log.e("signUp", t.getMessage());
            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<BaseResponse> sendChat(RequestBody message, RequestBody idChatRoom, MultipartBody.Part img) {
        MutableLiveData<BaseResponse> mutableLiveData = new MutableLiveData<>();

        apiInterfaceTomboAti.sendChat(
                message,
                idChatRoom,
                img
        ).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.code() == 200) {
                    mutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                mutableLiveData.postValue(null);
                Log.e("sendChat", t.getMessage());
            }
        });

        return mutableLiveData;
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
                    data.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<JadwalSholatResponse> call, Throwable t) {
                data.postValue(null);
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
                    data.postValue(response.body().getFeatures());
                }
            }

            @Override
            public void onFailure(Call<MasjidResponse> call, Throwable t) {
                data.postValue(null);
                Log.e("signUp", t.getMessage());
            }
        });
        return data;
    }

    public MutableLiveData<List<SurahResponse>> getSurah() {
        MutableLiveData<List<SurahResponse>> data = new MutableLiveData<>();
        apiInterfaceAlQuran.getSurah().enqueue(new Callback<List<SurahResponse>>() {
            @Override
            public void onResponse(Call<List<SurahResponse>> call, Response<List<SurahResponse>> response) {
                if (response.code() == 200) {
                    data.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<SurahResponse>> call, Throwable t) {
                data.postValue(null);
                Log.e("getSurah", t.getMessage());
            }
        });
        return data;
    }

    public MutableLiveData<AyatResponse> getAyat(String idSurah) {
        MutableLiveData<AyatResponse> data = new MutableLiveData<>();
        apiInterfaceAlQuran.getAyat(
                idSurah
        ).enqueue(new Callback<AyatResponse>() {
            @Override
            public void onResponse(Call<AyatResponse> call, Response<AyatResponse> response) {
                if (response.code() == 200) {
                    data.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<AyatResponse> call, Throwable t) {
                data.postValue(null);
                Log.e("getAyat", t.getMessage());
            }
        });
        return data;
    }

    public MutableLiveData<TahlilResponse> getDoaTahlil() {
        MutableLiveData<TahlilResponse> data = new MutableLiveData<>();
        apiInterfaceTahlil.getDoaTahlil().enqueue(new Callback<TahlilResponse>() {
            @Override
            public void onResponse(Call<TahlilResponse> call, Response<TahlilResponse> response) {
                if (response.code() == 200) {
                    data.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<TahlilResponse> call, Throwable t) {
                data.postValue(null);
                Log.e("getDoaTahlil", t.getMessage());
            }
        });
        return data;
    }

    public MutableLiveData<DoaHarianResponse> getDoaHarian() {
        MutableLiveData<DoaHarianResponse> data = new MutableLiveData<>();
        apiInterfaceTahlil.getDoaHarian().enqueue(new Callback<DoaHarianResponse>() {
            @Override
            public void onResponse(Call<DoaHarianResponse> call, Response<DoaHarianResponse> response) {
                if (response.code() == 200) {
                    data.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<DoaHarianResponse> call, Throwable t) {
                data.postValue(null);
                Log.e("getDoaHarian", t.getMessage());
            }
        });
        return data;
    }

    public MutableLiveData<PaketResponse> getPaket(String paket) {
        MutableLiveData<PaketResponse> data = new MutableLiveData<>();
        apiInterfaceTomboAti.getPaket(
                paket
        ).enqueue(new Callback<PaketResponse>() {
            @Override
            public void onResponse(Call<PaketResponse> call, Response<PaketResponse> response) {
                if (response.code() == 200) {
                    data.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<PaketResponse> call, Throwable t) {
                data.postValue(null);
                Log.e("getPaket", t.getMessage());
            }
        });
        return data;
    }

    public MutableLiveData<SignInResponse> updateProfile(String idUser, RequestBody noKTP, RequestBody email, RequestBody password, RequestBody namaLengkap, RequestBody noHP, MultipartBody.Part fileKTP, MultipartBody.Part foto) {
        MutableLiveData<SignInResponse> baseResponseMutableLiveData = new MutableLiveData<>();
        apiInterfaceTomboAti.updateProfile(
                idUser,
                noKTP,
                email,
                password,
                namaLengkap,
                noHP,
                fileKTP,
                foto
        ).enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                if (response.code() == 200) {
                    baseResponseMutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<SignInResponse> call, Throwable t) {
                baseResponseMutableLiveData.postValue(null);
                Log.e("updateProfile", t.getMessage());
            }
        });
        return baseResponseMutableLiveData;
    }

    public MutableLiveData<PaketMonthResponse> getPaketMonth(String paket) {
        MutableLiveData<PaketMonthResponse> data = new MutableLiveData<>();
        apiInterfaceTomboAti.getPaketMonth(
                paket
        ).enqueue(new Callback<PaketMonthResponse>() {
            @Override
            public void onResponse(Call<PaketMonthResponse> call, Response<PaketMonthResponse> response) {
                if (response.code() == 200) {
                    data.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<PaketMonthResponse> call, Throwable t) {
                data.postValue(null);
                Log.e("getPaketMonth", t.getMessage());
            }
        });
        return data;
    }
}

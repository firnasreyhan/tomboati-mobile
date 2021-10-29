package com.tomboati.tour.repository;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.tomboati.tour.api.ApiClient;
import com.tomboati.tour.api.ApiInterfaceAlQuranNew;
import com.tomboati.tour.api.ApiInterfaceJadwalSholat;
import com.tomboati.tour.api.ApiInterfaceLokasi;
import com.tomboati.tour.api.ApiInterfaceMasjid;
import com.tomboati.tour.api.ApiInterfaceTahlil;
import com.tomboati.tour.api.ApiInterfaceTomboAti;
import com.tomboati.tour.api.ApiInterfaceTomboAtiMitra;
import com.tomboati.tour.api.response.AkunMitraResponse;
import com.tomboati.tour.api.response.AkunResponse;
import com.tomboati.tour.api.response.BacaanSholatResponse;
import com.tomboati.tour.api.response.BaseResponse;
import com.tomboati.tour.api.response.ChatResponse;
import com.tomboati.tour.api.response.DetailPembayaranResponse;
import com.tomboati.tour.api.response.DoaHarianResponse;
import com.tomboati.tour.api.response.ItteneraryResponse;
import com.tomboati.tour.api.response.JadwalSholatResponse;
import com.tomboati.tour.api.response.KomunitasResponse;
import com.tomboati.tour.api.response.KataMutiaraResponse;
import com.tomboati.tour.api.response.ListPaketVerifyRespone;
import com.tomboati.tour.api.response.LokasiResponse;
import com.tomboati.tour.api.response.MasjidResponse;
import com.tomboati.tour.api.response.NewsResponse;
import com.tomboati.tour.api.response.PaketMonthResponse;
import com.tomboati.tour.api.response.PaketResponse;
import com.tomboati.tour.api.response.PaketWisataResponse;
import com.tomboati.tour.api.response.PembayaranResponse;
import com.tomboati.tour.api.response.PoinResponse;
import com.tomboati.tour.api.response.QuranListResponse;
import com.tomboati.tour.api.response.QuranSurahResponse;
import com.tomboati.tour.api.response.SignInResponse;
import com.tomboati.tour.api.response.TahlilResponse;
import com.tomboati.tour.api.response.UbahFotoProfileResponse;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {
    private ApiInterfaceTomboAti apiInterfaceTomboAti;
    private ApiInterfaceTomboAtiMitra apiInterfaceTomboAtiMitra;
    private ApiInterfaceJadwalSholat apiInterfaceTomboAtiJadwalSholat;
    private ApiInterfaceMasjid apiInterfaceMasjid;
    private ApiInterfaceAlQuranNew apiInterfaceAlQuranNew;
    private ApiInterfaceTahlil apiInterfaceTahlil;
    private ApiInterfaceLokasi apiInterfaceLokasi;

    private static final String TAG = "REPOSITORY";

    public Repository() {
        this.apiInterfaceTomboAti = ApiClient.getClientTomboAti();
        this.apiInterfaceTomboAtiMitra = ApiClient.getClientTomboAtiMitra();
        this.apiInterfaceTomboAtiJadwalSholat = ApiClient.getClientJadwalSholat();
        this.apiInterfaceMasjid = ApiClient.getClientMasjid();
        this.apiInterfaceAlQuranNew = ApiClient.getClientAlQuranNew();
        this.apiInterfaceTahlil = ApiClient.getClientDoaTahlil();
        this.apiInterfaceLokasi = ApiClient.getClientLokasi();
    }

    //==

    public MutableLiveData<BaseResponse> signOut(String email) {
        MutableLiveData<BaseResponse> data = new MutableLiveData<>();
        apiInterfaceTomboAti.signOut(email).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.code() == 200) { data.postValue(response.body()); }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                data.postValue(null);
            }
        }); return data;
    }

    //==

    public MutableLiveData<SignInResponse> signIn(String email, String password, String token) {
        MutableLiveData<SignInResponse> data = new MutableLiveData<>();
        apiInterfaceTomboAti.signIn(email, password, token).enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                if (response.code() == 200) { data.postValue(response.body()); }
            }

            @Override
            public void onFailure(Call<SignInResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                data.postValue(null);
            }
        }); return data;
    }

    //==

    public MutableLiveData<BaseResponse> signUp(
            RequestBody noKTP, RequestBody email, RequestBody password, RequestBody namaLengkap,
            RequestBody noHP, RequestBody token, MultipartBody.Part fileKTP, MultipartBody.Part foto
    ) {
        MutableLiveData<BaseResponse> data = new MutableLiveData<>();
        apiInterfaceTomboAti.signUp(
            noKTP, email, password, namaLengkap, noHP, token, fileKTP, foto
        ).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.code() == 200) { data.postValue(response.body()); }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) { Log.d(TAG, "onFailure: " + t.getMessage());
                data.postValue(null); }
        }); return data;
    }

    //==

    public MutableLiveData<ChatResponse> getChat(String idChatRoom) {
        MutableLiveData<ChatResponse> data = new MutableLiveData<>();
        apiInterfaceTomboAti.getChat(idChatRoom).enqueue(new Callback<ChatResponse>() {
            @Override
            public void onResponse(Call<ChatResponse> call, Response<ChatResponse> response) {
                if (response.code() == 200) { data.postValue(response.body()); }
            }

            @Override
            public void onFailure(Call<ChatResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                data.postValue(null);
            }
        });  return data;
    }

    //==

    public MutableLiveData<BaseResponse> sendChat(RequestBody message, RequestBody idChatRoom, MultipartBody.Part img) {
        MutableLiveData<BaseResponse> data = new MutableLiveData<>();
        apiInterfaceTomboAti.sendChat(message, idChatRoom, img).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.code() == 200) { data.postValue(response.body()); }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                data.postValue(null);
            }
        }); return data;
    }

    //==

    public MutableLiveData<JadwalSholatResponse> jadwalSholat(
            int year, int month, int day, double latitude, double longitude, int timezone
    ) {
        MutableLiveData<JadwalSholatResponse> data = new MutableLiveData<>();
        apiInterfaceTomboAtiJadwalSholat.jadwalSholat(
                year, month, day, latitude, longitude, timezone
        ).enqueue(new Callback<JadwalSholatResponse>() {
            @Override
            public void onResponse(Call<JadwalSholatResponse> call, Response<JadwalSholatResponse> response) {
                if (response.code() == 200) { data.postValue(response.body()); }
            }

            @Override
            public void onFailure(Call<JadwalSholatResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                data.postValue(null);
            }
        }); return data;
    }

    //==

    public MutableLiveData<List<MasjidResponse.Feature>> masjid(
            String query, String proximity, int limit, String accessToken
    ) {
        MutableLiveData<List<MasjidResponse.Feature>> data = new MutableLiveData<>();
        apiInterfaceMasjid.getMasjidTerdekat(query, proximity, limit, accessToken).enqueue(new Callback<MasjidResponse>() {
            @Override
            public void onResponse(Call<MasjidResponse> call, Response<MasjidResponse> response) {
                if (response.code() == 200) { data.postValue(response.body().getFeatures()); }
            }

            @Override
            public void onFailure(Call<MasjidResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                data.postValue(null);
            }
        }); return data;
    }

    //==

    public MutableLiveData<QuranListResponse> getListSurahNew() {
        MutableLiveData<QuranListResponse> data = new MutableLiveData<>();
        apiInterfaceAlQuranNew.getListSurah().enqueue(new Callback<QuranListResponse>() {
            @Override
            public void onResponse(Call<QuranListResponse> call, Response<QuranListResponse> response) {
                if (response.code() == 200) { data.postValue(response.body()); }
            }

            @Override
            public void onFailure(Call<QuranListResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                data.postValue(null);
            }
        }); return data;
    }

    //==

    public MutableLiveData<QuranSurahResponse> getAyatNew(String idSurah) {
        MutableLiveData<QuranSurahResponse> data = new MutableLiveData<>();
        apiInterfaceAlQuranNew.getSurah(idSurah).enqueue(new Callback<QuranSurahResponse>() {
            @Override
            public void onResponse(Call<QuranSurahResponse> call, Response<QuranSurahResponse> response) {
                if (response.code() == 200) { data.postValue(response.body()); }
            }

            @Override
            public void onFailure(Call<QuranSurahResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                data.postValue(null);
            }
        }); return data;
    }
    
    //==
    
    public MutableLiveData<TahlilResponse> getDoaTahlil() {
        MutableLiveData<TahlilResponse> data = new MutableLiveData<>();
        apiInterfaceTahlil.getDoaTahlil().enqueue(new Callback<TahlilResponse>() {
            @Override
            public void onResponse(Call<TahlilResponse> call, Response<TahlilResponse> response) {
                if (response.code() == 200) { data.postValue(response.body()); }
            }

            @Override
            public void onFailure(Call<TahlilResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                data.postValue(null); 
            }
        }); return data;
    }

    //==

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
                Log.d(TAG, "onFailure: " + t.getMessage());
                data.postValue(null);
                Log.e("getDoaHarian", t.getMessage());
            }
        });
        return data;
    }

    //==

    public MutableLiveData<PaketResponse> getPaket(String paket, String bulan) {
        MutableLiveData<PaketResponse> data = new MutableLiveData<>();
        apiInterfaceTomboAti.getPaket(paket, bulan).enqueue(new Callback<PaketResponse>() {
            @Override
            public void onResponse(Call<PaketResponse> call, Response<PaketResponse> response) {
                if (response.code() == 200) { data.postValue(response.body()); }
            }

            @Override
            public void onFailure(Call<PaketResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                data.postValue(null);
            }
        });  return data;
    }

    //==

    public MutableLiveData<PaketResponse> getPaketHaji( String paket, String bulan ) {
        MutableLiveData<PaketResponse> data = new MutableLiveData<>();
        apiInterfaceTomboAti.getPaketHaji( paket, bulan ).enqueue(new Callback<PaketResponse>() {
            @Override
            public void onResponse(Call<PaketResponse> call, Response<PaketResponse> response) {
                if (response.code() == 200) { data.postValue(response.body()); }
            }

            @Override
            public void onFailure(Call<PaketResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                data.postValue(null);
            }
        });  return data;
    }

    //==

    public MutableLiveData<PaketWisataResponse> getPaketWisata(String paket, String bulan) {
        MutableLiveData<PaketWisataResponse> data = new MutableLiveData<>();
        apiInterfaceTomboAti.getPaketWisata(paket, bulan).enqueue(new Callback<PaketWisataResponse>() {
            @Override
            public void onResponse(Call<PaketWisataResponse> call, Response<PaketWisataResponse> response) {
                if (response.code() == 200) { data.postValue(response.body()); }
            }

            @Override
            public void onFailure(Call<PaketWisataResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                data.postValue(null);
            }
        }); return data;
    }

    //==

    public MutableLiveData<PaketResponse> getDetailPaket(String idPaket) {
        MutableLiveData<PaketResponse> data = new MutableLiveData<>();
        apiInterfaceTomboAti.getDetailPaket(idPaket).enqueue(new Callback<PaketResponse>() {
            @Override
            public void onResponse(Call<PaketResponse> call, Response<PaketResponse> response) {
                if (response.code() == 200) { data.postValue(response.body()); }
            }

            @Override
            public void onFailure(Call<PaketResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                data.postValue(null);
            }
        }); return data;
    }

    //==

    public MutableLiveData<PaketWisataResponse> getDetailPaketWisata(String idPaketWisata) {
        MutableLiveData<PaketWisataResponse> data = new MutableLiveData<>();
        apiInterfaceTomboAti.getDetailPaketWisata(idPaketWisata).enqueue(new Callback<PaketWisataResponse>() {
            @Override
            public void onResponse(Call<PaketWisataResponse> call, Response<PaketWisataResponse> response) {
                if (response.code() == 200) { data.postValue(response.body()); }
            }

            @Override
            public void onFailure(Call<PaketWisataResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                data.postValue(null);
            }
        }); return data;
    }

    //==

    public MutableLiveData<PaketResponse> getPaketLimit() {
        MutableLiveData<PaketResponse> data = new MutableLiveData<>();
        apiInterfaceTomboAti.getPaketLimit().enqueue(new Callback<PaketResponse>() {
            @Override
            public void onResponse(Call<PaketResponse> call, Response<PaketResponse> response) {
                if (response.code() == 200) { data.postValue(response.body()); }
            }

            @Override
            public void onFailure(Call<PaketResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                data.postValue(null);
            }
        });  return data;
    }

    //==

    public MutableLiveData<PaketWisataResponse> getWisataHalalLimit() {
        MutableLiveData<PaketWisataResponse> data = new MutableLiveData<>();
        apiInterfaceTomboAti.getWisataHalalLimit().enqueue(new Callback<PaketWisataResponse>() {
            @Override
            public void onResponse(Call<PaketWisataResponse> call, Response<PaketWisataResponse> response) {
                if (response.code() == 200) { data.postValue(response.body()); }
            }

            @Override
            public void onFailure(Call<PaketWisataResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                data.postValue(null);
            }
        }); return data;
    }

    //==

    public MutableLiveData<BaseResponse> updateProfile(
            String idUser, RequestBody noKTP, RequestBody email, RequestBody namaLengkap, RequestBody noHP
    ) {
        MutableLiveData<BaseResponse> data = new MutableLiveData<>();
        apiInterfaceTomboAti.updateProfile(idUser, noKTP, email, namaLengkap, noHP).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.code() == 200) { data.postValue(response.body()); }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                data.postValue(null); 
            }
        }); return data;
    }

    //==

    public MutableLiveData<BaseResponse> updateFileKTP(String idUser, MultipartBody.Part fileKTP) {
        MutableLiveData<BaseResponse> data = new MutableLiveData<>();
        apiInterfaceTomboAti.updateFileKTP(idUser, fileKTP).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.code() == 200) { data.postValue(response.body()); }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                data.postValue(null); 
            }
        }); return data;
    }

    //==

    public MutableLiveData<BaseResponse> updateFoto(String idUser, MultipartBody.Part foto) {
        MutableLiveData<BaseResponse> data = new MutableLiveData<>();
        apiInterfaceTomboAti.updateFoto(idUser, foto).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.code() == 200) { data.postValue(response.body()); }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                data.postValue(null); 
            }
        }); return data;
    }

    //==

    public MutableLiveData<PaketMonthResponse> getPaketMonth(String tipe) {
        MutableLiveData<PaketMonthResponse> data = new MutableLiveData<>();
        apiInterfaceTomboAti.getPaketMonth(tipe).enqueue(new Callback<PaketMonthResponse>() {
            @Override
            public void onResponse(Call<PaketMonthResponse> call, Response<PaketMonthResponse> response) {
                if (response.code() == 200) { data.postValue(response.body()); }
            }

            @Override
            public void onFailure(Call<PaketMonthResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                data.postValue(null);
            }
        });
        return data;
    }

    //==

    public MutableLiveData<PaketMonthResponse> getPaketHajiMonth(String tipe) {
        MutableLiveData<PaketMonthResponse> data = new MutableLiveData<>();
        apiInterfaceTomboAti.getPaketHajiMonth(tipe).enqueue(new Callback<PaketMonthResponse>() {
            @Override
            public void onResponse(Call<PaketMonthResponse> call, Response<PaketMonthResponse> response) {
                if (response.code() == 200) { data.postValue(response.body()); }
            }

            @Override
            public void onFailure(Call<PaketMonthResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                data.postValue(null);
            }
        });
        return data;
    }

    //==

    public MutableLiveData<PaketMonthResponse> getPaketWisataMonth(String paket) {
        MutableLiveData<PaketMonthResponse> data = new MutableLiveData<>();
        apiInterfaceTomboAti.getPaketWisataMonth(paket).enqueue(new Callback<PaketMonthResponse>() {
            @Override
            public void onResponse(Call<PaketMonthResponse> call, Response<PaketMonthResponse> response) {
                if (response.code() == 200) { data.postValue(response.body()); }
            }

            @Override
            public void onFailure(Call<PaketMonthResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                data.postValue(null);
            }
        }); return data;
    }

    //==

    public MutableLiveData<List<BacaanSholatResponse>> getBacaanSholat() {
        MutableLiveData<List<BacaanSholatResponse>> data = new MutableLiveData<>();
        apiInterfaceTahlil.getBacaanSholat().enqueue(new Callback<List<BacaanSholatResponse>>() {
            @Override
            public void onResponse(Call<List<BacaanSholatResponse>> call, Response<List<BacaanSholatResponse>> response) {
                if (response.code() == 200) { data.postValue(response.body()); }
            }

            @Override
            public void onFailure(Call<List<BacaanSholatResponse>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                data.postValue(null);
            }
        }); return data;
    }

    //==

    public MutableLiveData<List<BacaanSholatResponse>> getNiatSholat() {
        MutableLiveData<List<BacaanSholatResponse>> data = new MutableLiveData<>();
        apiInterfaceTahlil.getNiatSholat().enqueue(new Callback<List<BacaanSholatResponse>>() {
            @Override
            public void onResponse(Call<List<BacaanSholatResponse>> call, Response<List<BacaanSholatResponse>> response) {
                if (response.code() == 200) { data.postValue(response.body()); }
            }

            @Override
            public void onFailure(Call<List<BacaanSholatResponse>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                data.postValue(null);
            }
        }); return data;
    }

    //==

    public MutableLiveData<List<KomunitasResponse.Datum>> getKomunitas() {
        MutableLiveData<List<KomunitasResponse.Datum>> data = new MutableLiveData<>();
        apiInterfaceTomboAti.getKomunitas().enqueue(new Callback<KomunitasResponse>() {
            @Override
            public void onResponse(Call<KomunitasResponse> call, Response<KomunitasResponse> response) {
                if (response.code() == 200) { data.postValue(response.body().getData()); }
            }

            @Override
            public void onFailure(Call<KomunitasResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                data.postValue(null);
            }
        }); return data;
    }

    //==

    public MutableLiveData<NewsResponse> getNews() {
        MutableLiveData<NewsResponse> data = new MutableLiveData<>();
        apiInterfaceTomboAti.getNews().enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (response.code() == 200) { data.postValue(response.body()); }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                data.postValue(null);
            }
        }); return data;
    }

    //==

    public MutableLiveData<KataMutiaraResponse> getKataMutiara() {
        MutableLiveData<KataMutiaraResponse> data = new MutableLiveData<>();
        apiInterfaceTomboAti.getKataMutiara().enqueue(new Callback<KataMutiaraResponse>() {
            @Override
            public void onResponse(Call<KataMutiaraResponse> call, Response<KataMutiaraResponse> response) {
                if (response.code() == 200) { data.postValue(response.body()); }
            }

            @Override
            public void onFailure(Call<KataMutiaraResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                data.postValue(null);
            }
        }); return data;
    }

    //==

    public MutableLiveData<ItteneraryResponse> getIttenerary(String idPaket) {
        MutableLiveData<ItteneraryResponse> data = new MutableLiveData<>();
        apiInterfaceTomboAti.getIttenerary(idPaket).enqueue(new Callback<ItteneraryResponse>() {
            @Override
            public void onResponse(Call<ItteneraryResponse> call, Response<ItteneraryResponse> response) {
                if (response.code() == 200) { data.postValue(response.body()); }
            }

            @Override
            public void onFailure(Call<ItteneraryResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                data.postValue(null);
            }
        }); return data;
    }

    //==

    public MutableLiveData<ItteneraryResponse> getItteneraryWisata(String idWisataHalal) {
        MutableLiveData<ItteneraryResponse> data = new MutableLiveData<>();
        apiInterfaceTomboAti.getItteneraryWisata(idWisataHalal).enqueue(new Callback<ItteneraryResponse>() {
            @Override
            public void onResponse(Call<ItteneraryResponse> call, Response<ItteneraryResponse> response) {
                if (response.code() == 200) { data.postValue(response.body());}
            }

            @Override
            public void onFailure(Call<ItteneraryResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                data.postValue(null);
            }
        }); return data;
    }

    //==

    public MutableLiveData<BaseResponse> resetPassword(String idUserRegister) {
        MutableLiveData<BaseResponse> data = new MutableLiveData<>();
        apiInterfaceTomboAti.resetPassword(idUserRegister).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.code() == 200) { data.postValue(response.body()); }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                data.postValue(null);
            }
        }); return data;
    }

    //==

    public MutableLiveData<BaseResponse> pendaftaranBadalHaji(
        RequestBody idUserRegister,
        RequestBody idPaket,
        MultipartBody.Part fcKTPAlmarhum,
        MultipartBody.Part fcKKAlmarhum,
        MultipartBody.Part fcFotoAlmarhum
    ){
        MutableLiveData<BaseResponse> data = new MutableLiveData<>();
        apiInterfaceTomboAti.pendaftaranBadalHaji(
            idUserRegister, idPaket, fcKTPAlmarhum, fcKKAlmarhum, fcFotoAlmarhum
        ).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.code() == 200) { data.postValue(response.body()); }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t)  {
                Log.d(TAG, "onFailure: " + t.getMessage());
                data.postValue(null);
            }
        }); return data;
    }

    //==

    public MutableLiveData<BaseResponse> pendaftaran(
        RequestBody idUserRegister,
        RequestBody nomorKTP,
        RequestBody email,
        RequestBody nomorPaspor,
        RequestBody tempatDikeluarkan,
        RequestBody tanggalPenerbitanPaspor,
        RequestBody tanggalBerakhirPaspor,
        RequestBody tempatLahir,
        RequestBody tanggalLahir,
        RequestBody jenisKelamin,
        RequestBody statusPerkawinan,
        RequestBody kewarganegaraan,
        RequestBody alamat,
        RequestBody kelurahan,
        RequestBody kecamatan,
        RequestBody kotaKabupaten,
        RequestBody provinsi,
        RequestBody kodePOS,
        RequestBody nomorHP,
        RequestBody pekerjaan,
        RequestBody riwayatPenyakit,
        RequestBody namaLengkap,
        MultipartBody.Part fileKTP,
        MultipartBody.Part fileKK,
        MultipartBody.Part filePaspor,
        MultipartBody.Part fileBukuNikah,
        MultipartBody.Part fileAkteKelahiran,
        MultipartBody.Part ttdPendaftar,
        RequestBody idPaket,
        RequestBody tanggalBerangkat,
        RequestBody sheet,
        RequestBody sheetHarga,
        RequestBody waktu,
        RequestBody namaLengkapKeluarga,
        RequestBody alamatKeluarga,
        RequestBody kelurahanKeluarga,
        RequestBody kecamatanKeluarga,
        RequestBody kotakabupatenKeluarga,
        RequestBody provinsiKeluarga,
        RequestBody kodePOSKeluarga,
        RequestBody nomorHPKeluarga
    ) {
        MutableLiveData<BaseResponse> data = new MutableLiveData<>();
        apiInterfaceTomboAti.pendaftaran(
                idUserRegister,
                nomorKTP,
                email,
                nomorPaspor,
                tempatDikeluarkan,
                tanggalPenerbitanPaspor,
                tanggalBerakhirPaspor,
                tempatLahir,
                tanggalLahir,
                jenisKelamin,
                statusPerkawinan,
                kewarganegaraan,
                alamat,
                kelurahan,
                kecamatan,
                kotaKabupaten,
                provinsi,
                kodePOS,
                nomorHP,
                pekerjaan,
                riwayatPenyakit,
                namaLengkap,
                fileKTP,
                fileKK,
                filePaspor,
                fileBukuNikah,
                fileAkteKelahiran,
                ttdPendaftar,
                idPaket,
                tanggalBerangkat,
                sheet,
                sheetHarga,
                waktu,
                namaLengkapKeluarga,
                alamatKeluarga,
                kelurahanKeluarga,
                kecamatanKeluarga,
                kotakabupatenKeluarga,
                provinsiKeluarga,
                kodePOSKeluarga,
                nomorHPKeluarga
        ).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.code() == 200) { data.postValue(response.body()); }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Log.d("FAILURE!", "onFailure: " + t.getMessage());
                Log.d(TAG, "onFailure: " + t.getMessage());
                data.postValue(null);
            }
        }); return data;
    }

    //==

    public MutableLiveData<BaseResponse> pendaftaranWisataHalal(
        RequestBody idUserRegister,
        RequestBody nomorKTP,
        RequestBody email,
        RequestBody nomorPaspor,
        RequestBody tempatDikeluarkan,
        RequestBody tanggalPenerbitanPaspor,
        RequestBody tanggalBerakhirPaspor,
        RequestBody tempatLahir,
        RequestBody tanggalLahir,
        RequestBody jenisKelamin,
        RequestBody statusPerkawinan,
        RequestBody kewarganegaraan,
        RequestBody alamat,
        RequestBody kelurahan,
        RequestBody kecamatan,
        RequestBody kotaKabupaten,
        RequestBody provinsi,
        RequestBody kodePOS,
        RequestBody nomorHP,
        RequestBody pekerjaan,
        RequestBody riwayatPenyakit,
        RequestBody namaLengkap,
        MultipartBody.Part fileKTP,
        MultipartBody.Part fileKK,
        MultipartBody.Part filePaspor,
        MultipartBody.Part fileBukuNikah,
        MultipartBody.Part fileAkteKelahiran,
        MultipartBody.Part ttdPendaftar,
        RequestBody idWisataHalal,
        RequestBody tanggalBerangkat,
        RequestBody sheet,
        RequestBody sheetHarga,
        RequestBody waktu,
        RequestBody namaLengkapKeluarga,
        RequestBody alamatKeluarga,
        RequestBody kelurahanKeluarga,
        RequestBody kecamatanKeluarga,
        RequestBody kotakabupatenKeluarga,
        RequestBody provinsiKeluarga,
        RequestBody kodePOSKeluarga,
        RequestBody nomorHPKeluarga
    ) {
        MutableLiveData<BaseResponse> data = new MutableLiveData<>();
        apiInterfaceTomboAti.pendaftaranWisataHalal(
                idUserRegister,
                nomorKTP,
                email,
                nomorPaspor,
                tempatDikeluarkan,
                tanggalPenerbitanPaspor,
                tanggalBerakhirPaspor,
                tempatLahir,
                tanggalLahir,
                jenisKelamin,
                statusPerkawinan,
                kewarganegaraan,
                alamat,
                kelurahan,
                kecamatan,
                kotaKabupaten,
                provinsi,
                kodePOS,
                nomorHP,
                pekerjaan,
                riwayatPenyakit,
                namaLengkap,
                fileKTP,
                fileKK,
                filePaspor,
                fileBukuNikah,
                fileAkteKelahiran,
                ttdPendaftar,
                idWisataHalal,
                tanggalBerangkat,
                sheet,
                sheetHarga,
                waktu,
                namaLengkapKeluarga,
                alamatKeluarga,
                kelurahanKeluarga,
                kecamatanKeluarga,
                kotakabupatenKeluarga,
                provinsiKeluarga,
                kodePOSKeluarga,
                nomorHPKeluarga
        ).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.code() == 200) { data.postValue(response.body()); }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                data.postValue(null);
            }
        }); return data;
    }

    //==

    public MutableLiveData<List<LokasiResponse>> getPropinsi() {
        MutableLiveData<List<LokasiResponse>> data = new MutableLiveData<>();
        apiInterfaceLokasi.getPropinsi().enqueue(new Callback<List<LokasiResponse>>() {
            @Override
            public void onResponse(Call<List<LokasiResponse>> call, Response<List<LokasiResponse>> response) {
                if (response.code() == 200) { data.postValue(response.body()); }
            }

            @Override
            public void onFailure(Call<List<LokasiResponse>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                data.postValue(null);
            }
        }); return data;
    }

    //==

    public MutableLiveData<List<LokasiResponse>> getKabupaten(String id) {
        MutableLiveData<List<LokasiResponse>> data = new MutableLiveData<>();
        apiInterfaceLokasi.getKabupaten(id).enqueue(new Callback<List<LokasiResponse>>() {
            @Override
            public void onResponse(Call<List<LokasiResponse>> call, Response<List<LokasiResponse>> response) {
                if (response.code() == 200) { data.postValue(response.body()); }
            }

            @Override
            public void onFailure(Call<List<LokasiResponse>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                data.postValue(null);
            }
        }); return data;
    }

    //==

    public MutableLiveData<List<LokasiResponse>> getKecamatan(String id) {
        MutableLiveData<List<LokasiResponse>> data = new MutableLiveData<>();
        apiInterfaceLokasi.getKecamatan(id).enqueue(new Callback<List<LokasiResponse>>() {
            @Override
            public void onResponse(Call<List<LokasiResponse>> call, Response<List<LokasiResponse>> response) {
                if (response.code() == 200) { data.postValue(response.body()); }
            }

            @Override
            public void onFailure(Call<List<LokasiResponse>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                data.postValue(null);
            }
        }); return data;
    }

    //==

    public MutableLiveData<List<LokasiResponse>> getKelurahan(String id) {
        MutableLiveData<List<LokasiResponse>> data = new MutableLiveData<>();
        apiInterfaceLokasi.getKelurahan(id).enqueue(new Callback<List<LokasiResponse>>() {
            @Override
            public void onResponse(Call<List<LokasiResponse>> call, Response<List<LokasiResponse>> response) {
                if (response.code() == 200) { data.postValue(response.body()); }
            }

            @Override
            public void onFailure(Call<List<LokasiResponse>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                data.postValue(null);
            }
        }); return data;
    }

    //==

    public MutableLiveData<ListPaketVerifyRespone> getPaketWisataHalalVerif(String id) {
        MutableLiveData<ListPaketVerifyRespone> data = new MutableLiveData<>();
        apiInterfaceTomboAti.getPaketWisataHalalVerif(id).enqueue(new Callback<ListPaketVerifyRespone>() {
            @Override
            public void onResponse(Call<ListPaketVerifyRespone> call, Response<ListPaketVerifyRespone> response) {
                if (response.code() == 200) { data.postValue(response.body()); }
            }

            @Override
            public void onFailure(Call<ListPaketVerifyRespone> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                data.postValue(null);
            }
        }); return data;
    }

    //==

    public MutableLiveData<ListPaketVerifyRespone> getPaketHajiUmrahVerif(String id) {
        MutableLiveData<ListPaketVerifyRespone> data = new MutableLiveData<>();
        apiInterfaceTomboAti.getPaketHajiUmrahVerif(id).enqueue(new Callback<ListPaketVerifyRespone>() {
            @Override
            public void onResponse(Call<ListPaketVerifyRespone> call, Response<ListPaketVerifyRespone> response) {
                if (response.code() == 200) { data.postValue(response.body()); }
            }

            @Override
            public void onFailure(Call<ListPaketVerifyRespone> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                data.postValue(null);
            }
        }); return data;
    }

    //==

    public MutableLiveData<ListPaketVerifyRespone> getRiwayatPaketWisataHalalVerif(String id) {
        MutableLiveData<ListPaketVerifyRespone> data = new MutableLiveData<>();
        apiInterfaceTomboAti.getRiwayatPaketWisataHalalVerif(id).enqueue(new Callback<ListPaketVerifyRespone>() {
            @Override
            public void onResponse(Call<ListPaketVerifyRespone> call, Response<ListPaketVerifyRespone> response) {
                if (response.code() == 200) { data.postValue(response.body()); }
            }

            @Override
            public void onFailure(Call<ListPaketVerifyRespone> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                data.postValue(null);
            }
        }); return data;
    }

    //==

    public MutableLiveData<ListPaketVerifyRespone> getRiwayatPaketHajiUmrahVerif(String id) {
        MutableLiveData<ListPaketVerifyRespone> data = new MutableLiveData<>();
        apiInterfaceTomboAti.getRiwayatPaketHajiUmrahVerif(id).enqueue(new Callback<ListPaketVerifyRespone>() {
            @Override
            public void onResponse(Call<ListPaketVerifyRespone> call, Response<ListPaketVerifyRespone> response) {
                if (response.code() == 200) { data.postValue(response.body()); }
            }

            @Override
            public void onFailure(Call<ListPaketVerifyRespone> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                data.postValue(null);
            }
        }); return data;
    }

    //==

    public MutableLiveData<BaseResponse> postPembayaran(
        String idTransaksi,
        RequestBody jumlahPembayaran,
        RequestBody tanggalPembayaran,
        RequestBody deskripsi,
        MultipartBody.Part buktiPembayaran
    ) {
        MutableLiveData<BaseResponse> data = new MutableLiveData<>();
        apiInterfaceTomboAti.postPembayaran(idTransaksi, jumlahPembayaran, tanggalPembayaran, deskripsi, buktiPembayaran).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.code() == 200) { data.postValue(response.body()); }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                data.postValue(null);
            }
        }); return data;
    }

    //==

    public MutableLiveData<PembayaranResponse> getPembayaran(String id) {
        MutableLiveData<PembayaranResponse> data = new MutableLiveData<>();
        apiInterfaceTomboAti.getPembayaran(id).enqueue(new Callback<PembayaranResponse>() {
            @Override
            public void onResponse(Call<PembayaranResponse> call, Response<PembayaranResponse> response) {
                if (response.code() == 200) { data.postValue(response.body()); }
            }

            @Override
            public void onFailure(Call<PembayaranResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                data.postValue(null);
            }
        }); return data;
    }

    //==

    public MutableLiveData<DetailPembayaranResponse> getDetailPembayaran(String idPembayaran) {
        MutableLiveData<DetailPembayaranResponse> data = new MutableLiveData<>();
        apiInterfaceTomboAti.getDetailPembayaran(idPembayaran).enqueue(new Callback<DetailPembayaranResponse>() {
            @Override
            public void onResponse(Call<DetailPembayaranResponse> call, Response<DetailPembayaranResponse> response) {
                if (response.code() == 200) { data.postValue(response.body()); }
            }

            @Override
            public void onFailure(Call<DetailPembayaranResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                data.postValue(null);
            }
        }); return data;
    }

    //==

    public MutableLiveData<AkunResponse> registerAkun(
            String function,
            String nomorHP,
            String referral,
            String token
    ) {
        MutableLiveData<AkunResponse> data = new MutableLiveData<>();
        apiInterfaceTomboAtiMitra.registerAkun(
                function, nomorHP, referral, token
        ).enqueue(new Callback<AkunResponse>() {
            @Override
            public void onResponse(Call<AkunResponse> call, Response<AkunResponse> response) {
                if (response.code() == 200) { data.postValue(response.body()); }
            }

            @Override
            public void onFailure(Call<AkunResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                data.postValue(null);
            }
        }); return data;
    }

    //==

    public MutableLiveData<BaseResponse> registerMitra(
            String function,
            String idUserRegister,
            RequestBody nomorKTP,
            RequestBody email,
            RequestBody username,
            RequestBody bank,
            RequestBody nomorRekening,
            RequestBody atasNama,
            RequestBody cabang,
            MultipartBody.Part fotoKtp,
            MultipartBody.Part fotoProfil,
            MultipartBody.Part buktiBayar
    ) {
        MutableLiveData<BaseResponse> data = new MutableLiveData<>();
        apiInterfaceTomboAtiMitra.registerMitra(
                function,
                idUserRegister,
                nomorKTP,
                email,
                username,
                bank,
                nomorRekening,
                atasNama,
                cabang,
                fotoKtp,
                fotoProfil,
                buktiBayar
        ).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.code() == 200) { data.postValue(response.body()); }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                data.postValue(null);
            }
        }); return data;
    }

    //==

    public MutableLiveData<BaseResponse> registerDataDiri(
            String function,
            String idUserRegister,
            String nama,
            String provinsi,
            String kota,
            String kecamatan,
            String address,
            String kodePos,
            String country
    ) {
        MutableLiveData<BaseResponse> data = new MutableLiveData<>();
        apiInterfaceTomboAtiMitra.registerDataDiri(
                function,
                idUserRegister,
                nama,
                provinsi,
                kota,
                kecamatan,
                address,
                kodePos,
                country
        ).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.code() == 200) { data.postValue(response.body()); }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                data.postValue(null);
            }
        }); return data;
    }

    //==

    public MutableLiveData<AkunMitraResponse> loginMitra(
            String function,
            String username,
            String password,
            String token
    ) {
        MutableLiveData<AkunMitraResponse> data = new MutableLiveData<>();
        apiInterfaceTomboAtiMitra.loginMitra(
                function,
                username,
                password,
                token
        ).enqueue(new Callback<AkunMitraResponse>() {
            @Override
            public void onResponse(Call<AkunMitraResponse> call, Response<AkunMitraResponse> response) {
                if(response.code() == 200) {
                    data.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<AkunMitraResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                data.postValue(null);
            }
        }); return data;
    }

    //==

    public MutableLiveData<BaseResponse> gantiPassword(
            String function,
            String idUserRegister,
            String oldPassword,
            String newPassword,
            String repeatPassword
    ) {
        MutableLiveData<BaseResponse> data = new MutableLiveData<>();
        apiInterfaceTomboAtiMitra.gantiPassword(
                function,
                idUserRegister,
                oldPassword,
                newPassword,
                repeatPassword
        ).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if(response.code() == 200) {
                    data.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                data.postValue(null);
            }
        }); return data;
    }

    //==

    public MutableLiveData<BaseResponse> logout(
            String function,
            String username
    ) {
        MutableLiveData<BaseResponse> data = new MutableLiveData<>();
        apiInterfaceTomboAtiMitra.logout(
                function,
                username
        ).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if(response.code() == 200) {
                    data.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                data.postValue(null);
            }
        }); return data;
    }

    //==

    public MutableLiveData<UbahFotoProfileResponse> gantiFotoProfil(
            String function,
            String idUserRegister,
            MultipartBody.Part fotoProfil
    ) {
        MutableLiveData<UbahFotoProfileResponse> data = new MutableLiveData<>();
        apiInterfaceTomboAtiMitra.gantiFotoProfil(
                function,
                idUserRegister,
                fotoProfil
        ).enqueue(new Callback<UbahFotoProfileResponse>() {
            @Override
            public void onResponse(Call<UbahFotoProfileResponse> call, Response<UbahFotoProfileResponse> response) {
                if(response.code() == 200) {
                    data.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<UbahFotoProfileResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                data.postValue(null);
            }
        }); return data;
    }

    //==

    public MutableLiveData<PoinResponse> getPoin(
            String function,
            String userId
    ) {
        MutableLiveData<PoinResponse> data = new MutableLiveData<>();
        apiInterfaceTomboAtiMitra.getPoin(
                function,
                userId
        ).enqueue(new Callback<PoinResponse>() {
            @Override
            public void onResponse(Call<PoinResponse> call, Response<PoinResponse> response) {
                if(response.code() == 200) {
                    data.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<PoinResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                data.postValue(null);
            }
        }); return data;
    }

    //==

    public MutableLiveData<BaseResponse> gantiDataProfil(
            String function,
            String idUserRegister,
            String nama,
            String provinsi,
            String kota,
            String kecamatan,
            String address,
            String kodePos,
            String country,
            String email
    ) {
        MutableLiveData<BaseResponse> data = new MutableLiveData<>();
        apiInterfaceTomboAtiMitra.gantiDataProfil(
                function,
                idUserRegister,
                nama,
                provinsi,
                kota,
                kecamatan,
                address,
                kodePos,
                country,
                email
        ).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.code() == 200) { data.postValue(response.body()); }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                data.postValue(null);
            }
        }); return data;
    }


}

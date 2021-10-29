package com.tomboati.tour.viewmodel.tomboati.homepage;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.tomboati.tour.api.response.JadwalSholatResponse;
import com.tomboati.tour.api.response.KataMutiaraResponse;
import com.tomboati.tour.api.response.NewsResponse;
import com.tomboati.tour.api.response.PaketResponse;
import com.tomboati.tour.api.response.PaketWisataResponse;
import com.tomboati.tour.repository.Repository;

public class BerandaViewModel extends AndroidViewModel {
    private Repository repository;

    private MutableLiveData<JadwalSholatResponse> jadwalSholatData;
    private MutableLiveData<JadwalSholatResponse> jadwalSholatData1;
    private MutableLiveData<JadwalSholatResponse> jadwalSholatData2;
    private MutableLiveData<PaketResponse> getPaketData;
    private MutableLiveData<PaketWisataResponse> getWisataHalalData;
    private MutableLiveData<NewsResponse> getNewsData;
    private MutableLiveData<KataMutiaraResponse> getKataMutiaraData;

    public BerandaViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
    }

    public void clearDataLocation() {
        jadwalSholatData = null;
        jadwalSholatData1 = null;
        jadwalSholatData2 = null;
    }

    public MutableLiveData<JadwalSholatResponse> jadwalSholat(int year, int month, int day, double latitude, double longitude, int timezone) {
        if(jadwalSholatData == null) {
            jadwalSholatData = repository.jadwalSholat( year, (month + 1), day, latitude, longitude, timezone);
        }
        return jadwalSholatData;
    }

    public MutableLiveData<JadwalSholatResponse> jadwalSholat1(int year, int month, int day, double latitude, double longitude, int timezone) {
        if(jadwalSholatData1 == null) {
            jadwalSholatData1 = repository.jadwalSholat( year, (month + 1), day, latitude, longitude, timezone);
        }
        return jadwalSholatData1;
    }

    public MutableLiveData<JadwalSholatResponse> jadwalSholat2(int year, int month, int day, double latitude, double longitude, int timezone) {
        if(jadwalSholatData2 == null) {
            jadwalSholatData2 = repository.jadwalSholat( year, (month + 1), day, latitude, longitude, timezone);
        }
        return jadwalSholatData2;
    }

    public MutableLiveData<PaketResponse> getPaket() {
        if(getPaketData == null) {
            getPaketData = repository.getPaketLimit();
        }
        return getPaketData;
    }

    public MutableLiveData<PaketWisataResponse> getWisataHalal() {
        if(getWisataHalalData == null) {
            getWisataHalalData = repository.getWisataHalalLimit();
        }
        return getWisataHalalData;
    }

    public MutableLiveData<NewsResponse> getNews() {
        if(getNewsData == null) {
            getNewsData = repository.getNews();
        }
        return getNewsData;
    }

    public MutableLiveData<KataMutiaraResponse> getKataMutiara() {
        if(getKataMutiaraData == null) {
            getKataMutiaraData = repository.getKataMutiara();
        }
        return getKataMutiaraData;
    }
}

package com.android.tomboati.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.android.tomboati.api.response.ItteneraryResponse;
import com.android.tomboati.api.response.PaketResponse;
import com.android.tomboati.api.response.PaketWisataResponse;
import com.android.tomboati.repository.Repository;

public class DetailPaketViewModel extends AndroidViewModel {
    private Repository repository;

    public DetailPaketViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
    }

    public MutableLiveData<PaketResponse> getPaket(String idPaket) {
        return repository.getDetailPaket(idPaket);
    }

    public MutableLiveData<ItteneraryResponse> getIttenerary(String idPaket) {
        return repository.getIttenerary(idPaket);
    }

    public MutableLiveData<PaketWisataResponse> getPaketWisata(String idPaketWisata) {
        return repository.getDetailPaketWisata(idPaketWisata);
    }

    public MutableLiveData<ItteneraryResponse> getItteneraryWisata(String idPaketWisata) {
        return repository.getItteneraryWisata(idPaketWisata);
    }
}

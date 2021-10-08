package com.tomboati.tour.viewmodel.tomboati.pendaftaran;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.tomboati.tour.api.response.ItteneraryResponse;
import com.tomboati.tour.api.response.PaketResponse;
import com.tomboati.tour.api.response.PaketWisataResponse;
import com.tomboati.tour.repository.Repository;

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

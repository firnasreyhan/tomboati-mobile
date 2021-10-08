package com.tomboati.tour.viewmodel.sholat;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.tomboati.tour.api.response.MasjidResponse;
import com.tomboati.tour.repository.Repository;

import java.util.List;

public class MasjidTerdekatViewModel extends AndroidViewModel {
    private Repository repository;

    public MasjidTerdekatViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
    }

    public MutableLiveData<List<MasjidResponse.Feature>> masjid(String query, String proximity, int limit) {
        return repository.masjid(
                query,
                proximity,
                limit,
                "pk.eyJ1Ijoicml6a3lha3MiLCJhIjoiY2tpbXF2OHU4MHcxNTJzcng4cXFpbnVoMCJ9.G0nNs9E5wdYaAbpuZw0-2A");
    }
}

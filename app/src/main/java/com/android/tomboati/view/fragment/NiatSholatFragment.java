package com.android.tomboati.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.tomboati.R;
import com.android.tomboati.adapter.SholatAdapter;
import com.android.tomboati.api.response.BacaanSholatResponse;
import com.android.tomboati.viewmodel.DoaHarianViewModel;
import com.android.tomboati.viewmodel.DoaTahlilViewModel;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

public class NiatSholatFragment extends Fragment {

    private DoaTahlilViewModel doaTahlilViewModel;
    private RecyclerView recyclerViewNiatSholat;
    private ShimmerFrameLayout shimmerFrameLayoutDoa;
    private SholatAdapter sholatAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_niat_sholat, container, false);


        doaTahlilViewModel = ViewModelProviders.of(this).get(DoaTahlilViewModel.class);
        recyclerViewNiatSholat = view.findViewById(R.id.recyclerViewNiatSholat);
        shimmerFrameLayoutDoa = view.findViewById(R.id.shimmerFrameLayoutDoa);

        recyclerViewNiatSholat.setHasFixedSize(true);
        recyclerViewNiatSholat.setLayoutManager(new LinearLayoutManager(view.getContext()));

        doaTahlilViewModel.getNiatSholat().observe(this, new Observer<List<BacaanSholatResponse>>() {
            @Override
            public void onChanged(List<BacaanSholatResponse> bacaanSholatResponses) {
                sholatAdapter = new SholatAdapter(bacaanSholatResponses, R.layout.item_doa_sehari_hari);
                recyclerViewNiatSholat.setAdapter(sholatAdapter);

                recyclerViewNiatSholat.setVisibility(View.VISIBLE);
                shimmerFrameLayoutDoa.setVisibility(View.GONE);
                shimmerFrameLayoutDoa.stopShimmer();
            }
        });

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        shimmerFrameLayoutDoa.startShimmer();
    }

    @Override
    public void onPause() {
        shimmerFrameLayoutDoa.stopShimmer();
        super.onPause();
    }
}
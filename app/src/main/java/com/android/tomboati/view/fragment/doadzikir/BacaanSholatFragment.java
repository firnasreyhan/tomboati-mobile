package com.android.tomboati.view.fragment.doadzikir;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.tomboati.R;
import com.android.tomboati.adapter.SholatAdapter;
import com.android.tomboati.adapter.TahlilAdapter;
import com.android.tomboati.api.response.BacaanSholatResponse;
import com.android.tomboati.api.response.TahlilResponse;
import com.android.tomboati.viewmodel.DoaTahlilViewModel;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

public class BacaanSholatFragment extends Fragment {

    private DoaTahlilViewModel doaTahlilViewModel;
    private RecyclerView recyclerViewBacaanSholat;
    private LinearLayout linearLayoutContent;
    private ShimmerFrameLayout shimmerFrameLayoutAyat;
    private SholatAdapter sholatAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bacaan_sholat, container, false);

        doaTahlilViewModel = ViewModelProviders.of(this).get(DoaTahlilViewModel.class);
        recyclerViewBacaanSholat = view.findViewById(R.id.recyclerViewBacaanSholat);
        shimmerFrameLayoutAyat = view.findViewById(R.id.shimmerFrameLayoutAyat);
        linearLayoutContent = view.findViewById(R.id.linearLayoutContent);


        recyclerViewBacaanSholat.setHasFixedSize(true);
        recyclerViewBacaanSholat.setLayoutManager(new LinearLayoutManager(view.getContext()));

        doaTahlilViewModel.getBacaanSholat().observe(this, new Observer<List<BacaanSholatResponse>>() {
            @Override
            public void onChanged(List<BacaanSholatResponse> bacaanSholatResponses) {
                sholatAdapter = new SholatAdapter(bacaanSholatResponses, R.layout.item_istighosah);
                recyclerViewBacaanSholat.setAdapter(sholatAdapter);

                linearLayoutContent.setVisibility(View.VISIBLE);
                shimmerFrameLayoutAyat.setVisibility(View.GONE);
                shimmerFrameLayoutAyat.stopShimmer();
            }
        });

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        shimmerFrameLayoutAyat.startShimmer();
    }

    @Override
    public void onPause() {
        shimmerFrameLayoutAyat.stopShimmer();
        super.onPause();
    }
}
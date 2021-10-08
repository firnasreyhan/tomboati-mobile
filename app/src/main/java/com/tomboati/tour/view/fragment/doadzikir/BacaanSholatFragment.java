package com.tomboati.tour.view.fragment.doadzikir;

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

import com.tomboati.tour.R;
import com.tomboati.tour.adapter.SholatAdapter;
import com.tomboati.tour.api.response.BacaanSholatResponse;
import com.tomboati.tour.viewmodel.doa.DoaTahlilViewModel;
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
                bacaanSholatResponses.add(3, new BacaanSholatResponse(
                        9, "Bacaan I'tidal", "سَمِعَ اللهُ لِمَنْ حَمِدَهُ", "Sami'allaahu liman " +
                        "hamidah", "Allah maha mendengar terhadap orang yang memujinya."
                ));
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
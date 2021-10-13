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
import com.tomboati.tour.adapter.TahlilAdapter;
import com.tomboati.tour.api.response.TahlilResponse;
import com.tomboati.tour.viewmodel.doa.DoaTahlilViewModel;
import com.facebook.shimmer.ShimmerFrameLayout;

public class TahlilFragment extends Fragment {

    private DoaTahlilViewModel doaTahlilViewModel;
    private RecyclerView recyclerViewTahlil;
    private LinearLayout linearLayoutContent;
    private ShimmerFrameLayout shimmerFrameLayoutAyat;
    private TahlilAdapter tahlilAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tahlil, container, false);

        doaTahlilViewModel = ViewModelProviders.of(this).get(DoaTahlilViewModel.class);
        recyclerViewTahlil = view.findViewById(R.id.recyclerViewTahlil);
        shimmerFrameLayoutAyat = view.findViewById(R.id.shimmerFrameLayoutAyat);
        linearLayoutContent = view.findViewById(R.id.linearLayoutContent);


        recyclerViewTahlil.setHasFixedSize(true);
        recyclerViewTahlil.setLayoutManager(new LinearLayoutManager(view.getContext()));

        doaTahlilViewModel.getDoaTahlil().observe(this, new Observer<TahlilResponse>() {
            @Override
            public void onChanged(TahlilResponse tahlilResponse) {
                tahlilAdapter = new TahlilAdapter(tahlilResponse.getData());
                recyclerViewTahlil.setAdapter(tahlilAdapter);

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
package com.android.tomboati.view.fragment.quran;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.tomboati.R;
import com.android.tomboati.adapter.SurahAdapter;
import com.android.tomboati.api.response.SurahResponse;
import com.android.tomboati.viewmodel.AlQuranViewModel;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

public class SurahFragment extends Fragment {
    private AlQuranViewModel alQuranViewModel;
    private RecyclerView recyclerViewSurah;
    private SurahAdapter surahAdapter;
    private ShimmerFrameLayout shimmerFrameLayoutSurah;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_surah, container, false);

        alQuranViewModel = ViewModelProviders.of(this).get(AlQuranViewModel.class);
        recyclerViewSurah = view.findViewById(R.id.recyclerViewSurah);
        shimmerFrameLayoutSurah = view.findViewById(R.id.shimmerFrameLayoutSurah);
        recyclerViewSurah.setHasFixedSize(true);
        recyclerViewSurah.setLayoutManager(new LinearLayoutManager(getContext()));

        alQuranViewModel.getSurah().observe(getActivity(), new Observer<List<SurahResponse>>() {
            @Override
            public void onChanged(List<SurahResponse> surahResponses) {
                if (!surahResponses.isEmpty()) {
                    surahAdapter = new SurahAdapter(surahResponses);
                    recyclerViewSurah.setAdapter(surahAdapter);

                    recyclerViewSurah.setVisibility(View.VISIBLE);
                    shimmerFrameLayoutSurah.setVisibility(View.GONE);
                    shimmerFrameLayoutSurah.stopShimmer();
                }
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        shimmerFrameLayoutSurah.startShimmer();
    }

    @Override
    public void onPause() {
        shimmerFrameLayoutSurah.stopShimmer();
        super.onPause();
    }
}
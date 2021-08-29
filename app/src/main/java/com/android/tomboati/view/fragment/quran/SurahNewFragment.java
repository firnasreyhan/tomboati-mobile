package com.android.tomboati.view.fragment.quran;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.tomboati.R;
import com.android.tomboati.adapter.SurahNewAdapter;
import com.android.tomboati.api.response.QuranListResponse;
import com.android.tomboati.viewmodel.AlQuranViewModel;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

public class SurahNewFragment extends Fragment {

    private RecyclerView recyclerViewSurah;
    private SurahNewAdapter surahNewAdapter;
    private ShimmerFrameLayout shimmerFrameLayoutSurah;
    private final LifecycleOwner OWNER = this;

    private static final String TAG = "SURAH NEW FRAGMENT";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_surah_new, container, false);

        AlQuranViewModel alQuranViewModel = ViewModelProviders.of(this).get(AlQuranViewModel.class);
        recyclerViewSurah = view.findViewById(R.id.recyclerViewSurah);
        shimmerFrameLayoutSurah = view.findViewById(R.id.shimmerFrameLayoutSurah);
        recyclerViewSurah.setHasFixedSize(true);
        recyclerViewSurah.setLayoutManager(new LinearLayoutManager(getContext()));

        alQuranViewModel.getSurahNew().observe(OWNER, new Observer<QuranListResponse>() {
            @Override
            public void onChanged(QuranListResponse quranListResponse) {
                if (quranListResponse.getCode() == 200) {
                    surahNewAdapter = new SurahNewAdapter(quranListResponse.getData());
                    recyclerViewSurah.setAdapter(surahNewAdapter);

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
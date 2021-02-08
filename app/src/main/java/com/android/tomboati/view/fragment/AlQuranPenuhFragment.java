package com.android.tomboati.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.tomboati.R;
import com.android.tomboati.adapter.AyatAdapter;
import com.android.tomboati.adapter.AyatPenuhAdapter;
import com.android.tomboati.api.response.AyatResponse;
import com.android.tomboati.viewmodel.DetailAlQuranViewModel;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

public class AlQuranPenuhFragment extends Fragment {
    private DetailAlQuranViewModel detailAlQuranViewModel;
    private RecyclerView recyclerViewAyatPenuh;
    private AyatPenuhAdapter ayatPenuhAdapter;
    private ShimmerFrameLayout shimmerFrameLayoutAyatPenuh;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_al_quran_penuh, container, false);

        detailAlQuranViewModel = ViewModelProviders.of(getActivity()).get(DetailAlQuranViewModel.class);
        recyclerViewAyatPenuh = view.findViewById(R.id.recyclerViewAyatPenuh);
        shimmerFrameLayoutAyatPenuh = view.findViewById(R.id.shimmerFrameLayoutAyatPenuh);

        recyclerViewAyatPenuh.setHasFixedSize(true);
        recyclerViewAyatPenuh.setLayoutManager(new LinearLayoutManager(getContext()));

        List<AyatResponse.AyatModel> list = new ArrayList<>();
        for (int i = 1; i <= 114; i++) {
            int x = i;
            detailAlQuranViewModel.getAyat(
                    String.valueOf(i)
            ).observe(this, new Observer<AyatResponse>() {
                @Override
                public void onChanged(AyatResponse ayatResponses) {
                    if (ayatResponses.isStatus()) {
                        list.addAll(ayatResponses.getAyat());
                        if (x == 114) {
                            ayatPenuhAdapter = new AyatPenuhAdapter(list);
                            recyclerViewAyatPenuh.setAdapter(ayatPenuhAdapter);

                            recyclerViewAyatPenuh.setVisibility(View.VISIBLE);
                            shimmerFrameLayoutAyatPenuh.setVisibility(View.GONE);
                            shimmerFrameLayoutAyatPenuh.stopShimmer();
                        }
                    }
                }
            });
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        shimmerFrameLayoutAyatPenuh.startShimmer();
    }

    @Override
    public void onPause() {
        shimmerFrameLayoutAyatPenuh.stopShimmer();
        super.onPause();
    }
}
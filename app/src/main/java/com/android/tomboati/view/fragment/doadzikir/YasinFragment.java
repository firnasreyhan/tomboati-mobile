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
import com.android.tomboati.adapter.AyatAdapter;
import com.android.tomboati.api.response.AyatResponse;
import com.android.tomboati.viewmodel.quran.DetailAlQuranViewModel;
import com.facebook.shimmer.ShimmerFrameLayout;

public class YasinFragment extends Fragment {

    private DetailAlQuranViewModel detailAlQuranViewModel;
    private RecyclerView recyclerViewAyat;
    private LinearLayout linearLayoutContent;
    private ShimmerFrameLayout shimmerFrameLayoutAyat;
    private AyatAdapter ayatAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_yasin, container, false);

        String idSurahYasin = "36";

        detailAlQuranViewModel = ViewModelProviders.of(this).get(DetailAlQuranViewModel.class);
        recyclerViewAyat = view.findViewById(R.id.recyclerViewAyat);
        shimmerFrameLayoutAyat = view.findViewById(R.id.shimmerFrameLayoutAyat);
        linearLayoutContent = view.findViewById(R.id.linearLayoutContent);


        recyclerViewAyat.setHasFixedSize(true);
        recyclerViewAyat.setLayoutManager(new LinearLayoutManager(view.getContext()));

        detailAlQuranViewModel.getAyat(
                idSurahYasin
        ).observe(this, new Observer<AyatResponse>() {
            @Override
            public void onChanged(AyatResponse ayatResponses) {
                if (ayatResponses.isStatus()) {
                    ayatAdapter = new AyatAdapter(ayatResponses.getAyat());
                    recyclerViewAyat.setAdapter(ayatAdapter);

                    linearLayoutContent.setVisibility(View.VISIBLE);
                    shimmerFrameLayoutAyat.setVisibility(View.GONE);
                    shimmerFrameLayoutAyat.stopShimmer();
                }
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
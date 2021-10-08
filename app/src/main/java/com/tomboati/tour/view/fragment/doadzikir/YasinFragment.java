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
import com.tomboati.tour.adapter.AyatNewAdapter;
import com.tomboati.tour.api.response.QuranSurahResponse;
import com.tomboati.tour.viewmodel.quran.DetailAlQuranViewModel;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

public class YasinFragment extends Fragment {

    private DetailAlQuranViewModel detailAlQuranViewModel;
    private RecyclerView recyclerViewAyat;
    private ShimmerFrameLayout shimmerFrameLayoutAyat;
    private LinearLayout linearLayoutContent;
    private AyatNewAdapter ayatNewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_yasin, container, false);

        String ID_SURAH = "36";

        recyclerViewAyat = view.findViewById(R.id.recyclerViewAyat);
        shimmerFrameLayoutAyat = view.findViewById(R.id.shimmerFrameLayoutAyat);
        linearLayoutContent = view.findViewById(R.id.linearLayoutContent);

        detailAlQuranViewModel = ViewModelProviders.of(this).get(DetailAlQuranViewModel.class);
        recyclerViewAyat.setHasFixedSize(true);
        recyclerViewAyat.setLayoutManager(new LinearLayoutManager(getContext()));

        detailAlQuranViewModel.getAyatNew(ID_SURAH).observe(this, new Observer<QuranSurahResponse>() {
            @Override
            public void onChanged(QuranSurahResponse quranSurahResponse) {
                if (quranSurahResponse.getCode() == 200) {
                    final List<QuranSurahResponse.Data.Verse> LIST_DATA = quranSurahResponse.getData().getVerses();

                    LIST_DATA.add(0, new QuranSurahResponse.Data.Verse(
                            new QuranSurahResponse.Data.Verse.Numbers(0, 0),
                            new QuranSurahResponse.Data.Verse.Text(
                                    "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ",
                                    new QuranSurahResponse.Data.Verse.Text.Transliteration__1("Bismillaahir Rahmaanir Raheem")
                            ),
                            new QuranSurahResponse.Data.Verse.Translation("Dengan nama Allah Yang Maha Pengasih, Maha Penyayang."),
                            new QuranSurahResponse.Data.Verse.Audio("https://cdn.alquran.cloud/media/audio/ayah/ar.alafasy/1")
                    ));

                    ayatNewAdapter = new AyatNewAdapter(LIST_DATA);
                    recyclerViewAyat.setAdapter(ayatNewAdapter);


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
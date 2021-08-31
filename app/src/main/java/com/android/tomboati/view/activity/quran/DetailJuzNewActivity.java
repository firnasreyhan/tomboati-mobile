package com.android.tomboati.view.activity.quran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.android.tomboati.R;
import com.android.tomboati.adapter.AyatAdapter;
import com.android.tomboati.adapter.AyatJuzNewAdapter;
import com.android.tomboati.adapter.AyatNewAdapter;
import com.android.tomboati.api.response.AyatResponse;
import com.android.tomboati.api.response.QuranSurahResponse;
import com.android.tomboati.model.JuzModelNew;
import com.android.tomboati.utils.JuzUtilityNew;
import com.android.tomboati.utils.Utility;
import com.android.tomboati.viewmodel.DetailAlQuranViewModel;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DetailJuzNewActivity extends AppCompatActivity {

    private static final String TAG = "DETAIL JUZ NEW ACTIVITY";

    private DetailAlQuranViewModel detailAlQuranViewModel;
    private Toolbar toolbar;
    private RecyclerView recyclerViewAyat;
    private AyatJuzNewAdapter ayatNewAdapter;
    private ShimmerFrameLayout shimmerFrameLayoutAyat;
    private int countSurah = 0;
    private final LifecycleOwner OWNER = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_detail_juz_new);

        int POSITION = getIntent().getIntExtra("POSITION", 0);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Juz " + (POSITION + 1));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        detailAlQuranViewModel = ViewModelProviders.of(this).get(DetailAlQuranViewModel.class);
        recyclerViewAyat = findViewById(R.id.recyclerViewAyat);
        shimmerFrameLayoutAyat = findViewById(R.id.shimmerFrameLayoutAyat);

        recyclerViewAyat.setHasFixedSize(true);
        recyclerViewAyat.setLayoutManager(new LinearLayoutManager(this));

        final JuzUtilityNew juzUtilityNew = new JuzUtilityNew();
        final JuzModelNew.ListSurah[] listSurahs = juzUtilityNew.getListJuz().get(POSITION).getListSurahs();

        final List<QuranSurahResponse.Data.Verse> listSurahAll = new ArrayList<>();
        final List<JuzModelNew.AyatModel> listAyatModels = new ArrayList<>();

        for (JuzModelNew.ListSurah listSurah : listSurahs) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    detailAlQuranViewModel.getAyatNew("" + listSurah.getNomorSurah()).observe(OWNER, new Observer<QuranSurahResponse>() {
                        @Override
                        public void onChanged(QuranSurahResponse quranSurahResponse) {
                            if (quranSurahResponse.getCode() == 200) {
                                final JuzModelNew.AyatModel model = new JuzModelNew.AyatModel();
                                model.setNamaAyat(quranSurahResponse.getData().getName().getTransliteration().getId());
                                model.setNomorSurah(listSurah.getNomorSurah());
                                if (listSurah.isFullSurah()) {
                                    listSurahAll.addAll(quranSurahResponse.getData().getVerses());
                                    model.setBanyakAyat(quranSurahResponse.getData().getVerses().size());
                                } else {
                                    int count = 0;
                                    for (int i = listSurah.getAyatAwal() - 1; i <= listSurah.getAyatAkhir() - 1; i++) {
                                        listSurahAll.add(quranSurahResponse.getData().getVerses().get(i));
                                        count++;
                                    }
                                    model.setBanyakAyat(count);
                                }

                                countSurah++;
                                listAyatModels.add(model);

                                if (listSurahs.length == countSurah) {
                                    Collections.sort(listSurahAll, sortSurah);
                                    Collections.sort(listAyatModels, sortAyat);
                                    ayatNewAdapter = new AyatJuzNewAdapter(listSurahAll, listAyatModels);
                                    recyclerViewAyat.setAdapter(ayatNewAdapter);

                                    recyclerViewAyat.setVisibility(View.VISIBLE);
                                    shimmerFrameLayoutAyat.setVisibility(View.GONE);
                                    shimmerFrameLayoutAyat.stopShimmer();
                                }
                            }
                        }
                    });

                }
            }, 1000);
        }


    }

    public static Comparator<JuzModelNew.AyatModel> sortAyat = new Comparator<JuzModelNew.AyatModel>() {
        @Override
        public int compare(JuzModelNew.AyatModel o1, JuzModelNew.AyatModel o2) {
            int idNo1 = o1.getNomorSurah();
            int idNo2 = o2.getNomorSurah();

            /*For ascending order*/
            return idNo1-idNo2;
        }
    };

    public static Comparator<QuranSurahResponse.Data.Verse> sortSurah = new Comparator<QuranSurahResponse.Data.Verse>() {
        @Override
        public int compare(QuranSurahResponse.Data.Verse o1, QuranSurahResponse.Data.Verse o2) {
            int idNo1 = o1.getNumber().getInQuran();
            int idNo2 = o2.getNumber().getInQuran();

            /*For ascending order*/
            return idNo1-idNo2;
        }
    };


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

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
package com.android.tomboati.view.activity.quran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.android.tomboati.R;
import com.android.tomboati.adapter.AyatAdapter;
import com.android.tomboati.adapter.AyatNewAdapter;
import com.android.tomboati.api.response.QuranSurahResponse;
import com.android.tomboati.viewmodel.DetailAlQuranViewModel;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

public class DetailAlQuranNewActivity extends AppCompatActivity {

    private DetailAlQuranViewModel detailAlQuranViewModel;
    private RecyclerView recyclerViewAyat;
    private ShimmerFrameLayout shimmerFrameLayoutAyat;
    private LinearLayout linearLayoutContent;
    private AyatNewAdapter ayatNewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_detail_al_quran_new);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("....");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerViewAyat = findViewById(R.id.recyclerViewAyat);
        shimmerFrameLayoutAyat = findViewById(R.id.shimmerFrameLayoutAyat);
        linearLayoutContent = findViewById(R.id.linearLayoutContent);

        detailAlQuranViewModel = ViewModelProviders.of(this).get(DetailAlQuranViewModel.class);

        final Intent intent = this.getIntent();
        final int ID_SURAH = intent.getIntExtra("ID_SURAH", 0);

        recyclerViewAyat.setHasFixedSize(true);
        recyclerViewAyat.setLayoutManager(new LinearLayoutManager(this));

        detailAlQuranViewModel.getAyatNew(String.valueOf(ID_SURAH)).observe(this, new Observer<QuranSurahResponse>() {
            @Override
            public void onChanged(QuranSurahResponse quranSurahResponse) {
                if(quranSurahResponse.getCode() == 200) {
                    toolbar.setTitle(quranSurahResponse.getData().getName().getTransliteration().getId());
                    final List<QuranSurahResponse.Data.Verse> LIST_DATA = quranSurahResponse.getData().getVerses();
                    if(ID_SURAH > 1) {
                        LIST_DATA.add(0, new QuranSurahResponse.Data.Verse(
                                new QuranSurahResponse.Data.Verse.Numbers(0, 0),
                                new QuranSurahResponse.Data.Verse.Text(
                                        "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ",
                                        new QuranSurahResponse.Data.Verse.Text.Transliteration__1("Bismillaahir Rahmaanir Raheem")
                                ),
                                new QuranSurahResponse.Data.Verse.Translation("Dengan nama Allah Yang Maha Pengasih, Maha Penyayang."),
                                new QuranSurahResponse.Data.Verse.Audio("https://cdn.alquran.cloud/media/audio/ayah/ar.alafasy/1")
                        ));
                    }
                    ayatNewAdapter = new AyatNewAdapter(LIST_DATA);
                    recyclerViewAyat.setAdapter(ayatNewAdapter);


                    linearLayoutContent.setVisibility(View.VISIBLE);
                    shimmerFrameLayoutAyat.setVisibility(View.GONE);
                    shimmerFrameLayoutAyat.stopShimmer();
                }
            }
        });


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
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
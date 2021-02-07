package com.android.tomboati.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.android.tomboati.R;
import com.android.tomboati.adapter.AyatAdapter;
import com.android.tomboati.adapter.SurahAdapter;
import com.android.tomboati.api.response.AyatResponse;
import com.android.tomboati.viewmodel.DetailAlQuranViewModel;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

public class DetailAlQuranActivity extends AppCompatActivity {
    private DetailAlQuranViewModel detailAlQuranViewModel;
    private Toolbar toolbar;
    private RecyclerView recyclerViewAyat;
    private AyatAdapter ayatAdapter;
    private ShimmerFrameLayout shimmerFrameLayoutAyat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_detail_al_quran);
        String idSurah = getIntent().getStringExtra("ID_SURAH");
        String namaSurah = getIntent().getStringExtra("NAMA_SURAH");

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(namaSurah);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        detailAlQuranViewModel = ViewModelProviders.of(this).get(DetailAlQuranViewModel.class);
        recyclerViewAyat = findViewById(R.id.recyclerViewAyat);
        shimmerFrameLayoutAyat = findViewById(R.id.shimmerFrameLayoutAyat);
        recyclerViewAyat.setHasFixedSize(true);
        recyclerViewAyat.setLayoutManager(new LinearLayoutManager(this));

        detailAlQuranViewModel.getAyat(
                idSurah
        ).observe(this, new Observer<AyatResponse>() {
            @Override
            public void onChanged(AyatResponse ayatResponses) {
                if (ayatResponses.isStatus()) {
                    ayatAdapter = new AyatAdapter(ayatResponses.getAyat());
                    recyclerViewAyat.setAdapter(ayatAdapter);

                    recyclerViewAyat.setVisibility(View.VISIBLE);
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
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
import com.android.tomboati.adapter.SurahAdapter;
import com.android.tomboati.api.response.SurahResponse;
import com.android.tomboati.viewmodel.AlQuranViewModel;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

public class AlQuranActivity extends AppCompatActivity {
    private AlQuranViewModel alQuranViewModel;
    private RecyclerView recyclerViewSurah;
    private SurahAdapter surahAdapter;
    private Toolbar toolbar;
    private ShimmerFrameLayout shimmerFrameLayoutSurah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_al_quran);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Al Qur’an");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        alQuranViewModel = ViewModelProviders.of(this).get(AlQuranViewModel.class);
        recyclerViewSurah = findViewById(R.id.recyclerViewSurah);
        shimmerFrameLayoutSurah = findViewById(R.id.shimmerFrameLayoutSurah);
        recyclerViewSurah.setHasFixedSize(true);
        recyclerViewSurah.setLayoutManager(new LinearLayoutManager(this));

        alQuranViewModel.getSurah().observe(this, new Observer<List<SurahResponse>>() {
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
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
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
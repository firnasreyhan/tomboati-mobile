package com.android.tomboati.view.activity.quran;

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
import com.android.tomboati.api.response.AyatResponse;
import com.android.tomboati.model.JuzModel;
import com.android.tomboati.utils.JuzUtility;
import com.android.tomboati.viewmodel.quran.DetailAlQuranViewModel;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DetailJuzActivity extends AppCompatActivity {
    private DetailAlQuranViewModel detailAlQuranViewModel;
    private Toolbar toolbar;
    private RecyclerView recyclerViewAyat;
    private AyatAdapter ayatAdapter;
    private ShimmerFrameLayout shimmerFrameLayoutAyat;

    private int position, x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_detail_juz);

        x = 0;
        position = getIntent().getIntExtra("POSITION",0);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("JUZ " + (position + 1));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        detailAlQuranViewModel = ViewModelProviders.of(this).get(DetailAlQuranViewModel.class);
        recyclerViewAyat = findViewById(R.id.recyclerViewAyat);
        shimmerFrameLayoutAyat = findViewById(R.id.shimmerFrameLayoutAyat);

        recyclerViewAyat.setHasFixedSize(true);
        recyclerViewAyat.setLayoutManager(new LinearLayoutManager(this));

        JuzModel juzModel = new JuzUtility().getList().get(position);
        List<AyatResponse.AyatModel> list = new ArrayList<>();


        for (JuzModel.JuzDetailModel juzDetailModel : juzModel.getList()) {
            detailAlQuranViewModel.getAyat(juzDetailModel.getNomorSurah()).observe(this, new Observer<AyatResponse>() {
                @Override
                public void onChanged(AyatResponse ayatResponse) {
                    if (ayatResponse.isStatus()) {
                        x++;

                        for (AyatResponse.AyatModel ayatModel : ayatResponse.getAyat()) {
                            if (Integer.parseInt(ayatModel.getNomor()) >= juzDetailModel.getAwalAyat() && Integer.parseInt(ayatModel.getNomor()) <= juzDetailModel.getAkhirAyat()) {
                                list.add(ayatModel);
                            }
                        }

                        if (x == juzModel.getList().size()) {
                            Collections.sort(list, AyatResponse.AyatModel.sortAyat);
                            ayatAdapter = new AyatAdapter(list);
                            recyclerViewAyat.setAdapter(ayatAdapter);

                            recyclerViewAyat.setVisibility(View.VISIBLE);
                            shimmerFrameLayoutAyat.setVisibility(View.GONE);
                            shimmerFrameLayoutAyat.stopShimmer();
                        }
                    }
                }
            });
        }
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

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
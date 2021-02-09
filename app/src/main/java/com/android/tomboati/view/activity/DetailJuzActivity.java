package com.android.tomboati.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.android.tomboati.R;
import com.android.tomboati.adapter.AyatAdapter;
import com.android.tomboati.api.response.AyatResponse;
import com.android.tomboati.model.JuzModel;
import com.android.tomboati.utils.JuzUtility;
import com.android.tomboati.viewmodel.DetailAlQuranViewModel;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.button.MaterialButton;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DetailJuzActivity extends AppCompatActivity {
    private DetailAlQuranViewModel detailAlQuranViewModel;
    private Toolbar toolbar;
    private RecyclerView recyclerViewAyat;
    private AyatAdapter ayatAdapter;
    private ShimmerFrameLayout shimmerFrameLayoutAyat;

    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_detail_juz);

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

        for (int i = 0; i < juzModel.getList().size(); i++) {
            int x = i;
            detailAlQuranViewModel.getAyat(
                    juzModel.getList().get(i).getNomorSurah()
            ).observe(this, new Observer<AyatResponse>() {
                @Override
                public void onChanged(AyatResponse ayatResponse) {
                    if (ayatResponse.isStatus()) {
                        for (int j = (juzModel.getList().get(x).getAwalAyat() - 1); j < juzModel.getList().get(x).getAkhirAyat(); j++) {
                            list.add(ayatResponse.getAyat().get(j));
                        }

                        if (x == (juzModel.getList().size() - 1)) {
                            ayatAdapter = new AyatAdapter(list);
                            recyclerViewAyat.setAdapter(ayatAdapter);

                            Log.e("size", String.valueOf(list.size()));

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
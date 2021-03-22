package com.android.tomboati.view.activity.quran;

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
import com.android.tomboati.api.ApiClient;
import com.android.tomboati.api.ApiInterfaceAlQuran;
import com.android.tomboati.api.response.AyatResponse;
import com.android.tomboati.model.JuzModel;
import com.android.tomboati.utils.JuzUtility;
import com.android.tomboati.viewmodel.DetailAlQuranViewModel;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.button.MaterialButton;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

//        for (JuzModel.JuzDetailModel juzDetailModel : juzModel.getList()) {
//            apiInterfaceAlQuran.getAyat(
//                    juzDetailModel.getNomorSurah()
//            ).enqueue(new Callback<AyatResponse>() {
//                @Override
//                public void onResponse(Call<AyatResponse> call, Response<AyatResponse> response) {
//                    if (response.body().isStatus()) {
//                        list1.add(response.body());
//                        Log.e("surah", response.body().getNamaLatin());
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<AyatResponse> call, Throwable t) {
//
//                }
//            });
//        }

        for (JuzModel.JuzDetailModel juzDetailModel : juzModel.getList()) {
//            Log.e("surah", juzDetailModel.getNomorSurah());
//            Log.e("awal", String.valueOf(juzDetailModel.getAwalAyat()));
//            Log.e("akhir", String.valueOf(juzDetailModel.getAkhirAyat()));
            detailAlQuranViewModel.getAyat(
                    juzDetailModel.getNomorSurah()
            ).observe(this, new Observer<AyatResponse>() {
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
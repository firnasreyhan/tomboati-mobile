package com.android.tomboati.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.android.tomboati.R;
import com.android.tomboati.adapter.DoaHarianAdapter;
import com.android.tomboati.adapter.TahlilAdapter;
import com.android.tomboati.api.response.DoaHarianResponse;
import com.android.tomboati.viewmodel.DoaHarianViewModel;
import com.android.tomboati.viewmodel.DoaTahlilViewModel;
import com.facebook.shimmer.ShimmerFrameLayout;

public class DoaSehariHariActivity extends AppCompatActivity {


    private DoaHarianViewModel doaHarianViewModel;
    private RecyclerView recyclerViewDoaSehari;
//    private LinearLayout linearLayoutContent;
    private ShimmerFrameLayout shimmerFrameLayoutDoa;
    private DoaHarianAdapter doaHarianAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_doa_sehari_hari);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Doa Sehari Hari");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        doaHarianViewModel = ViewModelProviders.of(this).get(DoaHarianViewModel.class);
        recyclerViewDoaSehari = findViewById(R.id.recyclerViewDoaSehari);
        shimmerFrameLayoutDoa = findViewById(R.id.shimmerFrameLayoutDoa);
//        linearLayoutContent = findViewById(R.id.linearLayoutContent);


        recyclerViewDoaSehari.setHasFixedSize(true);
        recyclerViewDoaSehari.setLayoutManager(new LinearLayoutManager(this));

        doaHarianViewModel.getDoaHarian().observe(this, new Observer<DoaHarianResponse>() {
            @Override
            public void onChanged(DoaHarianResponse doaHarianResponse) {
                doaHarianAdapter = new DoaHarianAdapter(doaHarianResponse.getData());
                recyclerViewDoaSehari.setAdapter(doaHarianAdapter);

                recyclerViewDoaSehari.setVisibility(View.VISIBLE);
                shimmerFrameLayoutDoa.setVisibility(View.GONE);
                shimmerFrameLayoutDoa.stopShimmer();
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
        shimmerFrameLayoutDoa.startShimmer();
    }

    @Override
    public void onPause() {
        shimmerFrameLayoutDoa.stopShimmer();
        super.onPause();
    }
}
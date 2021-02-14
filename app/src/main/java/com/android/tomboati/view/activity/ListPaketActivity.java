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
import com.android.tomboati.adapter.PaketAdapter;
import com.android.tomboati.api.response.PaketResponse;
import com.android.tomboati.model.PaketModel;
import com.android.tomboati.viewmodel.ListPaketViewModel;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;

public class ListPaketActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerViewPaket;
    private PaketAdapter paketAdapter;
    private ListPaketViewModel listPaketViewModel;
    private ShimmerFrameLayout shimmerFrameLayoutPaket;
    private LinearLayout linearLayoutPaket, linearLayoutNoPaket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        listPaketViewModel = ViewModelProviders.of(this).get(ListPaketViewModel.class);
        setContentView(R.layout.activity_list_paket);

        String paket = getIntent().getStringExtra("PAKET");

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Paket");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerViewPaket = findViewById(R.id.recyclerViewPaket);
        shimmerFrameLayoutPaket = findViewById(R.id.shimmerFrameLayoutPaket);
        linearLayoutPaket = findViewById(R.id.linearLayoutPaket);
        linearLayoutNoPaket = findViewById(R.id.linearLayoutNoPaket);
        recyclerViewPaket.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewPaket.setHasFixedSize(true);

        listPaketViewModel.getPaket(
                paket
        ).observe(this, new Observer<PaketResponse>() {
            @Override
            public void onChanged(PaketResponse paketResponse) {
                if (!paketResponse.isError()) {
                    if (!paketResponse.getData().isEmpty()) {
                        paketAdapter = new PaketAdapter(paketResponse.getData());
                        recyclerViewPaket.setAdapter(paketAdapter);

                        linearLayoutPaket.setVisibility(View.VISIBLE);
                        linearLayoutNoPaket.setVisibility(View.GONE);
                    } else {
                        linearLayoutPaket.setVisibility(View.GONE);
                        linearLayoutNoPaket.setVisibility(View.VISIBLE);
                    }
                } else {
                    linearLayoutPaket.setVisibility(View.GONE);
                    linearLayoutNoPaket.setVisibility(View.VISIBLE);
                }
                shimmerFrameLayoutPaket.setVisibility(View.GONE);
                shimmerFrameLayoutPaket.stopShimmer();
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
        shimmerFrameLayoutPaket.startShimmer();
    }

    @Override
    public void onPause() {
        shimmerFrameLayoutPaket.stopShimmer();
        super.onPause();
    }
}
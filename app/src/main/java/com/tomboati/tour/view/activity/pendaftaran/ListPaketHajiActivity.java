package com.tomboati.tour.view.activity.pendaftaran;

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

import com.tomboati.tour.R;
import com.tomboati.tour.adapter.PaketHajiAdapter;
import com.tomboati.tour.api.response.PaketResponse;
import com.tomboati.tour.viewmodel.tomboati.pendaftaran.ListPaketViewModel;
import com.facebook.shimmer.ShimmerFrameLayout;

public class ListPaketHajiActivity extends AppCompatActivity implements PaketHajiAdapter.onSelectedData {

    private RecyclerView recyclerViewPaket;
    private PaketHajiAdapter paketAdapter;
    private ListPaketViewModel listPaketViewModel;
    private ShimmerFrameLayout shimmerFrameLayoutPaket;
    private LinearLayout linearLayoutPaket, linearLayoutNoPaket, linearLayoutSpinner;
    private String paket, title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        listPaketViewModel = ViewModelProviders.of(this).get(ListPaketViewModel.class);
        setContentView(R.layout.activity_list_paket);

        paket = getIntent().getStringExtra("PAKET_HAJI");
        title = getIntent().getStringExtra("TITLE");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerViewPaket = findViewById(R.id.recyclerViewPaket);
        shimmerFrameLayoutPaket = findViewById(R.id.shimmerFrameLayoutPaket);
        linearLayoutPaket = findViewById(R.id.linearLayoutPaket);
        linearLayoutNoPaket = findViewById(R.id.linearLayoutNoPaket);
        linearLayoutSpinner = findViewById(R.id.layoutPilihBulan);

        linearLayoutSpinner.setVisibility(View.GONE);

        recyclerViewPaket.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewPaket.setHasFixedSize(true);

        listPaketViewModel.getPaketHaji(paket, "99").observe(this, new Observer<PaketResponse>() {
            @Override
            public void onChanged(PaketResponse paketResponse) {
                if (!paketResponse.isError()) {
                    if (!paketResponse.getData().isEmpty()) {
                        paketAdapter = new PaketHajiAdapter(paketResponse.getData(), ListPaketHajiActivity.this);
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

    @Override
    public void onSelected(String idPaket) {
        Intent intent = new Intent(this, SyaratActivity.class);
        intent.putExtra("ID_PAKET", idPaket);
        intent.putExtra("IS_PAKET_HAJI", true);
        if(this.paket.equals("Badal")) {
            intent.putExtra("IS_PAKET_HAJI_BADAL", true);
        } else {
            intent.putExtra("NAMA_PAKET", title);
        }
        this.startActivity(intent);
    }
}
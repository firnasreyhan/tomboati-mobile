package com.android.tomboati.view.activity.pembayaran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.android.tomboati.R;
import com.android.tomboati.adapter.DetailPembayaranAdapter;
import com.android.tomboati.api.response.DetailPembayaranResponse;
import com.android.tomboati.viewmodel.tomboati.homepage.PembayaranViewModel;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

public class DetailPembayaranActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ShimmerFrameLayout shimmerFrameLayoutDetailPembayaran;
    private PembayaranViewModel viewModel;
    private DetailPembayaranAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_detail_pembayaran);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Detail Pembayaran");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        viewModel = ViewModelProviders.of(this).get(PembayaranViewModel.class);
        recyclerView = findViewById(R.id.recycleViewDetailPembayaran);
        shimmerFrameLayoutDetailPembayaran = findViewById(R.id.shimmerFrameLayoutDetailPembayaran);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final String ID_PEMBAYARAN = getIntent().getStringExtra("ID_PEMBAYARAN");
        viewModel.getDetailPembayaran(ID_PEMBAYARAN).observe(this, new Observer<DetailPembayaranResponse>() {
            @Override
            public void onChanged(DetailPembayaranResponse detailPembayaranResponse) {
                if(detailPembayaranResponse != null) {
                    if (!detailPembayaranResponse.isError()) {
                        shimmerFrameLayoutDetailPembayaran.setVisibility(View.GONE);
                        shimmerFrameLayoutDetailPembayaran.stopShimmer();
                        recyclerView.setVisibility(View.VISIBLE);

                        final List<DetailPembayaranResponse.DataItem> list = detailPembayaranResponse.getData();
                        adapter = new DetailPembayaranAdapter(list);
                        recyclerView.setAdapter(adapter);
                    }
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
        shimmerFrameLayoutDetailPembayaran.startShimmer();
    }

    @Override
    public void onPause() {
        shimmerFrameLayoutDetailPembayaran.stopShimmer();
        super.onPause();
    }
}
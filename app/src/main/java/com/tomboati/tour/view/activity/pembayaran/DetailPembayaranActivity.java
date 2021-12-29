package com.tomboati.tour.view.activity.pembayaran;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tomboati.tour.adapter.DetailPembayaranAdapter;
import com.tomboati.tour.databinding.ActivityDetailPembayaranBinding;
import com.tomboati.tour.view.activity.base.BaseToolbarActivity;
import com.tomboati.tour.viewmodel.tomboati.homepage.PembayaranViewModel;

public class DetailPembayaranActivity extends BaseToolbarActivity {

    private ActivityDetailPembayaranBinding bind;
    private PembayaranViewModel viewModel;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        bind = ActivityDetailPembayaranBinding.inflate(getLayoutInflater());
        setToolbar(bind.toolbar, "Detail Pembayaran");

        viewModel = ViewModelProviders.of(this).get(PembayaranViewModel.class);

        final String ID_PEMBAYARAN = intent.getStringExtra("ID_PEMBAYARAN");
        viewModel.getDetailPembayaran(ID_PEMBAYARAN).observe(getOwner(), detailPembayaranResponse -> {
            if(detailPembayaranResponse != null) {
                if (!detailPembayaranResponse.isError()) {
                    bind.shimmerFrameLayoutDetailPembayaran.setVisibility(View.GONE);
                    bind.shimmerFrameLayoutDetailPembayaran.stopShimmer();
                    bind.recycleViewDetailPembayaran.setVisibility(View.VISIBLE);
                    setRecyclerView(bind.recycleViewDetailPembayaran, new DetailPembayaranAdapter(detailPembayaranResponse.getData()));
                }
            }
        });
    }

    @Override
    protected View getContentView() {
        return bind.getRoot();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        bind.shimmerFrameLayoutDetailPembayaran.startShimmer();
    }

    @Override
    public void onPause() {
        bind.shimmerFrameLayoutDetailPembayaran.stopShimmer();
        super.onPause();
    }
}
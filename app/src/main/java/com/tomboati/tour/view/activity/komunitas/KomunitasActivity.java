package com.tomboati.tour.view.activity.komunitas;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tomboati.tour.adapter.KomunitasAdapter;
import com.tomboati.tour.databinding.ActivityKomunitasBinding;
import com.tomboati.tour.utils.Utility;
import com.tomboati.tour.view.activity.base.BaseToolbarActivity;
import com.tomboati.tour.viewmodel.tomboati.homepage.KomunitasViewModel;


public class KomunitasActivity extends BaseToolbarActivity {

    private ActivityKomunitasBinding bind;
    private KomunitasViewModel komunitasViewModel;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        bind = ActivityKomunitasBinding.inflate(getLayoutInflater());
        setToolbar(bind.toolbar, "Komunitas");
        komunitasViewModel = ViewModelProviders.of(this).get(KomunitasViewModel.class);

        if(Utility.isConnecting(this)) {
            komunitasViewModel.getKomunitas().observe(getOwner(), data -> {
                if(data.getError()) {
                    showToast(data.getMessage());
                    finish();
                } else {
                    setRecyclerView(bind.recyclerViewKomunitas, new KomunitasAdapter(data.getData()));
                    bind.recyclerViewKomunitas.setVisibility(View.VISIBLE);
                    bind.shimmerFrameLayoutKomunitas.setVisibility(View.GONE);
                    bind.shimmerFrameLayoutKomunitas.stopShimmer();
                }
            });
        }
    }

    @Override
    protected View getContentView() {
        return bind.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        bind.shimmerFrameLayoutKomunitas.startShimmer();
    }

    @Override
    public void onPause() {
        bind.shimmerFrameLayoutKomunitas.stopShimmer();
        super.onPause();
    }

}
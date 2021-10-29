package com.tomboati.tour.view.activity.doa_dzikir;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tomboati.tour.adapter.TasbihAdapter;
import com.tomboati.tour.databinding.ActivityTasbihBinding;
import com.tomboati.tour.view.activity.base.BaseToolbarActivity;

public class TasbihActivity extends BaseToolbarActivity {

    private ActivityTasbihBinding bind;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        bind = ActivityTasbihBinding.inflate(getLayoutInflater());
        setToolbar(bind.toolbar, "Tasbih");
        setRecyclerView(bind.recyclerViewTasbih, new TasbihAdapter());
    }

    @Override
    protected View getContentView() {
        return bind.getRoot();
    }

}
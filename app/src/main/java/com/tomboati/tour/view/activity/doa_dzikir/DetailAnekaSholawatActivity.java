package com.tomboati.tour.view.activity.doa_dzikir;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tomboati.tour.databinding.ActivityDetailAnekaSholawatBinding;
import com.tomboati.tour.model.DoaModel;
import com.tomboati.tour.view.activity.base.BaseToolbarActivity;


public class DetailAnekaSholawatActivity extends BaseToolbarActivity {

    private ActivityDetailAnekaSholawatBinding bind;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        bind = ActivityDetailAnekaSholawatBinding.inflate(getLayoutInflater());
        setToolbar(bind.toolbar, "Detail Sholawat");
        final DoaModel OBJECT = (DoaModel) intent.getSerializableExtra("OBJECT");
        bind.setDoa(OBJECT);
    }

    @Override
    protected View getContentView() {
        return bind.getRoot();
    }
}
package com.tomboati.tour.view.activity.doa_dzikir;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tomboati.tour.databinding.ActivityDetailAsmaulHusnaBinding;
import com.tomboati.tour.model.AsmaulHusnaModel;
import com.tomboati.tour.view.activity.base.BaseToolbarActivity;

public class DetailAsmaulHusnaActivity extends BaseToolbarActivity {

    private ActivityDetailAsmaulHusnaBinding bind;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        bind = ActivityDetailAsmaulHusnaBinding.inflate(getLayoutInflater());
        final AsmaulHusnaModel OBJECT = (AsmaulHusnaModel) intent.getSerializableExtra("OBJECT");
        setToolbar(bind.toolbar, OBJECT.getLatin());
        bind.setAsma(OBJECT);
    }

    @Override
    protected View getContentView() {
        return bind.getRoot();
    }
}
package com.tomboati.tour.view.activity.doa_dzikir;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tomboati.tour.databinding.ActivityDetailDoaSehariHariBinding;
import com.tomboati.tour.model.DoaModel;
import com.tomboati.tour.view.activity.base.BaseToolbarActivity;


public class DetailDoaSehariHariActivity extends BaseToolbarActivity {

    private ActivityDetailDoaSehariHariBinding bind;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        bind = ActivityDetailDoaSehariHariBinding.inflate(getLayoutInflater());
        setToolbar(bind.toolbar, "Detail Doa");
        final DoaModel OBJECT = (DoaModel) intent.getSerializableExtra("OBJECT");
        bind.setDoa(OBJECT);
    }

    @Override
    protected View getContentView() {
        return bind.getRoot();
    }

}
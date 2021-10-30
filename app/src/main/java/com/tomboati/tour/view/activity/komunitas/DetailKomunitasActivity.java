package com.tomboati.tour.view.activity.komunitas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.tomboati.tour.databinding.ActivityDetailKomunitasBinding;
import com.tomboati.tour.model.NewsModel;
import com.tomboati.tour.view.activity.base.BaseToolbarActivity;

public class DetailKomunitasActivity extends BaseToolbarActivity {

    private ActivityDetailKomunitasBinding bind;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        bind = ActivityDetailKomunitasBinding.inflate(getLayoutInflater());
        setToolbar(bind.toolbar, "Detail Komunitas");

        NewsModel newsModel = (NewsModel) intent.getSerializableExtra("OBJECT");
        picassoLoad(newsModel.getImage(), bind.imageDetail);
        bind.setNews(newsModel);
    }

    @Override
    protected View getContentView() {
        return bind.getRoot();
    }

}
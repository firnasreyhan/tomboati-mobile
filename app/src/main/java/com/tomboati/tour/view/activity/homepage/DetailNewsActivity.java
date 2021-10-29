package com.tomboati.tour.view.activity.homepage;

import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.tomboati.tour.databinding.ActivityDetailNewsBinding;
import com.tomboati.tour.helper.Common;
import com.tomboati.tour.model.NewsModel;
import com.tomboati.tour.view.activity.base.BaseToolbarActivity;
import com.tomboati.tour.viewmodel.tomboati.homepage.DetailNewsViewModel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailNewsActivity extends BaseToolbarActivity {

    private ActivityDetailNewsBinding bind;

    @SuppressLint("SimpleDateFormat")
    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        bind = ActivityDetailNewsBinding.inflate(getLayoutInflater());
        setToolbar(bind.toolbar, "News Islam");
        DetailNewsViewModel detailNewsViewModel = ViewModelProviders.of(this).get(DetailNewsViewModel.class);
        detailNewsViewModel.getNews().observe(getOwner(), newsResponse -> {
            if (!newsResponse.isError()) {
                if (!newsResponse.getData().isEmpty()) {
                    picassoLoad(newsResponse.getData().get(0).getFoto(), bind.imageViewNews);
                    String judul = newsResponse.getData().get(0).getJudulNews();
                    String news = Common.splitTextToString(newsResponse.getData().get(0).getContentNews());
                    String date = newsResponse.getData().get(0).getTanggalNews();
                    try {
                        Date formatDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(date);
                        date = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss").format(formatDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    bind.setNews(new NewsModel(judul, date, news));
                }
            }
        });
    }

    @Override
    protected View getContentView() {
        return bind.getRoot();
    }

}
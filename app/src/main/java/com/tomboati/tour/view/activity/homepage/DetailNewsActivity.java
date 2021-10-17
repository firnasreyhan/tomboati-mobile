package com.tomboati.tour.view.activity.homepage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.tomboati.tour.R;
import com.tomboati.tour.api.response.NewsResponse;
import com.tomboati.tour.databinding.ActivityDetailNewsBinding;
import com.tomboati.tour.helper.Common;
import com.tomboati.tour.model.NewsModel;
import com.tomboati.tour.utils.Utility;
import com.tomboati.tour.viewmodel.tomboati.homepage.DetailNewsViewModel;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DetailNewsActivity extends AppCompatActivity {

    private ActivityDetailNewsBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        bind = ActivityDetailNewsBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        DetailNewsViewModel detailNewsViewModel = ViewModelProviders.of(this).get(DetailNewsViewModel.class);

        setSupportActionBar(bind.toolbar);
        setTitle("NEWS ISLAM");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        detailNewsViewModel.getNews().observe(this, new Observer<NewsResponse>() {
            @Override
            @SuppressLint("SimpleDateFormat")
            public void onChanged(NewsResponse newsResponse) {
                if (!newsResponse.isError()) {
                    if (!newsResponse.getData().isEmpty()) {
                        Common.picassoLoad(newsResponse.getData().get(0).getFoto(), bind.imageViewNews);
                        String judul = newsResponse.getData().get(0).getJudulNews();
                        String news = Common.splitTextToString(newsResponse.getData().get(0).getContentNews());
                        String date = newsResponse.getData().get(0).getTanggalNews();
                        try {
                            Date formatDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(date);
                            date = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss").format(formatDate);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        bind.setNews(new NewsModel(
                                judul, date, news
                        ));
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
}
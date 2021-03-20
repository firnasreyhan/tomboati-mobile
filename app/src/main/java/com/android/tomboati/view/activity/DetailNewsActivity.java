package com.android.tomboati.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.graphics.Bitmap;
import android.icu.util.UniversalTimeScale;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.tomboati.R;
import com.android.tomboati.api.response.NewsResponse;
import com.android.tomboati.utils.Utility;
import com.android.tomboati.viewmodel.DetailNewsViewModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.codesgood.views.JustifiedTextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class DetailNewsActivity extends AppCompatActivity {

    private DetailNewsViewModel detailNewsViewModel;
    private Toolbar toolbar;
    private TextView textViewJudulNews, textViewDateNews;
    private JustifiedTextView textViewContentNews;
    private ImageView imageViewNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_detail_news);

        detailNewsViewModel = ViewModelProviders.of(this).get(DetailNewsViewModel.class);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("NEWS ISLAM");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        textViewJudulNews = findViewById(R.id.textViewJudulNews);
        textViewDateNews = findViewById(R.id.textViewDateNews);
        textViewContentNews =  findViewById(R.id.textViewContentNews);
        imageViewNews = findViewById(R.id.imageViewNews);

        textViewContentNews.setText("\t".concat(Utility.getContentNews()));

        detailNewsViewModel.getNews().observe(this, new Observer<NewsResponse>() {
            @Override
            public void onChanged(NewsResponse newsResponse) {
                if (!newsResponse.isError()) {
                    if (!newsResponse.getData().isEmpty()) {
                        Glide.with(DetailNewsActivity.this)
                                .load(newsResponse.getData().get(0).getFoto())
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .skipMemoryCache(true)
                                .dontAnimate()
                                .dontTransform()
                                .priority(Priority.IMMEDIATE)
                                .encodeFormat(Bitmap.CompressFormat.PNG)
                                .format(DecodeFormat.DEFAULT)
                                .placeholder(R.drawable.ic_logo)
                                .into(imageViewNews);

                        textViewJudulNews.setText(newsResponse.getData().get(0).getJudulNews());

//                        String news = Utility.getContentNews().replaceAll("\\<.*?\\>" , "");


                        String nmyFormat = "dd MMMM yyyy"; //In which you need put here
                        SimpleDateFormat nsdf = new SimpleDateFormat(nmyFormat, new Locale("id", "ID"));
                        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
                        try {
                            Date date = format.parse(newsResponse.getData().get(0).getTanggalNews());
                            textViewDateNews.setText(nsdf.format(date));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
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
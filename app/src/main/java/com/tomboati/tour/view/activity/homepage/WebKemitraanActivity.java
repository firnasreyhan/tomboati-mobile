package com.tomboati.tour.view.activity.homepage;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.tomboati.tour.databinding.ActivityWebKemitraanBinding;
import com.tomboati.tour.view.activity.base.BaseToolbarActivity;

public class WebKemitraanActivity extends BaseToolbarActivity {

    private ActivityWebKemitraanBinding bind;
    private static final String TAG = "WEB KEMITRAAN ACTIVITY";

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        bind = ActivityWebKemitraanBinding.inflate(getLayoutInflater());
        setToolbar(bind.toolbar,"Web Kemitraan");
        
        bind.materialButtonMasukWebMitra.setOnClickListener(v -> {
            try {
                final String URI = "https://tomboatitour.biz/backoffice";
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setData(Uri.parse(URI));
                startActivity(sendIntent);
            }catch (Exception e) {
                Log.d(TAG, "onCreate: " + e.getMessage());
            }
        });

    }

    @Override
    protected View getContentView() {
        return bind.getRoot();
    }
}
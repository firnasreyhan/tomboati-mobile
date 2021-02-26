package com.android.tomboati.view.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.os.Build;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.view.View;
import android.widget.ZoomControls;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.android.tomboati.R;
import com.android.tomboati.adapter.TabAdapter;
import com.android.tomboati.utils.OnSwipeTouchListener;
import com.android.tomboati.utils.Utility;
import com.android.tomboati.view.fragment.BacaanSholatFragment;
import com.android.tomboati.view.fragment.NiatSholatFragment;
import com.android.tomboati.view.fragment.TahlilFragment;
import com.android.tomboati.view.fragment.YasinFragment;
import com.google.android.material.tabs.TabLayout;
import com.jsibbold.zoomage.ZoomageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class SholatWajibActivity extends AppCompatActivity {

    private TabAdapter tabAdapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_sholat_wajib);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Sholat Wajib");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if(Utility.isConnecting(this)) {

            viewPager = findViewById(R.id.viewPager);
            tabLayout = findViewById(R.id.tabLayout);

            tabAdapter = new TabAdapter(getSupportFragmentManager(), this);
            tabAdapter.addFragment(new NiatSholatFragment(), "Niat Sholat", R.drawable.ic_mosque2);
            tabAdapter.addFragment(new BacaanSholatFragment(), "Bacaan Sholat", R.drawable.ic_mosque2);

            viewPager.setAdapter(tabAdapter);
            tabLayout.setupWithViewPager(viewPager);

            highLightCurrentTab(0);

            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    highLightCurrentTab(position);
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void highLightCurrentTab(int position) {
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            assert tab != null;
            tab.setCustomView(null);
            tab.setCustomView(tabAdapter.getTabView(i));
        }
        TabLayout.Tab tab = tabLayout.getTabAt(position);
        assert tab != null;
        tab.setCustomView(null);
        tab.setCustomView(tabAdapter.getSelectedTabView(position));
    }
}
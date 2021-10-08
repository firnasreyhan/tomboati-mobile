package com.tomboati.tour.view.activity.sholat;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.tomboati.tour.R;
import com.tomboati.tour.adapter.TabAdapter;
import com.tomboati.tour.utils.Utility;
import com.tomboati.tour.view.fragment.doadzikir.BacaanSholatFragment;
import com.tomboati.tour.view.fragment.doadzikir.NiatSholatFragment;
import com.google.android.material.tabs.TabLayout;

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
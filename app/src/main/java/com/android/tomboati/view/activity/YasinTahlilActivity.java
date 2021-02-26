package com.android.tomboati.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.android.tomboati.R;
import com.android.tomboati.adapter.TabAdapter;
import com.android.tomboati.utils.Utility;
import com.android.tomboati.view.fragment.TahlilFragment;
import com.android.tomboati.view.fragment.YasinFragment;
import com.google.android.material.tabs.TabLayout;

public class YasinTahlilActivity extends AppCompatActivity {

    private TabAdapter tabAdapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_yasin_tahlil);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Yasin & Tahlil");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        if(Utility.isConnecting(this)) {

            tabAdapter = new TabAdapter(getSupportFragmentManager(), this);
            tabAdapter.addFragment(new YasinFragment(), "Surah Yasin", R.drawable.ic_quran);
            tabAdapter.addFragment(new TahlilFragment(), "Doa Tahlil", R.drawable.ic_quran);

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
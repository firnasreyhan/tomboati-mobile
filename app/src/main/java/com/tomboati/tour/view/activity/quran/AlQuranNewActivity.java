package com.tomboati.tour.view.activity.quran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import com.tomboati.tour.R;
import com.tomboati.tour.adapter.TabAdapter;
import com.tomboati.tour.view.fragment.quran.JuzNewFragment;
import com.tomboati.tour.view.fragment.quran.SurahNewFragment;
import com.google.android.material.tabs.TabLayout;

public class AlQuranNewActivity extends AppCompatActivity {

    private TabAdapter tabAdapter;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_al_quran_new);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Al Qur’an");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ViewPager viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        tabAdapter = new TabAdapter(getSupportFragmentManager(), this);
        tabAdapter.addFragment(new SurahNewFragment(), "Surah", R.drawable.ic_star);
        tabAdapter.addFragment(new JuzNewFragment(), "Juz", R.drawable.ic_star);

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
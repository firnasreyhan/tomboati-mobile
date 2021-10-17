package com.tomboati.tour.view.activity.doa_dzikir;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.tomboati.tour.R;
import com.tomboati.tour.adapter.TabAdapter;
import com.tomboati.tour.databinding.ActivityYasinTahlilBinding;
import com.tomboati.tour.utils.Utility;
import com.tomboati.tour.view.fragment.doadzikir.TahlilFragment;
import com.tomboati.tour.view.fragment.doadzikir.YasinFragment;
import com.google.android.material.tabs.TabLayout;

public class YasinTahlilActivity extends AppCompatActivity {

    private ActivityYasinTahlilBinding bind;
    private TabAdapter tabAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        bind = ActivityYasinTahlilBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        setSupportActionBar(bind.toolbar);
        setTitle("Yasin & Tahlil");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if(Utility.isConnecting(this)) {

            tabAdapter = new TabAdapter(getSupportFragmentManager(), this);
            tabAdapter.addFragment(new YasinFragment(), "Surah Yasin", R.drawable.ic_quran);
            tabAdapter.addFragment(new TahlilFragment(), "Doa Tahlil", R.drawable.ic_quran);

            bind.viewPager.setAdapter(tabAdapter);
            bind.tabLayout.setupWithViewPager(bind.viewPager);

            highLightCurrentTab(0);

            bind.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
        for (int i = 0; i < bind.tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = bind.tabLayout.getTabAt(i);
            assert tab != null;
            tab.setCustomView(null);
            tab.setCustomView(tabAdapter.getTabView(i));
        }
        TabLayout.Tab tab = bind.tabLayout.getTabAt(position);
        assert tab != null;
        tab.setCustomView(null);
        tab.setCustomView(tabAdapter.getSelectedTabView(position));
    }
}
package com.tomboati.tour.view.activity.doa_dzikir;

import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tomboati.tour.R;
import com.tomboati.tour.adapter.TabAdapter;
import com.tomboati.tour.databinding.ActivityYasinTahlilBinding;
import com.tomboati.tour.utils.Utility;
import com.tomboati.tour.view.activity.base.BaseToolbarActivity;
import com.tomboati.tour.view.fragment.doadzikir.TahlilFragment;
import com.tomboati.tour.view.fragment.doadzikir.YasinFragment;
import com.google.android.material.tabs.TabLayout;

public class YasinTahlilActivity extends BaseToolbarActivity {

    private ActivityYasinTahlilBinding bind;
    private TabAdapter tabAdapter;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        bind = ActivityYasinTahlilBinding.inflate(getLayoutInflater());
        setToolbar(bind.toolbar, "Yasin & Tahlil");

        if(Utility.isConnecting(this)) {

            tabAdapter = new TabAdapter(getSupportFragmentManager(), this);
            tabAdapter.addFragment(new YasinFragment(), "Surah Yasin", R.drawable.ic_quran);
            tabAdapter.addFragment(new TahlilFragment(), "Doa Tahlil", R.drawable.ic_quran);

            bind.viewPager.setAdapter(tabAdapter);
            bind.tabLayout.setupWithViewPager(bind.viewPager);

            highLightCurrentTab(0);

            bind.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
                @Override
                public void onPageSelected(int position) { highLightCurrentTab(position); }
                @Override
                public void onPageScrollStateChanged(int state) {}
            });
        }

    }

    @Override
    protected View getContentView() {
        return bind.getRoot();
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
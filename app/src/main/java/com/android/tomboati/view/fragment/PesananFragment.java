package com.android.tomboati.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.tomboati.R;
import com.android.tomboati.adapter.TabAdapter;
import com.android.tomboati.preference.AppPreference;
import com.google.android.material.tabs.TabLayout;

public class PesananFragment extends Fragment {

    private LinearLayout linearLayoutNoSignIn, linearLayoutYesSignIn;
    private TabAdapter tabAdapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pesanan, container, false);

        linearLayoutNoSignIn = view.findViewById(R.id.linearLayoutNoSignIn);
        linearLayoutYesSignIn = view.findViewById(R.id.linearLayoutYesSignIn);

        viewPager = view.findViewById(R.id.viewPager);
        tabLayout = view.findViewById(R.id.tabLayout);

        if (AppPreference.getUser(inflater.getContext()) != null) {
            linearLayoutNoSignIn.setVisibility(View.GONE);
            linearLayoutYesSignIn.setVisibility(View.VISIBLE);

            tabAdapter = new TabAdapter(getActivity().getSupportFragmentManager(), getContext());
            tabAdapter.addFragment(new PesananPendingFragment(), "Pending", R.drawable.ic_pending);
            tabAdapter.addFragment(new ChatFragment(), "Terbayar", R.drawable.ic_bayar);

            viewPager.setAdapter(tabAdapter);
            tabLayout.setupWithViewPager(viewPager);

            highLightCurrentTab(0);

            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

                @Override public void onPageSelected(int position) { highLightCurrentTab(position); }

                @Override public void onPageScrollStateChanged(int state) { }
            });
        } else {
            linearLayoutNoSignIn.setVisibility(View.VISIBLE);
            linearLayoutYesSignIn.setVisibility(View.GONE);
        }



        return view;
    }

    private void highLightCurrentTab(int position) {
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            assert tab != null;
            tab.setCustomView(null);
            tab.setCustomView(tabAdapter.getTabView(i));
        }TabLayout.Tab tab = tabLayout.getTabAt(position);
        assert tab != null;
        tab.setCustomView(null);
        tab.setCustomView(tabAdapter.getSelectedTabView(position));
    }
}
package com.tomboati.tour.view.fragment.homepage;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tomboati.tour.R;
import com.tomboati.tour.adapter.TabAdapter;

public class InboxFragment extends Fragment {

    private TabAdapter tabAdapter;
//    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int[] tabIcons = {
//            R.drawable.ic_chat,
            R.drawable.ic_notification
    };

    private Toolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inbox, container, false);

        viewPager = view.findViewById(R.id.viewPager);
        toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle("Chat");
//        tabLayout = view.findViewById(R.id.tabLayout);

        tabAdapter = new TabAdapter(getActivity().getSupportFragmentManager(), getContext());
//        tabAdapter.addFragment(new NotificationFragment(), "Notification", tabIcons[1]);
        tabAdapter.addFragment(new ChatFragment(), "Chat", tabIcons[0]);

        viewPager.setAdapter(tabAdapter);
//        tabLayout.setupWithViewPager(viewPager);

//        highLightCurrentTab(0);

//        viewPager.addOnPageC/

        return view;
    }

//    private void highLightCurrentTab(int position) {
//        for (int i = 0; i < tabLayout.getTabCount(); i++) {
//            TabLayout.Tab tab = tabLayout.getTabAt(i);
//            assert tab != null;
//            tab.setCustomView(null);
//            tab.setCustomView(tabAdapter.getTabView(i));
//        }TabLayout.Tab tab = tabLayout.getTabAt(position);
//        assert tab != null;
//        tab.setCustomView(null);
//        tab.setCustomView(tabAdapter.getSelectedTabView(position));
//    }
}
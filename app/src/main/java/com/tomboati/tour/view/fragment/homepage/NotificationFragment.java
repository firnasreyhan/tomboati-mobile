package com.tomboati.tour.view.fragment.homepage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tomboati.tour.R;
import com.tomboati.tour.adapter.NotifikasiAdapter;
import com.tomboati.tour.preference.AppPreference;

public class NotificationFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification, container, false);

        RecyclerView recyclerViewNotifikasi = view.findViewById(R.id.recyclerViewNotifikasi);
        recyclerViewNotifikasi.setHasFixedSize(true);
        recyclerViewNotifikasi.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewNotifikasi.setAdapter(new NotifikasiAdapter(AppPreference.getNotifData(view.getContext())));

        return view;
    }
}
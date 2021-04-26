package com.android.tomboati.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.tomboati.R;
import com.android.tomboati.adapter.NotifikasiAdapter;
import com.android.tomboati.model.NotifikasiModel;
import com.android.tomboati.preference.AppPreference;

import java.util.ArrayList;
import java.util.List;

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
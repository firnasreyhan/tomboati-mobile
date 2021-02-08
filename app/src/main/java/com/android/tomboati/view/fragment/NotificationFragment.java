package com.android.tomboati.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.tomboati.R;
import com.android.tomboati.adapter.NotifikasiAdapter;
import com.android.tomboati.model.NotifikasiModel;

import java.util.ArrayList;
import java.util.List;

public class NotificationFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification, container, false);

        List<NotifikasiModel> list = new ArrayList<>();
        list.add(new NotifikasiModel("7 Januari 2021", "Judul Notifikasi 1", "Isi Notifikasi 1"));
        list.add(new NotifikasiModel("6 Januari 2021", "Judul Notifikasi 2", "Isi Notifikasi 2"));
        list.add(new NotifikasiModel("5 Januari 2021", "Judul Notifikasi 3", "Isi Notifikasi 3"));
        list.add(new NotifikasiModel("4 Januari 2021", "Judul Notifikasi 4", "Isi Notifikasi 4"));
        list.add(new NotifikasiModel("3 Januari 2021", "Judul Notifikasi 5", "Isi Notifikasi 5"));

        RecyclerView recyclerViewNotifikasi = view.findViewById(R.id.recyclerViewNotifikasi);
        recyclerViewNotifikasi.setHasFixedSize(true);
        recyclerViewNotifikasi.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewNotifikasi.setAdapter(new NotifikasiAdapter(list));

        return view;
    }
}
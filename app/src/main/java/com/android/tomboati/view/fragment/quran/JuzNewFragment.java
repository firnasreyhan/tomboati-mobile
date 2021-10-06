package com.android.tomboati.view.fragment.quran;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.tomboati.R;
import com.android.tomboati.adapter.JuzNewAdapter;
import com.android.tomboati.utils.JuzUtilityNew;

public class JuzNewFragment extends Fragment {

    private RecyclerView recyclerViewAyatPenuh;
    private JuzNewAdapter juzNewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_juz_new, container, false);

        recyclerViewAyatPenuh = view.findViewById(R.id.recyclerViewAyatPenuh);

        recyclerViewAyatPenuh.setHasFixedSize(true);
        recyclerViewAyatPenuh.setLayoutManager(new LinearLayoutManager(getContext()));

        final JuzUtilityNew juzUtilityNew = new JuzUtilityNew();
        juzNewAdapter = new JuzNewAdapter(juzUtilityNew.getListJuz());
        recyclerViewAyatPenuh.setAdapter(juzNewAdapter);



        return view;
    }


}
package com.android.tomboati.view.fragment.homepage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.tomboati.R;
import com.android.tomboati.preference.AppPreference;

public class RiwayatFragment extends Fragment {
    private LinearLayout linearLayoutNoSignIn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_riwayat, container, false);

        linearLayoutNoSignIn = view.findViewById(R.id.linearLayoutNoSignIn);

        if (AppPreference.getUser(getContext()) != null) {
            linearLayoutNoSignIn.setVisibility(View.GONE);
        } else {
            linearLayoutNoSignIn.setVisibility(View.VISIBLE);
        }

        return view;
    }
}
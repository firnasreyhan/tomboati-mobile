package com.android.tomboati.view.fragment.kalender;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import com.android.tomboati.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class KalenderMasehiFragment extends Fragment {
    private CalendarView materialCalendarViewMasehi;
    private TextView textViewTanggalMasehi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kalender_masehi, container, false);

        materialCalendarViewMasehi = view.findViewById(R.id.materialCalendarViewMasehi);
        textViewTanggalMasehi = view.findViewById(R.id.textViewTanggalMasehi);

        materialCalendarViewMasehi.setDate(Calendar.getInstance().getTimeInMillis());

        String nmyFormat = "dd MMMM yyyy"; //In which you need put here
        SimpleDateFormat nsdf = new SimpleDateFormat(nmyFormat, new Locale("id", "ID"));
        textViewTanggalMasehi.setText(nsdf.format(Calendar.getInstance().getTime()));

        return view;
    }
}
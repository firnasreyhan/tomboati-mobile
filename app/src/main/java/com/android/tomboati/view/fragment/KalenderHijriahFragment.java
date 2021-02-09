package com.android.tomboati.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.tomboati.R;
import com.github.eltohamy.materialhijricalendarview.CalendarDay;
import com.github.eltohamy.materialhijricalendarview.MaterialHijriCalendarView;
import com.github.eltohamy.materialhijricalendarview.OnDateSelectedListener;
import com.github.eltohamy.materialhijricalendarview.OnMonthChangedListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class KalenderHijriahFragment extends Fragment {
    private static DateFormat FORMATTER = new SimpleDateFormat("MMM d, y");

    private MaterialHijriCalendarView materialHijriCalendarView;
    private TextView textViewTanggalHijriah;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kalender_hijriah, container, false);

        materialHijriCalendarView = view.findViewById(R.id.materialHijriCalendarView);
        textViewTanggalHijriah = view.findViewById(R.id.textViewTanggalHijriah);

        materialHijriCalendarView.setSelectedDate(Calendar.getInstance().getTime());
        materialHijriCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialHijriCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                Log.e("date", getSelectedDatesString());
            }
        });

        materialHijriCalendarView.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialHijriCalendarView widget, CalendarDay date) {
                //noinspection Constant Conditions
                //FORMATTER.setCalendar(date.getCalendar());
//        getSupportActionBar().setTitle(FORMATTER.format(date.getCalendar().getTime()));
//                getSupportActionBar().setTitle(date.getCalendar().getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault()) + " " + date.getCalendar().get(Calendar.DAY_OF_MONTH) + ", " + String.valueOf(date.getYear()));
            }
        });

        textViewTanggalHijriah.setText(getSelectedDatesString());

        return view;
    }

    private String getSelectedDatesString() {
        CalendarDay date = materialHijriCalendarView.getSelectedDate();
        if (date == null) {
            return "No Selection";
        }
        FORMATTER.setCalendar(date.getCalendar());
//        return FORMATTER.format(date.getCalendar().getTime());
//        return date.getCalendar().getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) + " " + date.getCalendar().get(Calendar.DAY_OF_MONTH) + ", " + String.valueOf(date.getYear());
        return date.getCalendar().get(Calendar.DAY_OF_MONTH) + " " + date.getCalendar().getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) + " " + String.valueOf(date.getYear()) + " H";
    }
}
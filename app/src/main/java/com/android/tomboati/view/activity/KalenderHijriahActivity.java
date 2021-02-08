package com.android.tomboati.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.tomboati.R;
import com.github.eltohamy.materialhijricalendarview.CalendarDay;
import com.github.eltohamy.materialhijricalendarview.MaterialHijriCalendarView;
import com.github.eltohamy.materialhijricalendarview.OnDateSelectedListener;
import com.github.eltohamy.materialhijricalendarview.OnMonthChangedListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class KalenderHijriahActivity extends AppCompatActivity {
    private static DateFormat FORMATTER = new SimpleDateFormat("MMM d, y");

    private Toolbar toolbar;
    private MaterialHijriCalendarView materialHijriCalendarView;
    private TextView textViewTanggalHijriah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_kalender_hijriah);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Kalender Hijriah");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        materialHijriCalendarView = findViewById(R.id.materialHijriCalendarView);
        textViewTanggalHijriah = findViewById(R.id.textViewTanggalHijriah);

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
                FORMATTER.setCalendar(date.getCalendar());
//        getSupportActionBar().setTitle(FORMATTER.format(date.getCalendar().getTime()));
//                getSupportActionBar().setTitle(date.getCalendar().getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault()) + " " + date.getCalendar().get(Calendar.DAY_OF_MONTH) + ", " + String.valueOf(date.getYear()));
            }
        });

        textViewTanggalHijriah.setText(getSelectedDatesString());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
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
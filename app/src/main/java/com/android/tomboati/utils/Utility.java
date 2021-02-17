package com.android.tomboati.utils;

import android.Manifest;
import android.annotation.SuppressLint;

import com.android.tomboati.api.response.JadwalSholatResponse;
import com.android.tomboati.model.TasbihModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class Utility {
    private static double latitude, longitude;
    private static final Calendar c = Calendar.getInstance();
    private static String kota;
    private static int hour, minute;
    private static ArrayList<JadwalSholatResponse> list = new ArrayList<>();
    private static List<TasbihModel> models;

    public static void addValue() {
        models = new ArrayList<>();
        models.add(new TasbihModel("Tasbih 33x", "ﺳُﺒْﺤَﺎﻥَ ﺍﻟﻠﻪْ", 33));
        models.add(new TasbihModel("Tahmid 33x", "ﻭَﺍﻟْﺤَﻤْﺪُ ﻟِﻠﻪْْ", 33));
        models.add(new TasbihModel("Takbir 33x", "ﺍﻟﻠﻪُ ﺍَﻛْﺒَﺮْْ", 33));
        models.add(new TasbihModel("Istighfar 33x", "اَسْتَغْفِرُاللهَ الْعَظِيْمَ", 33));
    }

    public static List<TasbihModel> getTasbihModel() {
        return models;
    }

    public static final String[] PERMISSION = {
            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION
    };

    public static int getYear() {
        return c.get(Calendar.YEAR);
    }

    public static int getMonth() {
        return c.get(Calendar.MONTH);
    }

    public static int getDay() {
        return c.get(Calendar.DAY_OF_MONTH);
    }

    public static int getGMT() {
        TimeZone timeZone = TimeZone.getDefault();
        return timeZone.getOffset(new Date().getTime()) / 3600000;
    }

    public static double getLatitude() {
        return latitude;
    }

    public static void setLatitude(double latitude) {
        Utility.latitude = latitude;
    }

    public static double getLongitude() {
        return longitude;
    }

    public static void setLongitude(double longitude) {
        Utility.longitude = longitude;
    }

    public static String getKota() {
        return kota;
    }

    public static void setKota(String kota) {
        Utility.kota = kota;
    }

    public static ArrayList<JadwalSholatResponse> getList() {
        return list;
    }

    public static void setList(ArrayList<JadwalSholatResponse> list) {
        Utility.list = list;
    }

    public static int getHour() {
        return c.get(Calendar.HOUR_OF_DAY);
    }

    public static int getMinute() {
        return c.get(Calendar.MINUTE);
    }
}

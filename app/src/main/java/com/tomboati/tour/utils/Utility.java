package com.tomboati.tour.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.appcompat.app.AlertDialog;

import com.tomboati.tour.api.response.JadwalSholatResponse;
import com.tomboati.tour.model.TasbihModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class Utility {
    private static double latitude, longitude;
    private static final Calendar c = Calendar.getInstance();
    private static String kota;

    private static String contentNews, shortNews;
    private static int hour, minute;
    private static ArrayList<JadwalSholatResponse> list = new ArrayList<>();
    private static List<TasbihModel> models;

    public static void addValue() {
        models = new ArrayList<>();
        models.add(new TasbihModel("Tasbih 33x", "ﺳُﺒْﺤَﺎﻥَ ﺍﻟﻠﻪْ","\"Subhanallah\"", 33));
        models.add(new TasbihModel("Tahmid 33x", "ﻭَﺍﻟْﺤَﻤْﺪُ ﻟِﻠﻪْْ","\"Walhamdulillah\"", 33));
        models.add(new TasbihModel("Takbir 33x", "ﺍﻟﻠﻪُ ﺍَﻛْﺒَﺮْْ","\"Allahu Akbar\"", 33));
        models.add(new TasbihModel("Istighfar 33x", "اَسْتَغْفِرُاللهَ الْعَظِيْمَ", "\"Astaghfirullahaladzim\"",33));
        models.add(new TasbihModel("Tahlil 33x", "لا إلهَ إِلاَّ اللهُ", "\"Lailaha Ilallah\"", 33));
        models.add(new TasbihModel("Sholawat 33x", "صَلَّى اللهُ عَلَى مُحَمَّدُ", "\"Shalallahuala Muhammad\"",33));
    }

    public static String getContentNews() {
        return contentNews;
    }
    public static String getShortNews() {
        return shortNews;
    }

    public static void setContentNews(String contentNews) {
        Utility.contentNews = contentNews;
    }
    public static void setShortNews(String shortNews) {
        Utility.shortNews = shortNews;
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

    public static void setHour(int hour) {
        Utility.hour = hour;
    }

    public static int getMinute() {
        return c.get(Calendar.MINUTE);
    }

    public static void setMinute(int minute) {
        Utility.minute = minute;
    }

    public static boolean isConnecting(Activity activity) {
        ConnectivityManager connectivityManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            AlertDialog.Builder alert = new AlertDialog.Builder(activity);
            alert.setCancelable(false);
            alert.setMessage("Perangkat anda tidak terhubung dengan internet!!");
            alert.setTitle("Peringatan!!!");
            alert.setPositiveButton("Kembali", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    activity.onBackPressed();
                }
            });
            alert.create();
            alert.show();
            return false;
        }
    }

}

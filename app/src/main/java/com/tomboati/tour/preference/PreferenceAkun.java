package com.tomboati.tour.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.tomboati.tour.model.AkunModel;
import com.google.gson.Gson;

public class PreferenceAkun {

    private static final String KEY_AKUN = "AKUN";
    private static final String KEY_TOKEN = "TOKEN";

    private static SharedPreferences getSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    private static SharedPreferences.Editor getPreferenceEditor(Context context) {
        return getSharedPreferences(context).edit();
    }

    public static void setAkun(Context context, AkunModel akunModel) {
        SharedPreferences.Editor editor = getPreferenceEditor(context);
        editor.putString(KEY_AKUN, new Gson().toJson(akunModel)).apply();
        editor.apply();
    }

    public static AkunModel getAkun(Context context) {
        return new Gson().fromJson(
                getSharedPreferences(context).getString(KEY_AKUN, ""),
                AkunModel.class
        );
    }

    public static void removeAkun(Context context) {
        SharedPreferences.Editor editor = getPreferenceEditor(context);
        editor.remove(KEY_AKUN);
        editor.apply();
    }

    public static void setToken(Context context, String token) {
        SharedPreferences.Editor editor = getPreferenceEditor(context);
        editor.putString(KEY_TOKEN, token).apply();
        editor.apply();
    }

    public static String getToken(Context context) {
        return getSharedPreferences(context).getString(KEY_TOKEN, "");
    }

}

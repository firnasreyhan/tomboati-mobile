package com.tomboati.tour.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.tomboati.tour.api.response.SignInResponse;
import com.tomboati.tour.model.NotifikasiModel;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AppPreference {
    static final String PREF = "PREF";
    static final String PREF_NOTIF = "PREF_NOTIF";
    static final String USER_PREF = "USER_PREF";
    static final String NOTIF_PREF = "NOTIF_PREF";
    static List<NotifikasiModel.DataItem> data = new ArrayList<>();

    public static void saveUser(Context context, SignInResponse.SignInModel signInModel){
        context.getSharedPreferences(PREF, Context.MODE_PRIVATE).edit().putString(USER_PREF, new Gson().toJson(signInModel)).apply();
    }

    public static void saveNotif(Context context, String tanggal, String judul, String isi) {
        if(getNotifData(context) != null) {
            data = getNotifData(context).getData();
        }
        data.add(new NotifikasiModel.DataItem(tanggal, judul, isi));
        JSONObject object = new JSONObject();
        JSONArray dataArray = new JSONArray();
        try {
            for (int i = 0; i < data.size(); i++) {
                JSONObject property = new JSONObject();
                property.put("tanggal:", data.get(i).getTanggal());
                property.put("judul:", data.get(i).getJudul());
                property.put("isi:", data.get(i).getIsi());
                dataArray.put(property);
            }
            object.put("data", dataArray);
        } catch (Exception e) {
            Log.d("Error JSON Parsing", e.getMessage());
        }
        Log.d("==============", object.toString());
        context.getSharedPreferences(PREF_NOTIF, Context.MODE_PRIVATE).edit().putString(NOTIF_PREF, object.toString()).apply();
    }

//    public static SignInResponse.SignInModel getUser(Context context){
//        SharedPreferences pref = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
//        if(pref.contains(USER_PREF)){
//            return new Gson().fromJson(pref.getString(USER_PREF, ""), SignInResponse.SignInModel.class);
//        }
//        return null;
//    }

    public static NotifikasiModel getNotifData(Context context){
        SharedPreferences pref = context.getSharedPreferences(PREF_NOTIF, Context.MODE_PRIVATE);
        if(pref.contains(NOTIF_PREF)){
            return new Gson().fromJson(pref.getString(NOTIF_PREF, ""), NotifikasiModel.class);
        }
        return null;
    }

    public static void removeUser(Context context){
        SharedPreferences pref = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        if(pref.contains(USER_PREF)){
            pref.edit().remove(USER_PREF).apply();
        }
    }

    public static void removeNotif(Context context){
        SharedPreferences pref = context.getSharedPreferences(PREF_NOTIF, Context.MODE_PRIVATE);
        if(pref.contains(NOTIF_PREF)){
            pref.edit().remove(NOTIF_PREF).apply();
        }
    }
}

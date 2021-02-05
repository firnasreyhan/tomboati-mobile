package com.android.tomboati.api.response;

import com.google.gson.annotations.SerializedName;

public class AyatResponse {
    @SerializedName("ar")
    private String ar;

    @SerializedName("id")
    private String id;

    @SerializedName("nomor")
    private String nomor;

    @SerializedName("tr")
    private String tr;

    public String getAr() {
        return ar;
    }

    public String getId() {
        return id;
    }

    public String getNomor() {
        return nomor;
    }

    public String getTr() {
        return tr;
    }
}

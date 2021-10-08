package com.tomboati.tour.api.response;

import com.google.gson.annotations.SerializedName;

public class LokasiResponse {
    @SerializedName("id")
    private String id;

    @SerializedName("nama")
    private String nama;

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    @Override
    public String toString() {
        return nama;
    }
}

package com.android.tomboati.api.response;

import com.google.gson.annotations.SerializedName;

public class SurahResponse {
    @SerializedName("arti")
    private String arti;

    @SerializedName("asma")
    private String asma;

    @SerializedName("audio")
    private String audio;

    @SerializedName("ayat")
    private String ayat;

    @SerializedName("keterangan")
    private String keterangan;

    @SerializedName("nama")
    private String nama;

    @SerializedName("nomor")
    private String nomor;

    @SerializedName("rukuk")
    private String rukuk;

    @SerializedName("type")
    private String type;

    @SerializedName("urut")
    private String urut;

    public String getArti() {
        return arti;
    }

    public String getAsma() {
        return asma;
    }

    public String getAudio() {
        return audio;
    }

    public String getAyat() {
        return ayat;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public String getNama() {
        return nama;
    }

    public String getNomor() {
        return nomor;
    }

    public String getRukuk() {
        return rukuk;
    }

    public String getType() {
        return type;
    }

    public String getUrut() {
        return urut;
    }
}

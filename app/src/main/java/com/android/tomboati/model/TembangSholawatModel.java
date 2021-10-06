package com.android.tomboati.model;

public class TembangSholawatModel {

    private String judul_tembang, url_tembang, duration;

    public TembangSholawatModel() {
    }

    public TembangSholawatModel(String judul_tembang, String url_tembang, String duration) {
        this.judul_tembang = judul_tembang;
        this.duration = duration;
        this.url_tembang = url_tembang;
    }

    public String getJudulTembang() {
        return judul_tembang;
    }

    public void setJudulTembang(String judul_tembang) {
        this.judul_tembang = judul_tembang;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getUrltembang() {
        return url_tembang;
    }

    public void setUrltembang(String url_tembang) {
        this.url_tembang = url_tembang;
    }
}

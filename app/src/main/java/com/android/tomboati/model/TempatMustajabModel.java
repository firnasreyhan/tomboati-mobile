package com.android.tomboati.model;

public class TempatMustajabModel {

    private String judul, keterangan, link;
    private int drawable;

    public TempatMustajabModel() {
    }

    public TempatMustajabModel(String judul, String keterangan, String link, int drawable) {
        this.judul = judul;
        this.keterangan = keterangan;
        this.link = link;
        this.drawable = drawable;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }
}

package com.tomboati.tour.model;

import java.io.Serializable;

public class DoaModel implements Serializable {

    private String judul = "";
    private String arabic = "";
    private String translate = "";
    private String arti = "";
    private String keterangan = "";

    public DoaModel(String judul, String arabic, String translate, String arti, String keterangan) {
        this.judul = judul;
        this.arabic = arabic;
        this.translate = translate;
        this.arti = arti;
        this.keterangan = keterangan;
    }

    public boolean isKeteranganActive() {
        return !this.keterangan.isEmpty();
    }

    public String getJudul() {
        return judul;
    }

    public String getArabic() {
        return arabic;
    }

    public String getTranslate() {
        return translate;
    }

    public String getArti() {
        return arti;
    }

    public String getKeterangan() {
        return keterangan;
    }
}

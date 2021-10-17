package com.tomboati.tour.model;

public class DoaModel {

    private String judul = "";
    private String arabic = "";
    private String translate = "";
    private String arti = "";
    private String keterangan = "";
    private boolean keteranganActive = false;

    public DoaModel(String judul, String arabic, String translate, String arti, String keterangan, boolean keteranganActive) {
        this.judul = judul;
        this.arabic = arabic;
        this.translate = translate;
        this.arti = arti;
        this.keterangan = keterangan;
        this.keteranganActive = keteranganActive;
    }

    public boolean isKeteranganActive() {
        return keteranganActive;
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

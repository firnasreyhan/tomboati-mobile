package com.tomboati.tour.model;

public class DoaHajiUmrahModel {

    private String title, arabic, latin, translation, keterangan;

    public DoaHajiUmrahModel() {
    }

    public DoaHajiUmrahModel(String title, String arabic, String latin, String translation, String keterangan) {
        this.title = title;
        this.arabic = arabic;
        this.latin = latin;
        this.translation = translation;
        this.keterangan = keterangan;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArabic() {
        return arabic;
    }

    public void setArabic(String arabic) {
        this.arabic = arabic;
    }

    public String getLatin() {
        return latin;
    }

    public void setLatin(String latin) {
        this.latin = latin;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}

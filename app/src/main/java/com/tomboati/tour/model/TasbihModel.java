package com.tomboati.tour.model;

public class TasbihModel {

    private String arabic, judul, translate;
    private int count_tasbeeh = 0, max  = 0;

    public TasbihModel(String judul) {
        this.judul = judul;
    }

    public TasbihModel(String judul, String arabic, String translate, int max) {
        this.arabic = arabic;
        this.judul = judul;
        this.translate = translate;
        this.max = max;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    public String getArabic() {
        return arabic;
    }

    public void setArabic(String arabic) {
        this.arabic = arabic;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public int getCount_tasbeeh() {
        return count_tasbeeh;
    }

    public void setCount_tasbeeh(int count_tasbeeh) {
        this.count_tasbeeh = count_tasbeeh;
    }
}

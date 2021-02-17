package com.android.tomboati.model;

public class TasbihModel {

    private String arabic, judul;
    private int count = 0, count_tasbeeh = 0, max  = 0;

    public TasbihModel() {
    }

    public TasbihModel(String judul, String arabic, int max) {
        this.arabic = arabic;
        this.judul = judul;
        this.max = max;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount_tasbeeh() {
        return count_tasbeeh;
    }

    public void setCount_tasbeeh(int count_tasbeeh) {
        this.count_tasbeeh = count_tasbeeh;
    }
}

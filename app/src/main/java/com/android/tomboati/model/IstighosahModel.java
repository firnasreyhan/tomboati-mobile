package com.android.tomboati.model;

public class IstighosahModel {

    private String textArab, textTranslate;
    private int countBacaan;

    public IstighosahModel() {
    }

    public IstighosahModel(String textArab, String textTranslate, int countBacaan) {
        this.textArab = textArab;
        this.textTranslate = textTranslate;
        this.countBacaan = countBacaan;
    }

    public String getTextArab() {
        return this.textArab;
    }

    public void setTextArab(String textArab) {
        this.textArab = textArab;
    }

    public String getTextTranslate() {
        return this.textTranslate;
    }

    public void setTextTranslate(String textTranslate) {
        this.textTranslate = textTranslate;
    }

    public int getCountBacaan() {
        return this.countBacaan;
    }

    public void setCountBacaan(int countBacaan) {
        this.countBacaan = countBacaan;
    }
}

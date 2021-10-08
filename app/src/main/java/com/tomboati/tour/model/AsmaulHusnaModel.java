package com.tomboati.tour.model;

public class AsmaulHusnaModel {
    private String nomor;
    private String arab;
    private String latin;
    private String terjemahan;

    public AsmaulHusnaModel(String nomor, String arab, String latin, String terjemahan) {
        this.nomor = nomor;
        this.arab = arab;
        this.latin = latin;
        this.terjemahan = terjemahan;
    }

    public String getNomor() {
        return nomor;
    }

    public String getArab() {
        return arab;
    }

    public String getLatin() {
        return latin;
    }

    public String getTerjemahan() {
        return terjemahan;
    }
}

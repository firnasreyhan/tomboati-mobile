package com.android.tomboati.model;

public class WudhuModel {
    private String nomor;
    private String keterangan;
    private String arab;
    private String latin;
    private String terjemahan;

    public WudhuModel(String nomor, String keterangan, String arab, String latin, String terjemahan) {
        this.nomor = nomor;
        this.keterangan = keterangan;
        this.arab = arab;
        this.latin = latin;
        this.terjemahan = terjemahan;
    }

    public String getNomor() {
        return nomor;
    }

    public String getKeterangan() {
        return keterangan;
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

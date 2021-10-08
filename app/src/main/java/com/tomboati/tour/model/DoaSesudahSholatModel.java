package com.tomboati.tour.model;

public class DoaSesudahSholatModel {
    private String nomor;
    private String arab;
    private String keterangan;

    public DoaSesudahSholatModel(String nomor, String arab, String keterangan) {
        this.nomor = nomor;
        this.arab = arab;
        this.keterangan = keterangan;
    }

    public String getNomor() {
        return nomor;
    }

    public String getArab() {
        return arab;
    }

    public String getKeterangan() {
        return keterangan;
    }
}

package com.android.tomboati.model;

public class NotifikasiModel {
    private String tanggal;
    private String judul;
    private String isi;

    public NotifikasiModel(String tanggal, String judul, String isi) {
        this.tanggal = tanggal;
        this.judul = judul;
        this.isi = isi;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getJudul() {
        return judul;
    }

    public String getIsi() {
        return isi;
    }
}

package com.tomboati.tour.model;

public class BulanModel {
    private String urutan;
    private String bulan;

    public BulanModel(String urutan, String bulan) {
        this.urutan = urutan;
        this.bulan = bulan;
    }

    public String getUrutan() {
        return urutan;
    }

    public void setUrutan(String urutan) {
        this.urutan = urutan;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    @Override
    public String toString() {
        return bulan;
    }
}

package com.tomboati.tour.model;

public class NewsModel {

    private String judul = "";
    private String date = "";
    private String news = "";

    public NewsModel(String judul, String date, String news) {
        this.judul = judul;
        this.date = date;
        this.news = news;
    }

    public String getJudul() {
        return judul;
    }

    public String getDate() {
        return date;
    }

    public String getNews() {
        return news;
    }
}

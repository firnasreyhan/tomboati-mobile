package com.tomboati.tour.model;

import java.io.Serializable;

public class NewsModel implements Serializable {

    private String judul = "";
    private String date = "";
    private String news = "";
    private String image = "";

    public NewsModel(String judul, String date, String news, String image) {
        this.judul = judul;
        this.date = date;
        this.news = news;
        this.image = image;
    }

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

    public String getImage() {
        return image;
    }
}

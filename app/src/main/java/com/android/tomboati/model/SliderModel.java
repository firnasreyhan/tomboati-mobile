package com.android.tomboati.model;

public class SliderModel {
    private String date;
    private String hijriDate;
    private String location;
    private String nameTime;
    private String time;
    private String nameNextTime;
    private String nextTime;

    public SliderModel(String date, String hijriDate, String location, String nameTime, String time, String nameNextTime, String nextTime) {
        this.date = date;
        this.hijriDate = hijriDate;
        this.location = location;
        this.nameTime = nameTime;
        this.time = time;
        this.nameNextTime = nameNextTime;
        this.nextTime = nextTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHijriDate() {
        return hijriDate;
    }

    public void setHijriDate(String hijriDate) {
        this.hijriDate = hijriDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNameTime() {
        return nameTime;
    }

    public void setNameTime(String nameTime) {
        this.nameTime = nameTime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNameNextTime() {
        return nameNextTime;
    }

    public void setNameNextTime(String nameNextTime) {
        this.nameNextTime = nameNextTime;
    }

    public String getNextTime() {
        return nextTime;
    }

    public void setNextTime(String nextTime) {
        this.nextTime = nextTime;
    }
}

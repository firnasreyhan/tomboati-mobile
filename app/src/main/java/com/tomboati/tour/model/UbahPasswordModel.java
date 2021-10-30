package com.tomboati.tour.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class UbahPasswordModel extends BaseObservable {

    String passwordNow = "", passwordNew = "", passwordNewRepeat = "";

    public UbahPasswordModel() {
    }

    @Bindable
    public String getPasswordNow() {
        return passwordNow;
    }

    public void setPasswordNow(String passwordNow) {
        this.passwordNow = passwordNow;
    }

    @Bindable
    public String getPasswordNew() {
        return passwordNew;
    }

    public void setPasswordNew(String passwordNew) {
        this.passwordNew = passwordNew;
    }

    @Bindable
    public String getPasswordNewRepeat() {
        return passwordNewRepeat;
    }

    public void setPasswordNewRepeat(String passwordNewRepeat) {
        this.passwordNewRepeat = passwordNewRepeat;
    }
}

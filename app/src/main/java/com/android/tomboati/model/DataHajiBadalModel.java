package com.android.tomboati.model;

import java.io.Serializable;

public class DataHajiBadalModel  implements Serializable {

    private String idUserRegister;
    private String idPaket;
    private String fcKTPAlmarhum;
    private String fcKKAlmarhum;
    private String fcFotoAlmarhum;

    public DataHajiBadalModel() {
    }

    public String getIdUserRegister() {
        return idUserRegister;
    }

    public void setIdUserRegister(String idUserRegister) {
        this.idUserRegister = idUserRegister;
    }

    public String getIdPaket() {
        return idPaket;
    }

    public void setIdPaket(String idPaket) {
        this.idPaket = idPaket;
    }

    public String getFcKTPAlmarhum() {
        return fcKTPAlmarhum;
    }

    public void setFcKTPAlmarhum(String fcKTPAlmarhum) {
        this.fcKTPAlmarhum = fcKTPAlmarhum;
    }

    public String getFcKKAlmarhum() {
        return fcKKAlmarhum;
    }

    public void setFcKKAlmarhum(String fcKKAlmarhum) {
        this.fcKKAlmarhum = fcKKAlmarhum;
    }

    public String getFcFotoAlmarhum() {
        return fcFotoAlmarhum;
    }

    public void setFcFotoAlmarhum(String fcFotoAlmarhum) {
        this.fcFotoAlmarhum = fcFotoAlmarhum;
    }

}

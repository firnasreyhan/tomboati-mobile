package com.tomboati.tour.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class KomunitasResponse {

    @SerializedName("error")
    @Expose
    private Boolean error = false;

    @SerializedName("message")
    @Expose
    private String Message = null;

    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    public Boolean getError() {
        return error;
    }

    public String getMessage() {
        return Message;
    }

    public List<Datum> getData() {
        return data;
    }

    public static class Datum {

        @SerializedName("IDKOMUNITASINFO")
        @Expose
        private String iDKOMUNITASINFO;
        @SerializedName("JUDULNEWS")
        @Expose
        private String jUDULNEWS;
        @SerializedName("DESKRIPSINEWS")
        @Expose
        private String dESKRIPSINEWS;
        @SerializedName("CONTENTNEWS")
        @Expose
        private String cONTENTNEWS;
        @SerializedName("FOTO")
        @Expose
        private String fOTO;
        @SerializedName("TANGGALNEWS")
        @Expose
        private String tANGGALNEWS;

        public String getIDKOMUNITASINFO() {
            return iDKOMUNITASINFO;
        }

        public String getJUDULNEWS() {
            return jUDULNEWS;
        }

        public String getDESKRIPSINEWS() {
            return dESKRIPSINEWS;
        }

        public String getCONTENTNEWS() {
            return cONTENTNEWS;
        }

        public String getFOTO() {
            return fOTO;
        }

        public String getTANGGALNEWS() {
            return tANGGALNEWS;
        }

    }

}
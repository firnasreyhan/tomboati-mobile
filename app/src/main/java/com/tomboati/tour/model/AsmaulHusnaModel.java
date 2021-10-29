package com.tomboati.tour.model;

import java.io.Serializable;

public class AsmaulHusnaModel implements Serializable {
    private String nomor, arab, latin, terjemahan, keterangan, meneladani;

    public AsmaulHusnaModel(String nomor, String arab, String latin, String terjemahan, String keterangan, String meneladani) {
        this.nomor = nomor;
        this.arab = arab;
        this.latin = latin;
        this.terjemahan = terjemahan;
        this.keterangan = keterangan;
        this.meneladani = meneladani;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public String getMeneladani() {
        return meneladani;
    }

    public String getNomor() {
        return nomor;
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

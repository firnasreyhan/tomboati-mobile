package com.android.tomboati.api.response;

import com.google.gson.annotations.SerializedName;

public class SurahResponse {
    @SerializedName("nomor")
    private String nomor;

    @SerializedName("nama")
    private String nama;

    @SerializedName("nama_latin")
    private String namaLatin;

    @SerializedName("jumlah_ayat")
    private String jumlahAyat;

    @SerializedName("tempat_turun")
    private String tempatTurun;

    @SerializedName("arti")
    private String arti;

    @SerializedName("deskripsi")
    private String deskripsi;

    @SerializedName("audio")
    private String audio;

    public String getNomor() {
        return nomor;
    }

    public String getNama() {
        return nama;
    }

    public String getNamaLatin() {
        return namaLatin;
    }

    public String getJumlahAyat() {
        return jumlahAyat;
    }

    public String getTempatTurun() {
        return tempatTurun;
    }

    public String getArti() {
        return arti;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getAudio() {
        return audio;
    }
}

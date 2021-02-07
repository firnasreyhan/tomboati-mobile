package com.android.tomboati.api.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AyatResponse extends SurahResponse{
    @SerializedName("status")
    private boolean status;

    @SerializedName("ayat")
    private List<AyatModel> ayat;

    public boolean isStatus() {
        return status;
    }

    public List<AyatModel> getAyat() {
        return ayat;
    }

    public static class AyatModel {
        @SerializedName("id")
        private String id;

        @SerializedName("surah")
        private String surah;

        @SerializedName("nomor")
        private String nomor;

        @SerializedName("ar")
        private String ar;

        @SerializedName("tr")
        private String tr;

        @SerializedName("idn")
        private String idn;

        public String getId() {
            return id;
        }

        public String getSurah() {
            return surah;
        }

        public String getNomor() {
            return nomor;
        }

        public String getAr() {
            return ar;
        }

        public String getTr() {
            return tr;
        }

        public String getIdn() {
            return idn;
        }
    }
}

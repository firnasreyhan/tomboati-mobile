package com.tomboati.tour.api.response;

import com.google.gson.annotations.SerializedName;

import java.util.Comparator;
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
        private int id;

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

        public int getId() {
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

        public static Comparator<AyatModel> sortAyat = new Comparator<AyatModel>() {
            @Override
            public int compare(AyatModel o1, AyatModel o2) {
                int idNo1 = o1.getId();
                int idNo2 = o2.getId();

                /*For ascending order*/
                return idNo1-idNo2;
            }
        };
    }
}

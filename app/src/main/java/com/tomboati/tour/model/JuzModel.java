package com.tomboati.tour.model;

import java.util.List;

public class JuzModel {
    private String juz;
    private String namaSurah;
    private String ayatPertama;
    private String asma;
    private List<JuzDetailModel> list;

    public JuzModel(String juz, String namaSurah, String ayatPertama, String asma, List<JuzDetailModel> list) {
        this.juz = juz;
        this.namaSurah = namaSurah;
        this.ayatPertama = ayatPertama;
        this.asma = asma;
        this.list = list;
    }

    public String getJuz() {
        return juz;
    }

    public String getNamaSurah() {
        return namaSurah;
    }

    public String getAyatPertama() {
        return ayatPertama;
    }

    public String getAsma() {
        return asma;
    }

    public List<JuzDetailModel> getList() {
        return list;
    }

    public static class JuzDetailModel {
        private String nomorSurah;
        private int awalAyat;
        private int akhirAyat;

        public JuzDetailModel(String nomorSurah, int awalAyat, int akhirAyat) {
            this.nomorSurah = nomorSurah;
            this.awalAyat = awalAyat;
            this.akhirAyat = akhirAyat;
        }

        public String getNomorSurah() {
            return nomorSurah;
        }

        public int getAwalAyat() {
            return awalAyat;
        }

        public int getAkhirAyat() {
            return akhirAyat;
        }
    }
}

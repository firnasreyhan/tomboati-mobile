package com.tomboati.tour.model;

public class JuzModelNew {

    private String keterangan;
    private ListSurah[] listSurahs;

    public JuzModelNew(String keterangan, ListSurah[] listSurahs) {
        this.keterangan = keterangan;
        this.listSurahs = listSurahs;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public ListSurah[] getListSurahs() {
        return listSurahs;
    }

    public void setListSurahs(ListSurah[] listSurahs) {
        this.listSurahs = listSurahs;
    }

    public static class ListSurah {
        private int nomorSurah, ayatAwal = 0, ayatAkhir = 0;

        public ListSurah(int nomorSurah, int ayatAwal, int ayatAkhir) {
            this.nomorSurah = nomorSurah;
            this.ayatAwal = ayatAwal;
            this.ayatAkhir = ayatAkhir;
        }

        public ListSurah(int nomorSurah) {
            this.nomorSurah = nomorSurah;
        }

        public boolean isFullSurah() {
            return ayatAwal == 0 && ayatAkhir == 0;
        }

        public int getNomorSurah() {
            return nomorSurah;
        }

        public void setNomorSurah(int nomorSurah) {
            this.nomorSurah = nomorSurah;
        }

        public int getAyatAwal() {
            return ayatAwal;
        }

        public void setAyatAwal(int ayatAwal) {
            this.ayatAwal = ayatAwal;
        }

        public int getAyatAkhir() {
            return ayatAkhir;
        }

        public void setAyatAkhir(int ayatAkhir) {
            this.ayatAkhir = ayatAkhir;
        }
    }

    public static class AyatModel{
        private int banyakAyat, nomorSurah;
        private String namaAyat;

        public int getBanyakAyat() {
            return banyakAyat;
        }

        public void setBanyakAyat(int banyakAyat) {
            this.banyakAyat = banyakAyat;
        }

        public String getNamaAyat() {
            return namaAyat;
        }

        public void setNamaAyat(String namaAyat) {
            this.namaAyat = namaAyat;
        }

        public int getNomorSurah() {
            return nomorSurah;
        }

        public void setNomorSurah(int nomorSurah) {
            this.nomorSurah = nomorSurah;
        }
    }

}

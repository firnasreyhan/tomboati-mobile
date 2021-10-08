package com.tomboati.tour.api.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PaketResponse extends BaseResponse {
    @SerializedName("data")
    private List<PaketModel> data;

    public List<PaketModel> getData() {
        return data;
    }

    public static class PaketModel {
        @SerializedName("IDPAKET")
        private String idPaket;
        @SerializedName("IDMASKAPAI")
        private String idMaskapai;
        @SerializedName("IDMASTERPAKET")
        private String idMasterPaket;
        @SerializedName("NAMAPAKET")
        private String namaPaket;
        @SerializedName("DURASIPAKET")
        private String durasiPaket;
        @SerializedName("RATINGHOTEL")
        private String ratingHotel;
        @SerializedName("PENERBANGAN")
        private String penerbangan;
        @SerializedName("TANGGALKEBERANGKATAN")
        private String tanggalKeberangkatan;
        @SerializedName("NAMAHOTELA")
        private String namaHotelA;
        @SerializedName("NAMAHOTELB")
        private String namaHotelB;
        @SerializedName("TEMPATHOTELA")
        private String tempatHotelA;
        @SerializedName("TEMPATHOTELB")
        private String tempatHotelB;
        @SerializedName("DOUBLESHEET")
        private double doubleSheet;
        @SerializedName("TRIPLESHEET")
        private double tripleSheet;
        @SerializedName("QUADSHEET")
        private double quadSheet;
        @SerializedName("BIAYASUDAHTERMASUK")
        private String biayaSudahTermasuk;
        @SerializedName("BIAYABELUMTERMASUK")
        private String biayaBelumTermasuk;
        @SerializedName("KUOTA")
        private String kuota;
        @SerializedName("IMAGEPAKET")
        private String imagePaket;
        @SerializedName("ISSHOW")
        private String isShow;
        @SerializedName("CREATED_AT")
        private Object createdAt;
        @SerializedName("UPDATED_AT")
        private Object updatedAt;
        @SerializedName("NAMAMASKAPAI")
        private String namaMaskapai;
        @SerializedName("IMAGEMASKAPAI")
        private String imageMaskapai;

        public String getIdPaket() {
            return idPaket;
        }

        public String getIdMaskapai() {
            return idMaskapai;
        }

        public String getIdMasterPaket() {
            return idMasterPaket;
        }

        public String getNamaPaket() {
            return namaPaket;
        }

        public String getDurasiPaket() {
            return durasiPaket;
        }

        public String getRatingHotel() {
            return ratingHotel;
        }

        public String getPenerbangan() {
            return penerbangan;
        }

        public String getTanggalKeberangkatan() {
            return tanggalKeberangkatan;
        }

        public String getNamaHotelA() {
            return namaHotelA;
        }

        public String getNamaHotelB() {
            return namaHotelB;
        }

        public String getTempatHotelA() {
            return tempatHotelA;
        }

        public String getTempatHotelB() {
            return tempatHotelB;
        }

        public double getDoubleSheet() {
            return doubleSheet;
        }

        public double getTripleSheet() {
            return tripleSheet;
        }

        public double getQuadSheet() {
            return quadSheet;
        }

        public String getBiayaSudahTermasuk() {
            return biayaSudahTermasuk;
        }

        public String getBiayaBelumTermasuk() {
            return biayaBelumTermasuk;
        }

        public String getKuota() {
            return kuota;
        }

        public String getImagePaket() {
            return imagePaket;
        }

        public String getIsShow() {
            return isShow;
        }

        public Object getCreatedAt() {
            return createdAt;
        }

        public Object getUpdatedAt() {
            return updatedAt;
        }

        public String getNamaMaskapai() {
            return namaMaskapai;
        }

        public String getImageMaskapai() {
            return imageMaskapai;
        }
    }
}

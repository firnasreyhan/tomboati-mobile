package com.android.tomboati.api.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PaketWisataResponse extends BaseResponse {
    @SerializedName("data")
    private List<PaketModel> data;

    public List<PaketModel> getData() {
        return data;
    }

    public static class PaketModel {
        @SerializedName("IDWISATAHALAL")
        private String idWisataHalal;
        @SerializedName("IDMASKAPAI")
        private String idMaskapai;
        @SerializedName("TIPEWISATA")
        private String tipeWisata;
        @SerializedName("NAMAWISATA")
        private String namaWisata;
        @SerializedName("DURASIWISATA")
        private String durasiWisata;
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
        @SerializedName("IMAGEWISATA")
        private String imageWisata;
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

        public String getIdWisataHalal() {
            return idWisataHalal;
        }

        public String getIdMaskapai() {
            return idMaskapai;
        }

        public String getTipeWisata() {
            return tipeWisata;
        }

        public String getNamaWisata() {
            return namaWisata;
        }

        public String getDurasiWisata() {
            return durasiWisata;
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

        public String getImageWisata() {
            return imageWisata;
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

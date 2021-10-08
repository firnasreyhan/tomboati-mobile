package com.tomboati.tour.api.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItteneraryResponse extends BaseResponse {
    @SerializedName("data")
    private List<ItteneraryModel> data;

    public List<ItteneraryModel> getData() {
        return data;
    }

    public static class ItteneraryModel {
        @SerializedName("IDDETAILITENERARY")
        private String idDetailIttenerary;

        @SerializedName("IDPAKET")
        private String idPaket;

        @SerializedName("IDWISATAHALAL")
        private String idWisataHalal;

        @SerializedName("HARIKE")
        private String hariKe;

        @SerializedName("TEMPAT")
        private String tempat;

        @SerializedName("DETAILKEGIATAN")
        private String detailKegiatan;

        @SerializedName("CREATED_AT")
        private String createdAt;

        public String getIdDetailIttenerary() {
            return idDetailIttenerary;
        }

        public String getIdPaket() {
            return idPaket;
        }

        public String getIdWisataHalal() {
            return idWisataHalal;
        }

        public String getHariKe() {
            return hariKe;
        }

        public String getTempat() {
            return tempat;
        }

        public String getDetailKegiatan() {
            return detailKegiatan;
        }

        public String getCreatedAt() {
            return createdAt;
        }
    }
}

package com.android.tomboati.api.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class KataMutiaraResponse extends BaseResponse{
    @SerializedName("data")
    private List<KataMutiaraModel> data;

    public List<KataMutiaraModel> getData() {
        return data;
    }

    public static class KataMutiaraModel {
        @SerializedName("IDKATAMUTIARA")
        private String idKataMutiara;

        @SerializedName("TEKSKATAMUTIARA")
        private String teksKataMutiara;

        @SerializedName("WAKTU")
        private String waktu;

        public String getIdKataMutiara() {
            return idKataMutiara;
        }

        public String getTeksKataMutiara() {
            return teksKataMutiara;
        }

        public String getWaktu() {
            return waktu;
        }
    }
}

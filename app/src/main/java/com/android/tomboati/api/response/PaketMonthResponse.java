package com.android.tomboati.api.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PaketMonthResponse extends BaseResponse {
    @SerializedName("data")
    private PaketMonthModel data;

    public PaketMonthModel getData() {
        return data;
    }

    public static class PaketMonthModel {
        @SerializedName("bulan")
        private List<String> bulan;

        public List<String> getBulan() {
            return bulan;
        }
    }
}

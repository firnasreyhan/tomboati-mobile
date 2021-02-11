package com.android.tomboati.api.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TahlilResponse {

    @SerializedName("data")
    @Expose
    private List<Datum> data;

    public List<Datum> getData() {
        return data;
    }

    public static class Datum {

        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("arabic")
        @Expose
        private String arabic;
        @SerializedName("translation")
        @Expose
        private String translation;

        public String getTitle() {
            return title;
        }

        public String getArabic() {
            return arabic;
        }

        public String getTranslation() {
            return translation;
        }

    }

}
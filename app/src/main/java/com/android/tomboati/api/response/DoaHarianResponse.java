package com.android.tomboati.api.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DoaHarianResponse {

    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

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
        @SerializedName("latin")
        @Expose
        private String latin;
        @SerializedName("translation")
        @Expose
        private String translation;

        public Datum(String title, String arabic, String latin, String translation) {
            this.title = title;
            this.arabic = arabic;
            this.latin = latin;
            this.translation = translation;
        }

        public String getTitle() {
            return title;
        }

        public String getArabic() {
            return arabic;
        }

        public String getLatin() {
            return latin;
        }

        public String getTranslation() {
            return translation;
        }

    }

}
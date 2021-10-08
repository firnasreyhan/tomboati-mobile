package com.tomboati.tour.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuranListResponse {

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    public Integer getCode() {
        return code;
    }

    public List<Datum> getData() {
        return data;
    }

    public class Datum {

        @SerializedName("number")
        @Expose
        private Integer number;
        @SerializedName("numberOfVerses")
        @Expose
        private Integer numberOfVerses;
        @SerializedName("name")
        @Expose
        private Name name;
        @SerializedName("revelation")
        @Expose
        private Revelation revelation;

        public Integer getNumber() {
            return number;
        }

        public Integer getNumberOfVerses() {
            return numberOfVerses;
        }

        public Name getName() {
            return name;
        }

        public Revelation getRevelation() {
            return revelation;
        }

        public class Revelation {

            @SerializedName("id")
            @Expose
            private String id;

            public String getId() {
                return id;
            }

        }

        public class Name {

            @SerializedName("short")
            @Expose
            private String _short;
            @SerializedName("transliteration")
            @Expose
            private Transliteration transliteration;

            public String getShort() {
                return _short;
            }

            public Transliteration getTransliteration() {
                return transliteration;
            }

            public class Transliteration {

                @SerializedName("id")
                @Expose
                private String id;

                public String getId() {
                    return id;
                }

            }
        }
    }
}
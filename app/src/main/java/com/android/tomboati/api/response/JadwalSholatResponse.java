package com.android.tomboati.api.response;

import com.google.gson.annotations.SerializedName;

public class JadwalSholatResponse {
    @SerializedName("success")
    private Integer success;

    @SerializedName("data")
    private Data data;

    public Integer getSuccess() {
        return success;
    }

    public Data getData() {
        return data;
    }

    public static class Data {
        @SerializedName("type")
        private String type;

        @SerializedName("year")
        private String year;

        @SerializedName("name")
        private String name;

        @SerializedName("initial")
        private String initial;

        @SerializedName("latitude")
        private String latitude;

        @SerializedName("longitude")
        private String longitude;

        @SerializedName("timezone")
        private String timezone;

        @SerializedName("data")
        private DetailData detailData;

        public String getType() {
            return type;
        }

        public String getYear() {
            return year;
        }

        public String getName() {
            return name;
        }

        public String getInitial() {
            return initial;
        }

        public String getLatitude() {
            return latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public String getTimezone() {
            return timezone;
        }

        public DetailData getDetailData() {
            return detailData;
        }

        public DetailData getData() {
            return detailData;
        }

        public static class DetailData {
            @SerializedName("date")
            private DetailDate date;
            @SerializedName("data")
            private DetailDetailData data;

            public DetailDate getDate() {
                return date;
            }

            public DetailDetailData getData() {
                return data;
            }

            public static class DetailDate {
                @SerializedName("text")
                private DateText text;

                public DateText getText() {
                    return text;
                }

                public static class DateText {
                    @SerializedName("M")
                    private String m;

                    @SerializedName("H")
                    private String h;

                    public String getM() {
                        return m;
                    }

                    public void setM(String m) {
                        this.m = m;
                    }

                    public String getH() {
                        return h;
                    }

                    public void setH(String h) {
                        this.h = h;
                    }
                }
            }

            public static class DetailDetailData {
                @SerializedName("short-imsak")
                private String shortImsak;

                @SerializedName("short-shubuh")
                private String shortShubuh;

                @SerializedName("short-syuruq")
                private String shortSyuruq;

                @SerializedName("short-dhuha")
                private String shortDhuha;

                @SerializedName("short-dhuhur")
                private String shortDhuhur;

                @SerializedName("short-ashar")
                private String shortAshar;

                @SerializedName("short-maghrib")
                private String shortMaghrib;

                @SerializedName("short-isya")
                private String shortIsya;

                public String getShortImsak() {
                    return shortImsak;
                }

                public String getShortShubuh() {
                    return shortShubuh;
                }

                public String getShortSyuruq() {
                    return shortSyuruq;
                }

                public String getShortDhuha() {
                    return shortDhuha;
                }

                public String getShortDhuhur() {
                    return shortDhuhur;
                }

                public String getShortAshar() {
                    return shortAshar;
                }

                public String getShortMaghrib() {
                    return shortMaghrib;
                }

                public String getShortIsya() {
                    return shortIsya;
                }
            }
        }
    }
}

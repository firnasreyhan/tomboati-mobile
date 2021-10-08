package com.tomboati.tour.api.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MasjidResponse {
    @SerializedName("features")
    private List<Feature> features;

    public List<Feature> getFeatures() {
        return features;
    }

    public static class Feature {
        @SerializedName("text")
        private String text;

        @SerializedName("place_name")
        private String placeName;

        @SerializedName("center")
        private List<Float> center;

        @SerializedName("context")
        private List<Context> context;

        public String getText() {
            return text;
        }

        public String getPlaceName() {
            return placeName;
        }

        public List<Float> getCenter() {
            return center;
        }

        public List<Context> getContext() {
            return context;
        }

        public static class Context {
            private String text;

            public String getText() {
                return text;
            }
        }
    }
}

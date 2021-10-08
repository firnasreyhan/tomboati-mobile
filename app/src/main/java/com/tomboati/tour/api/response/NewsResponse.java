package com.tomboati.tour.api.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsResponse extends BaseResponse{
    @SerializedName("data")
    private List<NewsModel> data;

    public List<NewsModel> getData() {
        return data;
    }

    public static class NewsModel {
        @SerializedName("IDNEWSINFO")
        private String idNewsInfo;

        @SerializedName("JUDULNEWS")
        private String judulNews;

        @SerializedName("DESKRIPSINEWS")
        private String deskripsiNews;

        @SerializedName("CONTENTNEWS")
        private String contentNews;

        @SerializedName("FOTO")
        private String foto;

        @SerializedName("TANGGALNEWS")
        private String tanggalNews;

        public String getIdNewsInfo() {
            return idNewsInfo;
        }

        public String getJudulNews() {
            return judulNews;
        }

        public String getDeskripsiNews() {
            return deskripsiNews;
        }

        public String getContentNews() {
            return contentNews;
        }

        public String getFoto() {
            return foto;
        }

        public String getTanggalNews() {
            return tanggalNews;
        }
    }
}

package com.android.tomboati.api.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SignInResponse extends BaseResponse{
    @SerializedName("data" )
    private ArrayList<SignInModel> data;

    public ArrayList<SignInModel> getData() {
        return data;
    }

    public static class SignInModel {
        @SerializedName("NOMORKTP")
        private String nomorKTP;

        @SerializedName("EMAIL")
        private String email;

        @SerializedName("NAMALENGKAP")
        private String namaLengkap;

        @SerializedName("KATEGORI")
        private String kategori;

        @SerializedName("STATUS")
        private String status;

        @SerializedName("KODEREFERRALFROM")
        private String kodeReferralFrom;

        @SerializedName("KODEREFERRAL")
        private String kodeReferral;

        @SerializedName("NOMORHP")
        private String nomorHP;

        @SerializedName("FILEKTP")
        private String fileKTP;

        @SerializedName("FOTO")
        private String foto;

        @SerializedName("POIN")
        private int String;

        @SerializedName("JAMAAHBERANGKAT")
        private String jamaahBerangkat;

        public String getNomorKTP() {
            return nomorKTP;
        }

        public String getEmail() {
            return email;
        }

        public String getNamaLengkap() {
            return namaLengkap;
        }

        public String getKategori() {
            return kategori;
        }

        public String getStatus() {
            return status;
        }

        public String getKodeReferralFrom() {
            return kodeReferralFrom;
        }

        public String getKodeReferral() {
            return kodeReferral;
        }

        public String getNomorHP() {
            return nomorHP;
        }

        public String getFileKTP() {
            return fileKTP;
        }

        public String getFoto() {
            return foto;
        }

        public String getJamaahBerangkat() {
            return jamaahBerangkat;
        }
    }
}

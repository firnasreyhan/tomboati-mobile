package com.tomboati.tour.api.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SignInResponse extends BaseResponse{
    @SerializedName("data" )
    private List<SignInModel> data;

    public List<SignInModel> getData() {
        return data;
    }

    public static class SignInModel {
        @SerializedName("NOMORKTP")
        private String nomorKTP;

        @SerializedName("IDUSERREGISTER")
        private String idUserRegister;

        @SerializedName("EMAIL")
        private String email;

        @SerializedName("PASSWORD")
        private String password;

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

        @SerializedName("USERTOKEN")
        private String userToken;

        @SerializedName("ID_CHAT_ROOM")
        private String idChatRoom;

        public java.lang.String getIdUserRegister() {
            return idUserRegister;
        }

        public java.lang.String getNomorKTP() {
            return nomorKTP;
        }

        public java.lang.String getEmail() {
            return email;
        }

        public java.lang.String getPassword() {
            return password;
        }

        public java.lang.String getNamaLengkap() {
            return namaLengkap;
        }

        public java.lang.String getKategori() {
            return kategori;
        }

        public java.lang.String getStatus() {
            return status;
        }

        public java.lang.String getKodeReferralFrom() {
            return kodeReferralFrom;
        }

        public java.lang.String getKodeReferral() {
            return kodeReferral;
        }

        public java.lang.String getNomorHP() {
            return nomorHP;
        }

        public java.lang.String getFileKTP() {
            return fileKTP;
        }

        public java.lang.String getFoto() {
            return foto;
        }

        public int getString() {
            return String;
        }

        public java.lang.String getUserToken() {
            return userToken;
        }

        public java.lang.String getIdChatRoom() {
            return idChatRoom;
        }
    }
}

package com.tomboati.tour.api.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AkunMitraResponse {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    public Boolean getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public List<Datum> getData() {
        return data;
    }

    public static class Datum {

        @SerializedName("IDUSERREGISTER")
        @Expose
        private String iduserregister;
        @SerializedName("STATUS_USER")
        @Expose
        private String statusUser;
        @SerializedName("NOMORKTP")
        @Expose
        private String nomorktp;
        @SerializedName("EMAIL")
        @Expose
        private String email;
        @SerializedName("PASSWORD")
        @Expose
        private String password;
        @SerializedName("NAMALENGKAP")
        @Expose
        private String namalengkap;
        @SerializedName("KATEGORI")
        @Expose
        private Object kategori;
        @SerializedName("STATUS")
        @Expose
        private Object status;
        @SerializedName("KODEREFERRALFROM")
        @Expose
        private Object kodereferralfrom;
        @SerializedName("KODEREFERRAL")
        @Expose
        private String kodereferral;
        @SerializedName("NOMORHP")
        @Expose
        private String nomorhp;
        @SerializedName("FILEKTP")
        @Expose
        private String filektp;
        @SerializedName("BUKTIBAYAR")
        @Expose
        private String buktibayar;
        @SerializedName("FOTO")
        @Expose
        private String foto;
        @SerializedName("POIN")
        @Expose
        private Object poin;
        @SerializedName("USERNAME")
        @Expose
        private String username;
        @SerializedName("KECAMATAN")
        @Expose
        private String kecamatan;
        @SerializedName("ALAMAT")
        @Expose
        private String alamat;
        @SerializedName("USERTOKEN")
        @Expose
        private String usertoken;
        @SerializedName("CREATED_AT")
        @Expose
        private Object createdAt;
        @SerializedName("UPDATED_AT")
        @Expose
        private String updatedAt;
        @SerializedName("PROVINSI")
        @Expose
        private String provinsi;
        @SerializedName("KODEPOS")
        @Expose
        private String kodepos;
        @SerializedName("CABANG")
        @Expose
        private String cabang;
        @SerializedName("ATASNAMA")
        @Expose
        private String atasnama;
        @SerializedName("REKENING")
        @Expose
        private String rekening;
        @SerializedName("BANK")
        @Expose
        private String bank;
        @SerializedName("NEGARA")
        @Expose
        private String negara;
        @SerializedName("KOTA")
        @Expose
        private String kota;
        @SerializedName("ID_CHAT_ROOM")
        @Expose
        private String idChatRoom;

        public String getIduserregister() {
            return iduserregister;
        }

        public void setIduserregister(String iduserregister) {
            this.iduserregister = iduserregister;
        }

        public String getStatusUser() {
            return statusUser;
        }

        public void setStatusUser(String statusUser) {
            this.statusUser = statusUser;
        }

        public String getNomorktp() {
            return nomorktp;
        }

        public void setNomorktp(String nomorktp) {
            this.nomorktp = nomorktp;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getNamalengkap() {
            return namalengkap;
        }

        public void setNamalengkap(String namalengkap) {
            this.namalengkap = namalengkap;
        }

        public Object getKategori() {
            return kategori;
        }

        public void setKategori(Object kategori) {
            this.kategori = kategori;
        }

        public Object getStatus() {
            return status;
        }

        public void setStatus(Object status) {
            this.status = status;
        }

        public Object getKodereferralfrom() {
            return kodereferralfrom;
        }

        public void setKodereferralfrom(Object kodereferralfrom) {
            this.kodereferralfrom = kodereferralfrom;
        }

        public String getKodereferral() {
            return kodereferral;
        }

        public void setKodereferral(String kodereferral) {
            this.kodereferral = kodereferral;
        }

        public String getNomorhp() {
            return nomorhp;
        }

        public void setNomorhp(String nomorhp) {
            this.nomorhp = nomorhp;
        }

        public String getFilektp() {
            return filektp;
        }

        public void setFilektp(String filektp) {
            this.filektp = filektp;
        }

        public String getBuktibayar() {
            return buktibayar;
        }

        public void setBuktibayar(String buktibayar) {
            this.buktibayar = buktibayar;
        }

        public String getFoto() {
            return foto;
        }

        public void setFoto(String foto) {
            this.foto = foto;
        }

        public Object getPoin() {
            return poin;
        }

        public void setPoin(Object poin) {
            this.poin = poin;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getKecamatan() {
            return kecamatan;
        }

        public void setKecamatan(String kecamatan) {
            this.kecamatan = kecamatan;
        }

        public String getAlamat() {
            return alamat;
        }

        public void setAlamat(String alamat) {
            this.alamat = alamat;
        }

        public String getUsertoken() {
            return usertoken;
        }

        public void setUsertoken(String usertoken) {
            this.usertoken = usertoken;
        }

        public Object getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(Object createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getProvinsi() {
            return provinsi;
        }

        public void setProvinsi(String provinsi) {
            this.provinsi = provinsi;
        }

        public String getKodepos() {
            return kodepos;
        }

        public void setKodepos(String kodepos) {
            this.kodepos = kodepos;
        }

        public String getCabang() {
            return cabang;
        }

        public void setCabang(String cabang) {
            this.cabang = cabang;
        }

        public String getAtasnama() {
            return atasnama;
        }

        public void setAtasnama(String atasnama) {
            this.atasnama = atasnama;
        }

        public String getRekening() {
            return rekening;
        }

        public void setRekening(String rekening) {
            this.rekening = rekening;
        }

        public String getBank() {
            return bank;
        }

        public void setBank(String bank) {
            this.bank = bank;
        }

        public String getNegara() {
            return negara;
        }

        public void setNegara(String negara) {
            this.negara = negara;
        }

        public String getKota() {
            return kota;
        }

        public void setKota(String kota) {
            this.kota = kota;
        }

        public String getIdChatRoom() {
            return idChatRoom;
        }

        public void setIdChatRoom(String idChatRoom) {
            this.idChatRoom = idChatRoom;
        }

    }

}
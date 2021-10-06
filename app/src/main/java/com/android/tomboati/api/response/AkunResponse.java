package com.android.tomboati.api.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AkunResponse {

    @SerializedName("error")
    @Expose
    private Boolean error;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data_db_dash_tombo")
    @Expose
    private List<DataDbDashTombo> dataDbDashTombo = null;

    @SerializedName("data_tomboati")
    @Expose
    private List<DataTomboatus> dataTomboati = null;

    public Boolean getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public List<DataDbDashTombo> getDataDbDashTombo() {
        return dataDbDashTombo;
    }

    public class DataDbDashTombo {

        @SerializedName("id")
        @Expose
        private String id;

        @SerializedName("userid")
        @Expose
        private Object userid;

        @SerializedName("paket")
        @Expose
        private String paket;

        @SerializedName("name")
        @Expose
        private Object name;

        @SerializedName("hphone")
        @Expose
        private String hphone;

        @SerializedName("hphone2")
        @Expose
        private Object hphone2;

        @SerializedName("bank")
        @Expose
        private Object bank;

        @SerializedName("cabang")
        @Expose
        private Object cabang;

        @SerializedName("atasnama")
        @Expose
        private Object atasnama;

        @SerializedName("rekening")
        @Expose
        private Object rekening;

        @SerializedName("email")
        @Expose
        private Object email;

        @SerializedName("upline")
        @Expose
        private Object upline;

        @SerializedName("sponsor")
        @Expose
        private String sponsor;

        @SerializedName("g2")
        @Expose
        private Object g2;

        @SerializedName("g3")
        @Expose
        private Object g3;

        @SerializedName("g4")
        @Expose
        private Object g4;

        @SerializedName("g5")
        @Expose
        private Object g5;

        @SerializedName("g6")
        @Expose
        private Object g6;

        @SerializedName("g7")
        @Expose
        private Object g7;

        @SerializedName("g8")
        @Expose
        private Object g8;

        @SerializedName("g9")
        @Expose
        private Object g9;

        @SerializedName("g10")
        @Expose
        private Object g10;

        @SerializedName("passw")
        @Expose
        private Object passw;

        @SerializedName("passenc")
        @Expose
        private Object passenc;

        @SerializedName("bukti_bayar")
        @Expose
        private Object buktiBayar;

        @SerializedName("photo")
        @Expose
        private String photo;

        @SerializedName("kota")
        @Expose
        private Object kota;

        @SerializedName("kecamatan")
        @Expose
        private Object kecamatan;

        @SerializedName("propinsi")
        @Expose
        private Object propinsi;

        @SerializedName("kode_pos")
        @Expose
        private Object kodePos;

        @SerializedName("address")
        @Expose
        private Object address;

        @SerializedName("country")
        @Expose
        private Object country;

        @SerializedName("fotoktp")
        @Expose
        private Object fotoktp;

        @SerializedName("ktp")
        @Expose
        private Object ktp;

        @SerializedName("npwp")
        @Expose
        private Object npwp;

        @SerializedName("ahli_waris")
        @Expose
        private Object ahliWaris;

        @SerializedName("hub_ahliwaris")
        @Expose
        private Object hubAhliwaris;

        @SerializedName("activate")
        @Expose
        private String activate;

        @SerializedName("timer")
        @Expose
        private Object timer;

        @SerializedName("timerupgrade")
        @Expose
        private Object timerupgrade;

        @SerializedName("tot_ref")
        @Expose
        private Object totRef;

        @SerializedName("last_login")
        @Expose
        private Object lastLogin;

        @SerializedName("usercode")
        @Expose
        private Object usercode;

        @SerializedName("status_bank")
        @Expose
        private String statusBank;

        @SerializedName("status_nama")
        @Expose
        private String statusNama;

        @SerializedName("status_ec")
        @Expose
        private String statusEc;

        @SerializedName("photostatus")
        @Expose
        private String photostatus;

        @SerializedName("traffic")
        @Expose
        private Object traffic;

        @SerializedName("login")
        @Expose
        private Object login;

        @SerializedName("status")
        @Expose
        private String status;

        @SerializedName("tpassw")
        @Expose
        private Object tpassw;

        @SerializedName("tglupgrade")
        @Expose
        private Object tglupgrade;

        @SerializedName("transaction_code")
        @Expose
        private String transactionCode;

        @SerializedName("is_seen_notifikasi")
        @Expose
        private String isSeenNotifikasi;

        @SerializedName("qr_code")
        @Expose
        private Object qrCode;

        @SerializedName("usertoken")
        @Expose
        private String usertoken;

        public String getId() {
            return id;
        }

        public Object getUserid() {
            return userid;
        }

        public String getPaket() {
            return paket;
        }

        public Object getName() {
            return name;
        }

        public String getHphone() {
            return hphone;
        }

        public Object getHphone2() {
            return hphone2;
        }

        public Object getBank() {
            return bank;
        }

        public Object getCabang() {
            return cabang;
        }

        public Object getAtasnama() {
            return atasnama;
        }

        public Object getRekening() {
            return rekening;
        }

        public Object getEmail() {
            return email;
        }

        public Object getUpline() {
            return upline;
        }

        public String getSponsor() {
            return sponsor;
        }

        public Object getG2() {
            return g2;
        }

        public Object getG3() {
            return g3;
        }

        public Object getG4() {
            return g4;
        }

        public Object getG5() {
            return g5;
        }

        public Object getG6() {
            return g6;
        }

        public Object getG7() {
            return g7;
        }

        public Object getG8() {
            return g8;
        }

        public Object getG9() {
            return g9;
        }

        public Object getG10() {
            return g10;
        }

        public Object getPassw() {
            return passw;
        }

        public Object getPassenc() {
            return passenc;
        }

        public Object getBuktiBayar() {
            return buktiBayar;
        }

        public String getPhoto() {
            return photo;
        }

        public Object getKota() {
            return kota;
        }

        public Object getKecamatan() {
            return kecamatan;
        }

        public Object getPropinsi() {
            return propinsi;
        }

        public Object getKodePos() {
            return kodePos;
        }

        public Object getAddress() {
            return address;
        }

        public Object getCountry() {
            return country;
        }

        public Object getFotoktp() {
            return fotoktp;
        }

        public Object getKtp() {
            return ktp;
        }

        public Object getNpwp() {
            return npwp;
        }

        public Object getAhliWaris() {
            return ahliWaris;
        }

        public Object getHubAhliwaris() {
            return hubAhliwaris;
        }

        public String getActivate() {
            return activate;
        }

        public Object getTimer() {
            return timer;
        }

        public Object getTimerupgrade() {
            return timerupgrade;
        }

        public Object getTotRef() {
            return totRef;
        }

        public Object getLastLogin() {
            return lastLogin;
        }

        public Object getUsercode() {
            return usercode;
        }

        public String getStatusBank() {
            return statusBank;
        }

        public String getStatusNama() {
            return statusNama;
        }

        public String getStatusEc() {
            return statusEc;
        }

        public String getPhotostatus() {
            return photostatus;
        }

        public Object getTraffic() {
            return traffic;
        }

        public Object getLogin() {
            return login;
        }

        public String getStatus() {
            return status;
        }

        public Object getTpassw() {
            return tpassw;
        }

        public Object getTglupgrade() {
            return tglupgrade;
        }

        public String getTransactionCode() {
            return transactionCode;
        }

        public String getIsSeenNotifikasi() {
            return isSeenNotifikasi;
        }

        public void setIsSeenNotifikasi(String isSeenNotifikasi) {
            this.isSeenNotifikasi = isSeenNotifikasi;
        }

        public Object getQrCode() {
            return qrCode;
        }

        public void setQrCode(Object qrCode) {
            this.qrCode = qrCode;
        }

        public String getUsertoken() {
            return usertoken;
        }

        public void setUsertoken(String usertoken) {
            this.usertoken = usertoken;
        }

    }

    public List<DataTomboatus> getDataTomboati() {
        return dataTomboati;
    }

    public class DataTomboatus {

        @SerializedName("IDUSERREGISTER")
        @Expose
        private String iduserregister;
        @SerializedName("STATUS_USER")
        @Expose
        private String statusUser;
        @SerializedName("NOMORKTP")
        @Expose
        private Object nomorktp;
        @SerializedName("EMAIL")
        @Expose
        private Object email;
        @SerializedName("PASSWORD")
        @Expose
        private Object password;
        @SerializedName("NAMALENGKAP")
        @Expose
        private Object namalengkap;
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
        private Object filektp;
        @SerializedName("BUKTIBAYAR")
        @Expose
        private Object buktibayar;
        @SerializedName("FOTO")
        @Expose
        private Object foto;
        @SerializedName("POIN")
        @Expose
        private Object poin;
        @SerializedName("USERNAME")
        @Expose
        private Object username;
        @SerializedName("KECAMATAN")
        @Expose
        private Object kecamatan;
        @SerializedName("ALAMAT")
        @Expose
        private Object alamat;
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
        private Object provinsi;
        @SerializedName("KODEPOS")
        @Expose
        private Object kodepos;
        @SerializedName("CABANG")
        @Expose
        private Object cabang;
        @SerializedName("ATASNAMA")
        @Expose
        private Object atasnama;
        @SerializedName("REKENING")
        @Expose
        private Object rekening;
        @SerializedName("BANK")
        @Expose
        private Object bank;
        @SerializedName("NEGARA")
        @Expose
        private Object negara;
        @SerializedName("KOTA")
        @Expose
        private Object kota;
        @SerializedName("ID_CHAT_ROOM")
        @Expose
        private String idChatRoom;

        public String getIduserregister() {
            return iduserregister;
        }

        public String getStatusUser() {
            return statusUser;
        }

        public Object getNomorktp() {
            return nomorktp;
        }

        public Object getEmail() {
            return email;
        }

        public Object getPassword() {
            return password;
        }

        public Object getNamalengkap() {
            return namalengkap;
        }

        public Object getKategori() {
            return kategori;
        }

        public Object getStatus() {
            return status;
        }

        public Object getKodereferralfrom() {
            return kodereferralfrom;
        }

        public String getKodereferral() {
            return kodereferral;
        }

        public String getNomorhp() {
            return nomorhp;
        }

        public Object getFilektp() {
            return filektp;
        }

        public Object getBuktibayar() {
            return buktibayar;
        }

        public Object getFoto() {
            return foto;
        }

        public Object getPoin() {
            return poin;
        }

        public Object getUsername() {
            return username;
        }

        public Object getKecamatan() {
            return kecamatan;
        }

        public Object getAlamat() {
            return alamat;
        }

        public String getUsertoken() {
            return usertoken;
        }

        public Object getCreatedAt() {
            return createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public Object getProvinsi() {
            return provinsi;
        }

        public Object getKodepos() {
            return kodepos;
        }

        public Object getCabang() {
            return cabang;
        }

        public Object getAtasnama() {
            return atasnama;
        }

        public Object getRekening() {
            return rekening;
        }

        public Object getBank() {
            return bank;
        }

        public Object getNegara() {
            return negara;
        }

        public Object getKota() {
            return kota;
        }

        public String getIdChatRoom() {
            return idChatRoom;
        }

    }

}


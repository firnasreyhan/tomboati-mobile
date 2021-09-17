package com.android.tomboati.model;

import android.util.Log;

import java.io.Serializable;

public class AkunModel implements Serializable {

    private String id; // <= AUTO
    private String userId; // <= REGISTER =
    private String paket; // <= AUTO
    private String name; // <= PRE REQUIRE
    private String hphone; // <= AUTO
    private String bank; // <= REGISTER =
    private String cabang; // <= REGISTER =
    private String atasNama; // <= REGISTER =
    private String rekening; // <= REGISTER =
    private String email = "-"; // <= REGISTER =
    private String buktiBayar; // <= REGISTER =
    private String photo; // <= REGISTER =
    private String kota; // <= PRE REQUIRE
    private String kecamatan; // <= PRE REQUIRE
    private String propinsi; // <= PRE REQUIRE
    private String kodePos; // <= PRE REQUIRE
    private String address; // <= PRE REQUIRE
    private String country = "Indonesia";
    private String fotoKTP; // <= REGISTER =
    private String ktp; // <= REGISTER =
    private String referral; // <= AUTO
    private String idChatRoom; // <= AUTO

    private boolean suksesDaftarMitra = false;


    public boolean isFieldFilled() {

        String[] requiredField = {
                name, kota, kecamatan, propinsi, kodePos, address
        };

        int count = 0;
        for(String s : requiredField) {
            if(s == null) {
                count++;
                Log.d("====", "isFieldFilled: " + count);
            }
        }
        return (count == 0);
    }

    public boolean isSuksesDaftarMitra() {
        return suksesDaftarMitra;
    }

    public void setSuksesDaftarMitra(boolean suksesDaftarMitra) {
        this.suksesDaftarMitra = suksesDaftarMitra;
    }

    public String getIdChatRoom() {
        return idChatRoom;
    }

    public void setIdChatRoom(String idChatRoom) {
        this.idChatRoom = idChatRoom;
    }

    public String getReferral() {
        return referral;
    }

    public void setReferral(String referral) {
        this.referral = referral;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPaket() {
        return paket;
    }

    public void setPaket(String paket) {
        this.paket = paket;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHphone() {
        return hphone;
    }

    public void setHphone(String hphone) {
        this.hphone = hphone;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getCabang() {
        return cabang;
    }

    public void setCabang(String cabang) {
        this.cabang = cabang;
    }

    public String getAtasNama() {
        return atasNama;
    }

    public void setAtasNama(String atasNama) {
        this.atasNama = atasNama;
    }

    public String getRekening() {
        return rekening;
    }

    public void setRekening(String rekening) {
        this.rekening = rekening;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBuktiBayar() {
        return buktiBayar;
    }

    public void setBuktiBayar(String buktiBayar) {
        this.buktiBayar = buktiBayar;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getPropinsi() {
        return propinsi;
    }

    public void setPropinsi(String propinsi) {
        this.propinsi = propinsi;
    }

    public String getKodePos() {
        return kodePos;
    }

    public void setKodePos(String kodePos) {
        this.kodePos = kodePos;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFotoKTP() {
        return fotoKTP;
    }

    public void setFotoKTP(String fotoKTP) {
        this.fotoKTP = fotoKTP;
    }

    public String getKtp() {
        return ktp;
    }

    public void setKtp(String ktp) {
        this.ktp = ktp;
    }
}

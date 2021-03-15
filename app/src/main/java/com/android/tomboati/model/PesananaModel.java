package com.android.tomboati.model;

import android.net.Uri;

import java.io.Serializable;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Part;

public class PesananaModel implements Serializable {
    private String idUserRegister;
    private String email;
    private String nomorPaspor;
    private String tempatDikeluarkan;
    private String tanggalPenerbitanPaspor;
    private String tanggalBerakhirPaspor;
    private String tempatLahir;
    private String tanggalLahir;
    private String jenisKelamin;
    private String statusPerkawinan;
    private String kewarganegaraan;
    private String alamat;
    private String kelurahan;
    private String kecamatan;
    private String kotaKabupaten;
    private String provinsi;
    private String kodePOS;
    private String nomorHP;
    private String pekerjaan;
    private String riwayatPenyakit;
    private String namaLengkap;
    private String fileKTP;
    private String fileKK;
    private String filePaspor;
    private String fileBukuNikah;
    private String fileAkteKelahiran;
    private String ttdPendaftar;
    private String fcKTPAlmarhum;
    private String fcKKAlmarhum;
    private String fcFotoAlmarhum;
    private String idPaket;
    private String tanggalBerangkat;
    private String sheet;
    private String sheetHarga;
    private String waktu;
    private String namaLengkapKeluarga;
    private String alamatKeluarga;
    private String kelurahanKeluarga;
    private String kecamatanKeluarga;
    private String kotakabupatenKeluarga;
    private String provinsiKeluarga;
    private String kodePOSKeluarga;
    private String nomorHPKeluarga;

    public String getIdUserRegister() {
        return idUserRegister;
    }

    public void setIdUserRegister(String idUserRegister) {
        this.idUserRegister = idUserRegister;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomorPaspor() {
        return nomorPaspor;
    }

    public void setNomorPaspor(String nomorPaspor) {
        this.nomorPaspor = nomorPaspor;
    }

    public String getTempatDikeluarkan() {
        return tempatDikeluarkan;
    }

    public void setTempatDikeluarkan(String tempatDikeluarkan) {
        this.tempatDikeluarkan = tempatDikeluarkan;
    }

    public String getTanggalPenerbitanPaspor() {
        return tanggalPenerbitanPaspor;
    }

    public void setTanggalPenerbitanPaspor(String tanggalPenerbitanPaspor) {
        this.tanggalPenerbitanPaspor = tanggalPenerbitanPaspor;
    }

    public String getTanggalBerakhirPaspor() {
        return tanggalBerakhirPaspor;
    }

    public void setTanggalBerakhirPaspor(String tanggalBerakhirPaspor) {
        this.tanggalBerakhirPaspor = tanggalBerakhirPaspor;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getStatusPerkawinan() {
        return statusPerkawinan;
    }

    public void setStatusPerkawinan(String statusPerkawinan) {
        this.statusPerkawinan = statusPerkawinan;
    }

    public String getKewarganegaraan() {
        return kewarganegaraan;
    }

    public void setKewarganegaraan(String kewarganegaraan) {
        this.kewarganegaraan = kewarganegaraan;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getKelurahan() {
        return kelurahan;
    }

    public void setKelurahan(String kelurahan) {
        this.kelurahan = kelurahan;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getKotaKabupaten() {
        return kotaKabupaten;
    }

    public void setKotaKabupaten(String kotaKabupaten) {
        this.kotaKabupaten = kotaKabupaten;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    public String getKodePOS() {
        return kodePOS;
    }

    public void setKodePOS(String kodePOS) {
        this.kodePOS = kodePOS;
    }

    public String getNomorHP() {
        return nomorHP;
    }

    public void setNomorHP(String nomorHP) {
        this.nomorHP = nomorHP;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public String getRiwayatPenyakit() {
        return riwayatPenyakit;
    }

    public void setRiwayatPenyakit(String riwayatPenyakit) {
        this.riwayatPenyakit = riwayatPenyakit;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getFileKTP() {
        return fileKTP;
    }

    public void setFileKTP(String fileKTP) {
        this.fileKTP = fileKTP;
    }

    public String getFileKK() {
        return fileKK;
    }

    public void setFileKK(String fileKK) {
        this.fileKK = fileKK;
    }

    public String getFilePaspor() {
        return filePaspor;
    }

    public void setFilePaspor(String filePaspor) {
        this.filePaspor = filePaspor;
    }

    public String getFileBukuNikah() {
        return fileBukuNikah;
    }

    public void setFileBukuNikah(String fileBukuNikah) {
        this.fileBukuNikah = fileBukuNikah;
    }

    public String getFileAkteKelahiran() {
        return fileAkteKelahiran;
    }

    public void setFileAkteKelahiran(String fileAkteKelahiran) {
        this.fileAkteKelahiran = fileAkteKelahiran;
    }

    public String getTtdPendaftar() {
        return ttdPendaftar;
    }

    public void setTtdPendaftar(String ttdPendaftar) {
        this.ttdPendaftar = ttdPendaftar;
    }

    public String getFcKTPAlmarhum() {
        return fcKTPAlmarhum;
    }

    public void setFcKTPAlmarhum(String fcKTPAlmarhum) {
        this.fcKTPAlmarhum = fcKTPAlmarhum;
    }

    public String getFcKKAlmarhum() {
        return fcKKAlmarhum;
    }

    public void setFcKKAlmarhum(String fcKKAlmarhum) {
        this.fcKKAlmarhum = fcKKAlmarhum;
    }

    public String getFcFotoAlmarhum() {
        return fcFotoAlmarhum;
    }

    public void setFcFotoAlmarhum(String fcFotoAlmarhum) {
        this.fcFotoAlmarhum = fcFotoAlmarhum;
    }

    public String getIdPaket() {
        return idPaket;
    }

    public void setIdPaket(String idPaket) {
        this.idPaket = idPaket;
    }

    public String getTanggalBerangkat() {
        return tanggalBerangkat;
    }

    public void setTanggalBerangkat(String tanggalBerangkat) {
        this.tanggalBerangkat = tanggalBerangkat;
    }

    public String getSheet() {
        return sheet;
    }

    public void setSheet(String sheet) {
        this.sheet = sheet;
    }

    public String getSheetHarga() {
        return sheetHarga;
    }

    public void setSheetHarga(String sheetHarga) {
        this.sheetHarga = sheetHarga;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getNamaLengkapKeluarga() {
        return namaLengkapKeluarga;
    }

    public void setNamaLengkapKeluarga(String namaLengkapKeluarga) {
        this.namaLengkapKeluarga = namaLengkapKeluarga;
    }

    public String getAlamatKeluarga() {
        return alamatKeluarga;
    }

    public void setAlamatKeluarga(String alamatKeluarga) {
        this.alamatKeluarga = alamatKeluarga;
    }

    public String getKelurahanKeluarga() {
        return kelurahanKeluarga;
    }

    public void setKelurahanKeluarga(String kelurahanKeluarga) {
        this.kelurahanKeluarga = kelurahanKeluarga;
    }

    public String getKecamatanKeluarga() {
        return kecamatanKeluarga;
    }

    public void setKecamatanKeluarga(String kecamatanKeluarga) {
        this.kecamatanKeluarga = kecamatanKeluarga;
    }

    public String getKotakabupatenKeluarga() {
        return kotakabupatenKeluarga;
    }

    public void setKotakabupatenKeluarga(String kotakabupatenKeluarga) {
        this.kotakabupatenKeluarga = kotakabupatenKeluarga;
    }

    public String getProvinsiKeluarga() {
        return provinsiKeluarga;
    }

    public void setProvinsiKeluarga(String provinsiKeluarga) {
        this.provinsiKeluarga = provinsiKeluarga;
    }

    public String getKodePOSKeluarga() {
        return kodePOSKeluarga;
    }

    public void setKodePOSKeluarga(String kodePOSKeluarga) {
        this.kodePOSKeluarga = kodePOSKeluarga;
    }

    public String getNomorHPKeluarga() {
        return nomorHPKeluarga;
    }

    public void setNomorHPKeluarga(String nomorHPKeluarga) {
        this.nomorHPKeluarga = nomorHPKeluarga;
    }
}

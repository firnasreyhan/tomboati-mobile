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
    private Uri fileKTP;
    private Uri fileKK;
    private Uri filePaspor;
    private Uri fileBukuNikah;
    private Uri fileAkteKelahiran;
    private Uri ttdPendaftar;
    private Uri fcKTPAlmarhum;
    private Uri fcKKAlmarhum;
    private Uri fcFotoAlmarhum;
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

    public Uri getFileKTP() {
        return fileKTP;
    }

    public void setFileKTP(Uri fileKTP) {
        this.fileKTP = fileKTP;
    }

    public Uri getFileKK() {
        return fileKK;
    }

    public void setFileKK(Uri fileKK) {
        this.fileKK = fileKK;
    }

    public Uri getFilePaspor() {
        return filePaspor;
    }

    public void setFilePaspor(Uri filePaspor) {
        this.filePaspor = filePaspor;
    }

    public Uri getFileBukuNikah() {
        return fileBukuNikah;
    }

    public void setFileBukuNikah(Uri fileBukuNikah) {
        this.fileBukuNikah = fileBukuNikah;
    }

    public Uri getFileAkteKelahiran() {
        return fileAkteKelahiran;
    }

    public void setFileAkteKelahiran(Uri fileAkteKelahiran) {
        this.fileAkteKelahiran = fileAkteKelahiran;
    }

    public Uri getTtdPendaftar() {
        return ttdPendaftar;
    }

    public void setTtdPendaftar(Uri ttdPendaftar) {
        this.ttdPendaftar = ttdPendaftar;
    }

    public Uri getFcKTPAlmarhum() {
        return fcKTPAlmarhum;
    }

    public void setFcKTPAlmarhum(Uri fcKTPAlmarhum) {
        this.fcKTPAlmarhum = fcKTPAlmarhum;
    }

    public Uri getFcKKAlmarhum() {
        return fcKKAlmarhum;
    }

    public void setFcKKAlmarhum(Uri fcKKAlmarhum) {
        this.fcKKAlmarhum = fcKKAlmarhum;
    }

    public Uri getFcFotoAlmarhum() {
        return fcFotoAlmarhum;
    }

    public void setFcFotoAlmarhum(Uri fcFotoAlmarhum) {
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

    public String getKotakabupatenKeluarga() {
        return kotakabupatenKeluarga;
    }

    public void setKotakabupatenKeluarga(String kotakabupatenKeluarga) {
        this.kotakabupatenKeluarga = kotakabupatenKeluarga;
    }
}

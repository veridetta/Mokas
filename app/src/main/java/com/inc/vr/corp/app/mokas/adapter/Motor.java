package com.inc.vr.corp.app.mokas.adapter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Motor {

    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("kode_barang")
    @Expose
    private String kodeBarang;
    @SerializedName("harga")
    @Expose
    private String harga;
    @SerializedName("deskripsi")
    @Expose
    private String deskripsi;
    @SerializedName("kontak")
    @Expose
    private String kontak;
    @SerializedName("stok")
    @Expose
    private String stok;
    @SerializedName("gambar")
    @Expose
    private String gambar;
    @SerializedName("kategori")
    @Expose
    private String kategori;

    public Motor(String nama, String harga, String kategori){
        this.nama=nama;
        this.harga=harga;
        this.kategori=kategori;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKodeBarang() {
        return kodeBarang;
    }

    public void setKodeBarang(String kodeBarang) {
        this.kodeBarang = kodeBarang;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getKontak() {
        return kontak;
    }

    public void setKontak(String kontak) {
        this.kontak = kontak;
    }

    public String getStok() {
        return stok;
    }

    public void setStok(String stok) {
        this.stok = stok;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

}
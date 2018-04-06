package com.ngocbich.chitieucanhan;

/**
 * Created by Ngoc Bich on 4/5/2018.
 */

public class ChiTieu {
    private int id;
    private String noidung;
    private int soTien;
    private int loai;
    private String ghiChu;
    private int ngay;
    private int thang;
    private int nam;

    public ChiTieu(String noidung, int soTien, int loai, String ghiChu, int ngay, int thang, int nam) {
        this.noidung = noidung;
        this.soTien = soTien;
        this.loai = loai;
        this.ghiChu = ghiChu;
        this.ngay = ngay;
        this.thang = thang;
        this.nam = nam;
    }

    public ChiTieu(int id, String noidung, int soTien, int loai, String ghiChu, int ngay, int thang, int nam) {
        this.id = id;
        this.noidung = noidung;
        this.soTien = soTien;
        this.loai = loai;
        this.ghiChu = ghiChu;
        this.ngay = ngay;
        this.thang = thang;
        this.nam = nam;
    }

    public ChiTieu(int id, int soTien, String bname) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public int getSoTien() {
        return soTien;
    }

    public void setSoTien(int soTien) {
        this.soTien = soTien;
    }

    public int getLoai() {
        return loai;
    }

    public void setLoai(int loai) {
        this.loai = loai;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public int getNgay() {
        return ngay;
    }

    public void setNgay(int ngay) {
        this.ngay = ngay;
    }

    public int getThang() {
        return thang;
    }

    public void setThang(int thang) {
        this.thang = thang;
    }

    public int getNam() {
        return nam;
    }

    public void setNam(int nam) {
        this.nam = nam;
    }

    @Override
    public String toString() {
        return "ChiTieu{" +
                "id=" + id +
                ", noidung='" + noidung + '\'' +
                ", soTien=" + soTien +
                ", loai=" + loai +
                ", ghiChu='" + ghiChu + '\'' +
                ", ngay=" + ngay +
                ", thang=" + thang +
                ", nam=" + nam +
                '}';
    }
}

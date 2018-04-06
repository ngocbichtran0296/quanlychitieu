package com.ngocbich.chitieucanhan;

/**
 * Created by Ngoc Bich on 4/6/2018.
 */

public class Report {
    private int category;
    private int total;
    private String noiDung;

    public Report(int category, int total,String noiDung) {
        this.category = category;
        this.total = total;
        this.noiDung=noiDung;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }
}

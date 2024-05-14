package com.example.model;

public class KiHoc {
    private Integer id;
    private String ten;
    private NamHoc namhoc;
    private boolean danghoc;
    private boolean dangdk;

    public KiHoc() {
        this.id = 0;
        this.ten = "";
        this.namhoc = new NamHoc();
        this.danghoc = false;
        this.dangdk = false;
    }
    
    public KiHoc(Integer id, String ten, NamHoc namhoc, boolean danghoc, boolean dangdk) {
        this.id = id;
        this.ten = ten;
        this.namhoc = namhoc;
        this.danghoc = danghoc;
        this.dangdk = dangdk;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public NamHoc getNamhoc() {
        return namhoc;
    }

    public void setNamhoc(NamHoc namhoc) {
        this.namhoc = namhoc;
    }

    public boolean isDanghoc() {
        return danghoc;
    }

    public void setDanghoc(boolean danghoc) {
        this.danghoc = danghoc;
    }

    public boolean isDangdk() {
        return dangdk;
    }

    public void setDangdk(boolean dangdk) {
        this.dangdk = dangdk;
    }
    
    
}
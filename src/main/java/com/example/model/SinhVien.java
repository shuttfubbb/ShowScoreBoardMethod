package com.example.model;

public class SinhVien {
    private Integer id;
    private String masv;
    private String ten;
    private String username;
    private String password;
    
    public SinhVien() {
        this.id = 0;
        this.masv = "";
        this.ten = "";
        this.username = "";
        this.password = "";
    }

    public SinhVien(Integer id, String masv, String ten, String username, String password) {
        this.id = id;
        this.masv = masv;
        this.ten = ten;
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMasv() {
        return masv;
    }

    public void setMasv(String masv) {
        this.masv = masv;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}

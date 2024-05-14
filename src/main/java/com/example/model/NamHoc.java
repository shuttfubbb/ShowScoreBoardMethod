package com.example.model;

public class NamHoc {
    private Integer id;
    private String ten;

    public NamHoc() {
        this.id = 0;
        this.ten = "";
    }
    
    public NamHoc(Integer id, String ten) {
        this.id = id;
        this.ten = ten;
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
    
    
}
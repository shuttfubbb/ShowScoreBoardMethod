package com.example.model;

public class DauDiem {
    private Integer id;
    private String ten;
    
    public DauDiem() {
        this.id = 0;
        this.ten = "";
    }

    public DauDiem(Integer id, String ten) {
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
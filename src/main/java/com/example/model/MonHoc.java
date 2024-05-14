package com.example.model;

public class MonHoc {
    private Integer id;
    private String ten;
    private Integer soTC;

    public MonHoc() {
        this.id = 0;
        this.ten = "";
        this.soTC = 0;
    }
    
    public MonHoc(Integer id, String ten, Integer soTC) {
        this.id = id;
        this.ten = ten;
        this.soTC = soTC;
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

    public Integer getSoTC() {
        return soTC;
    }

    public void setSoTC(Integer soTC) {
        this.soTC = soTC;
    }
    
    
}

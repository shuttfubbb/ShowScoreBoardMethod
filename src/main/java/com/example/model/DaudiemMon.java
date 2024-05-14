package com.example.model;

public class DaudiemMon {
    private Integer id;
    private DauDiem daudiem;
    private Double heso;

    public DaudiemMon() {
        this.id = 0;
        this.daudiem = new DauDiem();
        this.heso = 0.0;
    }
    
    public DaudiemMon(Integer id, DauDiem daudiem, Double heso) {
        this.id = id;
        this.daudiem = daudiem;
        this.heso = heso;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DauDiem getDaudiem() {
        return daudiem;
    }

    public void setDaudiem(DauDiem daudiem) {
        this.daudiem = daudiem;
    }

    public Double getHeso() {
        return heso;
    }

    public void setHeso(Double heso) {
        this.heso = heso;
    }
    
    
}
package com.example.model;

public class KetQua {
    private Integer id;
    private DaudiemMon diemtp;
    private Double diem;

    public KetQua() {
        this.id = 0;
        this.diemtp = new DaudiemMon();
        this.diem = 0.0;
    }
    
    public KetQua(Integer id, DaudiemMon diemtp, Double diem) {
        this.id = id;
        this.diemtp = diemtp;
        this.diem = diem;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DaudiemMon getDiemtp() {
        return diemtp;
    }

    public void setDiemtp(DaudiemMon diemtp) {
        this.diemtp = diemtp;
    }

    public Double getDiem() {
        return diem;
    }

    public void setDiem(Double diem) {
        this.diem = diem;
    }
    
    
}

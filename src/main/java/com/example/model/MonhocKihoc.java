package com.example.model;

public class MonhocKihoc {
    private Integer id;
    private MonHoc monhoc;
    private KiHoc kihoc;

    public MonhocKihoc() {
        this.id = 0;
        this.monhoc = new MonHoc();
        this.kihoc = new KiHoc();
    }
    
    public MonhocKihoc(Integer id, MonHoc monhoc, KiHoc kihoc) {
        this.id = id;
        this.monhoc = monhoc;
        this.kihoc = kihoc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MonHoc getMonhoc() {
        return monhoc;
    }

    public void setMonhoc(MonHoc monhoc) {
        this.monhoc = monhoc;
    }

    public KiHoc getKihoc() {
        return kihoc;
    }

    public void setKihoc(KiHoc kihoc) {
        this.kihoc = kihoc;
    }
    
    
}

package com.example.model;

import java.util.ArrayList;

public class TKSinhvien extends SinhVien{
	private Integer soTC;
	private Integer soTCno;
	private Double diemTB;
	private ArrayList<DangkiMon> dsDangkiMon;
	private ArrayList<TKKihocSv> dsTKKihocSv;
	
	public TKSinhvien() {
		super();
		this.soTC = 0;
		this.soTCno = 0;
		this.diemTB = 0.0;
		this.dsDangkiMon = new ArrayList<DangkiMon>();
		this.dsTKKihocSv = new ArrayList<TKKihocSv>();
	}
	
	public TKSinhvien(Integer id, String masv, String ten, String username, String password) {
		super(id, masv, ten, username, password);
		this.soTC = 0;
		this.soTCno = 0;
		this.diemTB = 0.0;
		this.dsDangkiMon = new ArrayList<DangkiMon>();
		this.dsTKKihocSv = new ArrayList<TKKihocSv>();
	}
	
	public TKSinhvien(Integer soTC, Integer soTCno, Double diemTB, ArrayList<DangkiMon> dsDangkiMon, ArrayList<TKKihocSv> dsTKKihocSv) {
		super();
		this.soTC = soTC;
		this.soTCno = soTCno;
		this.diemTB = diemTB;
		this.dsDangkiMon = dsDangkiMon;
		this.dsTKKihocSv = dsTKKihocSv;
	}
	public Integer getSoTC() {
		return soTC;
	}
	public void setSoTC(Integer soTC) {
		this.soTC = soTC;
	}
	public Integer getSoTCno() {
		return soTCno;
	}
	public void setSoTCno(Integer soTCno) {
		this.soTCno = soTCno;
	}
	public Double getDiemTB() {
		return diemTB;
	}
	public void setDiemTB(Double diemTB) {
		this.diemTB = diemTB;
	}
	public ArrayList<DangkiMon> getDsDangkiMon() {
		return dsDangkiMon;
	}
	public void setDsDangkiMon(ArrayList<DangkiMon> dsDangkiMon) {
		this.dsDangkiMon = dsDangkiMon;
	}
	public ArrayList<TKKihocSv> getDsTKKihocSv() {
		return dsTKKihocSv;
	}
	public void setDsTKKihocSv(ArrayList<TKKihocSv> dsTKKihocSv) {
		this.dsTKKihocSv = dsTKKihocSv;
	}
	
}

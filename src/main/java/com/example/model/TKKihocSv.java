package com.example.model;

import java.util.ArrayList;

public class TKKihocSv extends KiHoc{
	private ArrayList<DangkiMon> dsmon;
	private Double diemTB;
	private Integer soTc;
	private Integer soTcno;
	
	public TKKihocSv() {
		super();
		this.dsmon = new ArrayList<DangkiMon>();
		this.diemTB = 0.0;
		this.soTc = 0;
		this.soTcno = 0;
	}
	
	public TKKihocSv(ArrayList<DangkiMon> dsmon, Double diemTB, Integer soTc, Integer soTcno) {
		super();
		this.dsmon = dsmon;
		this.diemTB = diemTB;
		this.soTc = soTc;
		this.soTcno = soTcno;
	}

	public ArrayList<DangkiMon> getDsmon() {
		return dsmon;
	}

	public void setDsmon(ArrayList<DangkiMon> dsmon) {
		this.dsmon = dsmon;
	}

	public Double getDiemTB() {
		return diemTB;
	}

	public void setDiemTB(Double diemTB) {
		this.diemTB = diemTB;
	}

	public Integer getSoTc() {
		return soTc;
	}

	public void setSoTc(Integer soTc) {
		this.soTc = soTc;
	}

	public Integer getSoTcno() {
		return soTcno;
	}

	public void setSoTcno(Integer soTcno) {
		this.soTcno = soTcno;
	}
	
	
}

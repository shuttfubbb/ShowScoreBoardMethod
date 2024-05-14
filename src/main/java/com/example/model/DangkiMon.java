package com.example.model;
import java.util.ArrayList;


public class DangkiMon {
    private Integer id;
    private SinhVien sv;
    private MonhocKihoc monhoc;
    private ArrayList<KetQua> dsdiemtp;
    private Double diemThi;
    private Double diemTB10;
    private Double diemTB4;
    private String diemTBc;
    private String ketqua;
    
    public DangkiMon() {
		this.id = 0;
		this.sv = new SinhVien();
		this.monhoc = new MonhocKihoc();
		this.dsdiemtp = new ArrayList<KetQua>();
		this.diemThi = 0.0;
		this.diemTB10 = 0.0;
		this.diemTB4 = 0.0;
		this.diemTBc = "F";
		this.ketqua = "Không đạt";
	}
    
	public DangkiMon(Integer id, SinhVien sv, MonhocKihoc monhoc, ArrayList<KetQua> dsdiemtp, Double diemThi, Double diemTB10,
			Double diemTB4, String diemTBc, String ketqua) {
		this.id = id;
		this.sv = sv;
		this.monhoc = monhoc;
		this.dsdiemtp = dsdiemtp;
		this.diemTB10 = diemTB10;
		this.diemTB4 = diemTB4;
		this.diemTBc = diemTBc;
		this.ketqua = ketqua;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SinhVien getSv() {
		return sv;
	}

	public void setSv(SinhVien sv) {
		this.sv = sv;
	}

	public MonhocKihoc getMonhoc() {
		return monhoc;
	}

	public void setMonhoc(MonhocKihoc monhoc) {
		this.monhoc = monhoc;
	}

	public ArrayList<KetQua> getDsdiemtp() {
		return dsdiemtp;
	}

	public void setDsdiemtp(ArrayList<KetQua> dsdiemtp) {
		this.dsdiemtp = dsdiemtp;
	}

	public Double getDiemTB10() {
		return diemTB10;
	}

	public void setDiemTB10(Double diemTB10) {
		this.diemTB10 = diemTB10;
	}

	public Double getDiemTB4() {
		return diemTB4;
	}

	public void setDiemTB4(Double diemTB4) {
		this.diemTB4 = diemTB4;
	}

	public String getDiemTBc() {
		return diemTBc;
	}

	public void setDiemTBc(String diemTBc) {
		this.diemTBc = diemTBc;
	}

	public Double getDiemThi() {
		return diemThi;
	}

	public void setDiemThi(Double diemThi) {
		this.diemThi = diemThi;
	}

	public String getKetqua() {
		return ketqua;
	}

	public void setKetqua(String ketqua) {
		this.ketqua = ketqua;
	}
	
	
    
}

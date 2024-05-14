package com.example.demo;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.example.controller.SinhVienDAO;
import com.example.controller.TKSinhvienDAO;
import com.example.model.DangkiMon;
import com.example.model.DaudiemMon;
import com.example.model.KetQua;
import com.example.model.KiHoc;
import com.example.model.MonHoc;
import com.example.model.MonhocKihoc;
import com.example.model.SinhVien;
import com.example.model.TKKihocSv;
import com.example.model.TKSinhvien;

class TKSinhvienDAOTest {

	TKSinhvienDAO tkDAO;
	public TKSinhvienDAOTest(){
		tkDAO = new TKSinhvienDAO();
	}
	
	@Test
	public void testDisplayComponentScores() throws Exception { // Check hiển thị điểm thành phần 
		TKSinhvien sv = new TKSinhvien(1, "B20DCAT078","Nguyễn Quang Huy", "huy", "123");

		
		boolean actual = tkDAO.getScoreboard(sv);
		DangkiMon mon =	sv.getDsDangkiMon().get(0); 
		assertTrue(actual); // Check query
		
		Double chuyencan = 10.0;
		Double giuaki = 7.0;
		Double btl = 6.0;
		Double thi = 8.5;
		assertEquals(chuyencan , mon.getDsdiemtp().get(1).getDiem()); // Check điểm chuyên cần
		assertEquals(giuaki , mon.getDsdiemtp().get(2).getDiem()); // Check điểm giua ki
		assertEquals(btl , mon.getDsdiemtp().get(3).getDiem()); // Check điểm btl
		assertEquals(thi , mon.getDsdiemtp().get(0).getDiem()); // Check điểm thi
	}
	
	@Test
	public void testCalculateScoreException1() throws Exception { // Check hiển thị điểm thành phần 
		DangkiMon mon = new DangkiMon();
		
		DaudiemMon dd1 = new DaudiemMon();dd1.setHeso(0.1);
		DaudiemMon dd2 = new DaudiemMon();dd2.setHeso(0.2);
		DaudiemMon dd3 = new DaudiemMon();dd3.setHeso(0.1);
		DaudiemMon dd4 = new DaudiemMon();dd4.setHeso(0.6);
		
		KetQua kq1 = new KetQua();kq1.setDiemtp(dd1);kq1.setDiem(10.0);
		KetQua kq2 = new KetQua();kq2.setDiemtp(dd2);kq2.setDiem(7.0);
		KetQua kq3 = new KetQua();kq3.setDiemtp(dd3);kq3.setDiem(6.0);
		KetQua kq4 = new KetQua();kq4.setDiemtp(dd4);kq4.setDiem(8.5);
		
		mon.getDsdiemtp().add(kq4);mon.getDsdiemtp().add(kq1);mon.getDsdiemtp().add(kq2);mon.getDsdiemtp().add(kq3);
		
		tkDAO.calculateScore(mon); 
		
		String diemTBc = "B+";
		Double diemTB4 = 3.5;
		Double diemTB10 = 8.1;
		String ketqua = "Đạt";
		assertEquals(diemTBc , mon.getDiemTBc()); // Check điểm TB chữ
		assertEquals(diemTB4 , mon.getDiemTB4()); // Check điểm TB 4
		assertEquals(diemTB10 , mon.getDiemTB10()); // Check điểm TB 10
		assertEquals(ketqua , mon.getKetqua()); // Check kết quả
	}
	
	@Test
	public void testCalculateScoreException2() throws Exception { // Check hiển thị điểm thành phần (F) khi điểm Chuyên cần < 5
		DangkiMon mon = new DangkiMon();
		
		DaudiemMon dd1 = new DaudiemMon();dd1.setHeso(0.1);
		DaudiemMon dd2 = new DaudiemMon();dd2.setHeso(0.2);
		DaudiemMon dd3 = new DaudiemMon();dd3.setHeso(0.1);
		DaudiemMon dd4 = new DaudiemMon();dd4.setHeso(0.6);
		
		KetQua kq1 = new KetQua();kq1.setDiemtp(dd1);kq1.setDiem(4.9);
		KetQua kq2 = new KetQua();kq2.setDiemtp(dd2);kq2.setDiem(10.0);
		KetQua kq3 = new KetQua();kq3.setDiemtp(dd3);kq3.setDiem(10.0);
		KetQua kq4 = new KetQua();kq4.setDiemtp(dd4);kq4.setDiem(10.0);
		
		mon.getDsdiemtp().add(kq4);mon.getDsdiemtp().add(kq1);mon.getDsdiemtp().add(kq2);mon.getDsdiemtp().add(kq3);
		
		tkDAO.calculateScore(mon); 
		
		String diemTBc = "F";
		Double diemTB4 = 0.0;
		Double diemTB10 = 0.0;
		String ketqua = "Không đạt";
		assertEquals(diemTBc , mon.getDiemTBc()); // Check điểm TB chữ
		assertEquals(diemTB4 , mon.getDiemTB4()); // Check điểm TB 4
		assertEquals(diemTB10 , mon.getDiemTB10()); // Check điểm TB 10
		assertEquals(ketqua , mon.getKetqua()); // Check kết quả
	}

	@Test
	public void testCalculateScoreException3() throws Exception { // Check hiển thị điểm thành phần (F) khi điểm điểm giữa kì 0
		DangkiMon mon = new DangkiMon();
		
		DaudiemMon dd1 = new DaudiemMon();dd1.setHeso(0.1);
		DaudiemMon dd2 = new DaudiemMon();dd2.setHeso(0.2);
		DaudiemMon dd3 = new DaudiemMon();dd3.setHeso(0.1);
		DaudiemMon dd4 = new DaudiemMon();dd4.setHeso(0.6);
		
		KetQua kq1 = new KetQua();kq1.setDiemtp(dd1);kq1.setDiem(10.0);
		KetQua kq2 = new KetQua();kq2.setDiemtp(dd2);kq2.setDiem(0.0);
		KetQua kq3 = new KetQua();kq3.setDiemtp(dd3);kq3.setDiem(10.0);
		KetQua kq4 = new KetQua();kq4.setDiemtp(dd4);kq4.setDiem(10.0);
		
		mon.getDsdiemtp().add(kq4);mon.getDsdiemtp().add(kq1);mon.getDsdiemtp().add(kq2);mon.getDsdiemtp().add(kq3);
		
		tkDAO.calculateScore(mon); 
		
		String diemTBc = "F";
		Double diemTB4 = 0.0;
		Double diemTB10 = 0.0;
		String ketqua = "Không đạt";
		assertEquals(diemTBc , mon.getDiemTBc()); // Check điểm TB chữ
		assertEquals(diemTB4 , mon.getDiemTB4()); // Check điểm TB 4
		assertEquals(diemTB10 , mon.getDiemTB10()); // Check điểm TB 10
		assertEquals(ketqua , mon.getKetqua()); // Check kết quả
	}
	
	@Test
	public void testCalculateScoreException4() throws Exception { // Check hiển thị điểm thành phần (F) khi điểm điểm BTL 0
		DangkiMon mon = new DangkiMon();
		
		DaudiemMon dd1 = new DaudiemMon();dd1.setHeso(0.1);
		DaudiemMon dd2 = new DaudiemMon();dd2.setHeso(0.2);
		DaudiemMon dd3 = new DaudiemMon();dd3.setHeso(0.1);
		DaudiemMon dd4 = new DaudiemMon();dd4.setHeso(0.6);
		
		KetQua kq1 = new KetQua();kq1.setDiemtp(dd1);kq1.setDiem(10.0);
		KetQua kq2 = new KetQua();kq2.setDiemtp(dd2);kq2.setDiem(10.0);
		KetQua kq3 = new KetQua();kq3.setDiemtp(dd3);kq3.setDiem(0.0);
		KetQua kq4 = new KetQua();kq4.setDiemtp(dd4);kq4.setDiem(10.0);
		
		mon.getDsdiemtp().add(kq4);mon.getDsdiemtp().add(kq1);mon.getDsdiemtp().add(kq2);mon.getDsdiemtp().add(kq3);
		
		tkDAO.calculateScore(mon); 
		
		String diemTBc = "F";
		Double diemTB4 = 0.0;
		Double diemTB10 = 0.0;
		String ketqua = "Không đạt";
		assertEquals(diemTBc , mon.getDiemTBc()); // Check điểm TB chữ
		assertEquals(diemTB4 , mon.getDiemTB4()); // Check điểm TB 4
		assertEquals(diemTB10 , mon.getDiemTB10()); // Check điểm TB 10
		assertEquals(ketqua , mon.getKetqua()); // Check kết quả
	}
	
	@Test
	public void testCalculateScoreException5() throws Exception { // Check hiển thị điểm thành phần (F) khi điểm Thi = 0
		DangkiMon mon = new DangkiMon();
		
		DaudiemMon dd1 = new DaudiemMon();dd1.setHeso(0.1);
		DaudiemMon dd2 = new DaudiemMon();dd2.setHeso(0.2);
		DaudiemMon dd3 = new DaudiemMon();dd3.setHeso(0.1);
		DaudiemMon dd4 = new DaudiemMon();dd4.setHeso(0.6);
		
		KetQua kq1 = new KetQua();kq1.setDiemtp(dd1);kq1.setDiem(10.0);
		KetQua kq2 = new KetQua();kq2.setDiemtp(dd2);kq2.setDiem(10.0);
		KetQua kq3 = new KetQua();kq3.setDiemtp(dd3);kq3.setDiem(10.0);
		KetQua kq4 = new KetQua();kq4.setDiemtp(dd4);kq4.setDiem(0.0);
		
		mon.getDsdiemtp().add(kq4);mon.getDsdiemtp().add(kq1);mon.getDsdiemtp().add(kq2);mon.getDsdiemtp().add(kq3);
		
		tkDAO.calculateScore(mon); 
		
		String diemTBc = "F";
		Double diemTB4 = 0.0;
		Double diemTB10 = 0.0;
		String ketqua = "Không đạt";
		assertEquals(diemTBc , mon.getDiemTBc()); // Check điểm TB chữ
		assertEquals(diemTB4 , mon.getDiemTB4()); // Check điểm TB 4
		assertEquals(diemTB10 , mon.getDiemTB10()); // Check điểm TB 10
		assertEquals(ketqua , mon.getKetqua()); // Check kết quả
	}
	
	@Test
	public void testCalculateScoreException6() throws Exception { // Check A+ 1
		DangkiMon mon = new DangkiMon();
		
		DaudiemMon dd1 = new DaudiemMon();dd1.setHeso(0.1);
		DaudiemMon dd2 = new DaudiemMon();dd2.setHeso(0.2);
		DaudiemMon dd3 = new DaudiemMon();dd3.setHeso(0.1);
		DaudiemMon dd4 = new DaudiemMon();dd4.setHeso(0.6);
		
		KetQua kq1 = new KetQua();kq1.setDiemtp(dd1);kq1.setDiem(10.0);
		KetQua kq2 = new KetQua();kq2.setDiemtp(dd2);kq2.setDiem(10.0);
		KetQua kq3 = new KetQua();kq3.setDiemtp(dd3);kq3.setDiem(10.0);
		KetQua kq4 = new KetQua();kq4.setDiemtp(dd4);kq4.setDiem(10.0);
		
		mon.getDsdiemtp().add(kq4);mon.getDsdiemtp().add(kq1);mon.getDsdiemtp().add(kq2);mon.getDsdiemtp().add(kq3);
		
		tkDAO.calculateScore(mon); 
		
		String diemTBc = "A+";
		Double diemTB4 = 4.0;
		Double diemTB10 = 10.0;
		String ketqua = "Đạt";
		assertEquals(diemTBc , mon.getDiemTBc()); // Check điểm TB chữ
		assertEquals(diemTB4 , mon.getDiemTB4()); // Check điểm TB 4
		assertEquals(diemTB10 , mon.getDiemTB10()); // Check điểm TB 10
		assertEquals(ketqua , mon.getKetqua()); // Check kết quả
	}
	
	@Test
	public void testCalculateScoreException7() throws Exception { // Check A+ 2
		DangkiMon mon = new DangkiMon();
		
		DaudiemMon dd1 = new DaudiemMon();dd1.setHeso(0.1);
		DaudiemMon dd2 = new DaudiemMon();dd2.setHeso(0.2);
		DaudiemMon dd3 = new DaudiemMon();dd3.setHeso(0.1);
		DaudiemMon dd4 = new DaudiemMon();dd4.setHeso(0.6);
		
		KetQua kq1 = new KetQua();kq1.setDiemtp(dd1);kq1.setDiem(6.0);
		KetQua kq2 = new KetQua();kq2.setDiemtp(dd2);kq2.setDiem(10.0);
		KetQua kq3 = new KetQua();kq3.setDiemtp(dd3);kq3.setDiem(4.0);
		KetQua kq4 = new KetQua();kq4.setDiemtp(dd4);kq4.setDiem(10.0);
		
		mon.getDsdiemtp().add(kq4);mon.getDsdiemtp().add(kq1);mon.getDsdiemtp().add(kq2);mon.getDsdiemtp().add(kq3);
		
		tkDAO.calculateScore(mon); 
		
		String diemTBc = "A+";
		Double diemTB4 = 4.0;
		Double diemTB10 = 9.0;
		String ketqua = "Đạt";
		assertEquals(diemTBc , mon.getDiemTBc()); // Check điểm TB chữ
		assertEquals(diemTB4 , mon.getDiemTB4()); // Check điểm TB 4
		assertEquals(diemTB10 , mon.getDiemTB10()); // Check điểm TB 10
		assertEquals(ketqua , mon.getKetqua()); // Check kết quả
	}
	
	@Test
	public void testCalculateScoreException8() throws Exception { // Check A 1
		DangkiMon mon = new DangkiMon();
		
		DaudiemMon dd1 = new DaudiemMon();dd1.setHeso(0.1);
		DaudiemMon dd2 = new DaudiemMon();dd2.setHeso(0.2);
		DaudiemMon dd3 = new DaudiemMon();dd3.setHeso(0.1);
		DaudiemMon dd4 = new DaudiemMon();dd4.setHeso(0.6);
		
		KetQua kq1 = new KetQua();kq1.setDiemtp(dd1);kq1.setDiem(10.0);
		KetQua kq2 = new KetQua();kq2.setDiemtp(dd2);kq2.setDiem(10.0);
		KetQua kq3 = new KetQua();kq3.setDiemtp(dd3);kq3.setDiem(10.0);
		KetQua kq4 = new KetQua();kq4.setDiemtp(dd4);kq4.setDiem(7.9);
		
		
		mon.getDsdiemtp().add(kq4);mon.getDsdiemtp().add(kq1);mon.getDsdiemtp().add(kq2);mon.getDsdiemtp().add(kq3);
		
		tkDAO.calculateScore(mon); 
		
		String diemTBc = "A";
		Double diemTB4 = 3.7;
		Double diemTB10 = 8.7;
		String ketqua = "Đạt";
		assertEquals(diemTBc , mon.getDiemTBc()); // Check điểm TB chữ
		assertEquals(diemTB4 , mon.getDiemTB4()); // Check điểm TB 4
		assertEquals(diemTB10 , mon.getDiemTB10()); // Check điểm TB 10
		assertEquals(ketqua , mon.getKetqua()); // Check kết quả
	}
	
	@Test
	public void testCalculateScoreException9() throws Exception { // Check A 2
		DangkiMon mon = new DangkiMon();
		
		DaudiemMon dd1 = new DaudiemMon();dd1.setHeso(0.1);
		DaudiemMon dd2 = new DaudiemMon();dd2.setHeso(0.2);
		DaudiemMon dd3 = new DaudiemMon();dd3.setHeso(0.1);
		DaudiemMon dd4 = new DaudiemMon();dd4.setHeso(0.6);
		
		KetQua kq1 = new KetQua();kq1.setDiemtp(dd1);kq1.setDiem(10.0);
		KetQua kq2 = new KetQua();kq2.setDiemtp(dd2);kq2.setDiem(10.0);
		KetQua kq3 = new KetQua();kq3.setDiemtp(dd3);kq3.setDiem(10.0);
		KetQua kq4 = new KetQua();kq4.setDiemtp(dd4);kq4.setDiem(7.5);
		
		mon.getDsdiemtp().add(kq4);mon.getDsdiemtp().add(kq1);mon.getDsdiemtp().add(kq2);mon.getDsdiemtp().add(kq3);
		
		tkDAO.calculateScore(mon); 
		
		String diemTBc = "A";
		Double diemTB4 = 3.7;
		Double diemTB10 = 8.5;
		String ketqua = "Đạt";
		assertEquals(diemTBc , mon.getDiemTBc()); // Check điểm TB chữ
		assertEquals(diemTB4 , mon.getDiemTB4()); // Check điểm TB 4
		assertEquals(diemTB10 , mon.getDiemTB10()); // Check điểm TB 10
		assertEquals(ketqua , mon.getKetqua()); // Check kết quả
	}
	
	@Test
	public void testCalculateScoreException10() throws Exception { // Check B+ 1
		DangkiMon mon = new DangkiMon();
		
		DaudiemMon dd1 = new DaudiemMon();dd1.setHeso(0.1);
		DaudiemMon dd2 = new DaudiemMon();dd2.setHeso(0.2);
		DaudiemMon dd3 = new DaudiemMon();dd3.setHeso(0.1);
		DaudiemMon dd4 = new DaudiemMon();dd4.setHeso(0.6);
		
		KetQua kq1 = new KetQua();kq1.setDiemtp(dd1);kq1.setDiem(10.0);
		KetQua kq2 = new KetQua();kq2.setDiemtp(dd2);kq2.setDiem(10.0);
		KetQua kq3 = new KetQua();kq3.setDiemtp(dd3);kq3.setDiem(10.0);
		KetQua kq4 = new KetQua();kq4.setDiemtp(dd4);kq4.setDiem(7.0);
		
		mon.getDsdiemtp().add(kq4);mon.getDsdiemtp().add(kq1);mon.getDsdiemtp().add(kq2);mon.getDsdiemtp().add(kq3);
		
		tkDAO.calculateScore(mon); 
		
		String diemTBc = "B+";
		Double diemTB4 = 3.5;
		Double diemTB10 = 8.2;
		String ketqua = "Đạt";
		assertEquals(diemTBc , mon.getDiemTBc()); // Check điểm TB chữ
		assertEquals(diemTB4 , mon.getDiemTB4()); // Check điểm TB 4
		assertEquals(diemTB10 , mon.getDiemTB10()); // Check điểm TB 10
		assertEquals(ketqua , mon.getKetqua()); // Check kết quả
	}
	
	@Test
	public void testCalculateScoreException11() throws Exception { // Check B+ 2
		DangkiMon mon = new DangkiMon();
		
		DaudiemMon dd1 = new DaudiemMon();dd1.setHeso(0.1);
		DaudiemMon dd2 = new DaudiemMon();dd2.setHeso(0.2);
		DaudiemMon dd3 = new DaudiemMon();dd3.setHeso(0.1);
		DaudiemMon dd4 = new DaudiemMon();dd4.setHeso(0.6);
		
		KetQua kq1 = new KetQua();kq1.setDiemtp(dd1);kq1.setDiem(8.0);
		KetQua kq2 = new KetQua();kq2.setDiemtp(dd2);kq2.setDiem(8.0);
		KetQua kq3 = new KetQua();kq3.setDiemtp(dd3);kq3.setDiem(8.0);
		KetQua kq4 = new KetQua();kq4.setDiemtp(dd4);kq4.setDiem(8.0);
		
		mon.getDsdiemtp().add(kq4);mon.getDsdiemtp().add(kq1);mon.getDsdiemtp().add(kq2);mon.getDsdiemtp().add(kq3);
		
		tkDAO.calculateScore(mon); 
		
		String diemTBc = "B+";
		Double diemTB4 = 3.5;
		Double diemTB10 = 8.0;
		String ketqua = "Đạt";
		assertEquals(diemTBc , mon.getDiemTBc()); // Check điểm TB chữ
		assertEquals(diemTB4 , mon.getDiemTB4()); // Check điểm TB 4
		assertEquals(diemTB10 , mon.getDiemTB10()); // Check điểm TB 10
		assertEquals(ketqua , mon.getKetqua()); // Check kết quả
	}
	
	@Test
	public void testCalculateScoreException12() throws Exception { // Check B 1
		DangkiMon mon = new DangkiMon();
		
		DaudiemMon dd1 = new DaudiemMon();dd1.setHeso(0.1);
		DaudiemMon dd2 = new DaudiemMon();dd2.setHeso(0.2);
		DaudiemMon dd3 = new DaudiemMon();dd3.setHeso(0.1);
		DaudiemMon dd4 = new DaudiemMon();dd4.setHeso(0.6);
		
		KetQua kq1 = new KetQua();kq1.setDiemtp(dd1);kq1.setDiem(7.5);
		KetQua kq2 = new KetQua();kq2.setDiemtp(dd2);kq2.setDiem(7.5);
		KetQua kq3 = new KetQua();kq3.setDiemtp(dd3);kq3.setDiem(7.5);
		KetQua kq4 = new KetQua();kq4.setDiemtp(dd4);kq4.setDiem(7.5);
		
		mon.getDsdiemtp().add(kq4);mon.getDsdiemtp().add(kq1);mon.getDsdiemtp().add(kq2);mon.getDsdiemtp().add(kq3);
		
		tkDAO.calculateScore(mon); 
		
		String diemTBc = "B";
		Double diemTB4 = 3.0;
		Double diemTB10 = 7.5;
		String ketqua = "Đạt";
		assertEquals(diemTBc , mon.getDiemTBc()); // Check điểm TB chữ
		assertEquals(diemTB4 , mon.getDiemTB4()); // Check điểm TB 4
		assertEquals(diemTB10 , mon.getDiemTB10()); // Check điểm TB 10
		assertEquals(ketqua , mon.getKetqua()); // Check kết quả
	}
	
	@Test
	public void testCalculateScoreException13() throws Exception { // Check B 2
		DangkiMon mon = new DangkiMon();
		
		DaudiemMon dd1 = new DaudiemMon();dd1.setHeso(0.1);
		DaudiemMon dd2 = new DaudiemMon();dd2.setHeso(0.2);
		DaudiemMon dd3 = new DaudiemMon();dd3.setHeso(0.1);
		DaudiemMon dd4 = new DaudiemMon();dd4.setHeso(0.6);
		
		KetQua kq1 = new KetQua();kq1.setDiemtp(dd1);kq1.setDiem(7.0);
		KetQua kq2 = new KetQua();kq2.setDiemtp(dd2);kq2.setDiem(7.0);
		KetQua kq3 = new KetQua();kq3.setDiemtp(dd3);kq3.setDiem(7.0);
		KetQua kq4 = new KetQua();kq4.setDiemtp(dd4);kq4.setDiem(7.0);
		
		mon.getDsdiemtp().add(kq4);mon.getDsdiemtp().add(kq1);mon.getDsdiemtp().add(kq2);mon.getDsdiemtp().add(kq3);
		
		tkDAO.calculateScore(mon); 
		
		String diemTBc = "B";
		Double diemTB4 = 3.0;
		Double diemTB10 = 7.0;
		String ketqua = "Đạt";
		assertEquals(diemTBc , mon.getDiemTBc()); // Check điểm TB chữ
		assertEquals(diemTB4 , mon.getDiemTB4()); // Check điểm TB 4
		assertEquals(diemTB10 , mon.getDiemTB10()); // Check điểm TB 10
		assertEquals(ketqua , mon.getKetqua()); // Check kết quả
	}
	
	@Test
	public void testCalculateScoreException14() throws Exception { // Check C+ 1
		DangkiMon mon = new DangkiMon();
		
		DaudiemMon dd1 = new DaudiemMon();dd1.setHeso(0.1);
		DaudiemMon dd2 = new DaudiemMon();dd2.setHeso(0.2);
		DaudiemMon dd3 = new DaudiemMon();dd3.setHeso(0.1);
		DaudiemMon dd4 = new DaudiemMon();dd4.setHeso(0.6);
		
		KetQua kq1 = new KetQua();kq1.setDiemtp(dd1);kq1.setDiem(6.7);
		KetQua kq2 = new KetQua();kq2.setDiemtp(dd2);kq2.setDiem(6.7);
		KetQua kq3 = new KetQua();kq3.setDiemtp(dd3);kq3.setDiem(6.7);
		KetQua kq4 = new KetQua();kq4.setDiemtp(dd4);kq4.setDiem(6.7);
		
		mon.getDsdiemtp().add(kq4);mon.getDsdiemtp().add(kq1);mon.getDsdiemtp().add(kq2);mon.getDsdiemtp().add(kq3);
		
		tkDAO.calculateScore(mon); 
		
		String diemTBc = "C+";
		Double diemTB4 = 2.5;
		Double diemTB10 = 6.7;
		String ketqua = "Đạt";
		assertEquals(diemTBc , mon.getDiemTBc()); // Check điểm TB chữ
		assertEquals(diemTB4 , mon.getDiemTB4()); // Check điểm TB 4
		assertEquals(diemTB10 , mon.getDiemTB10()); // Check điểm TB 10
		assertEquals(ketqua , mon.getKetqua()); // Check kết quả
	}
	
	@Test
	public void testCalculateScoreException15() throws Exception { // Check C+ 2
		DangkiMon mon = new DangkiMon();
		
		DaudiemMon dd1 = new DaudiemMon();dd1.setHeso(0.1);
		DaudiemMon dd2 = new DaudiemMon();dd2.setHeso(0.2);
		DaudiemMon dd3 = new DaudiemMon();dd3.setHeso(0.1);
		DaudiemMon dd4 = new DaudiemMon();dd4.setHeso(0.6);
		
		KetQua kq1 = new KetQua();kq1.setDiemtp(dd1);kq1.setDiem(6.5);
		KetQua kq2 = new KetQua();kq2.setDiemtp(dd2);kq2.setDiem(6.5);
		KetQua kq3 = new KetQua();kq3.setDiemtp(dd3);kq3.setDiem(6.5);
		KetQua kq4 = new KetQua();kq4.setDiemtp(dd4);kq4.setDiem(6.5);
		
		mon.getDsdiemtp().add(kq4);mon.getDsdiemtp().add(kq1);mon.getDsdiemtp().add(kq2);mon.getDsdiemtp().add(kq3);
		
		tkDAO.calculateScore(mon); 
		
		String diemTBc = "C+";
		Double diemTB4 = 2.5;
		Double diemTB10 = 6.5;
		String ketqua = "Đạt";
		assertEquals(diemTBc , mon.getDiemTBc()); // Check điểm TB chữ
		assertEquals(diemTB4 , mon.getDiemTB4()); // Check điểm TB 4
		assertEquals(diemTB10 , mon.getDiemTB10()); // Check điểm TB 10
		assertEquals(ketqua , mon.getKetqua()); // Check kết quả
	}
	
	@Test
	public void testCalculateScoreException16() throws Exception { // Check C 1
		DangkiMon mon = new DangkiMon();
		
		DaudiemMon dd1 = new DaudiemMon();dd1.setHeso(0.1);
		DaudiemMon dd2 = new DaudiemMon();dd2.setHeso(0.2);
		DaudiemMon dd3 = new DaudiemMon();dd3.setHeso(0.1);
		DaudiemMon dd4 = new DaudiemMon();dd4.setHeso(0.6);
		
		KetQua kq1 = new KetQua();kq1.setDiemtp(dd1);kq1.setDiem(5.7);
		KetQua kq2 = new KetQua();kq2.setDiemtp(dd2);kq2.setDiem(5.7);
		KetQua kq3 = new KetQua();kq3.setDiemtp(dd3);kq3.setDiem(5.7);
		KetQua kq4 = new KetQua();kq4.setDiemtp(dd4);kq4.setDiem(5.7);
		
		mon.getDsdiemtp().add(kq4);mon.getDsdiemtp().add(kq1);mon.getDsdiemtp().add(kq2);mon.getDsdiemtp().add(kq3);
		
		tkDAO.calculateScore(mon); 
		
		String diemTBc = "C";
		Double diemTB4 = 2.0;
		Double diemTB10 = 5.7;
		String ketqua = "Đạt";
		assertEquals(diemTBc , mon.getDiemTBc()); // Check điểm TB chữ
		assertEquals(diemTB4 , mon.getDiemTB4()); // Check điểm TB 4
		assertEquals(diemTB10 , mon.getDiemTB10()); // Check điểm TB 10
		assertEquals(ketqua , mon.getKetqua()); // Check kết quả
	}
	
	@Test
	public void testCalculateScoreException17() throws Exception { // Check C 2
		DangkiMon mon = new DangkiMon();
		
		DaudiemMon dd1 = new DaudiemMon();dd1.setHeso(0.1);
		DaudiemMon dd2 = new DaudiemMon();dd2.setHeso(0.2);
		DaudiemMon dd3 = new DaudiemMon();dd3.setHeso(0.1);
		DaudiemMon dd4 = new DaudiemMon();dd4.setHeso(0.6);
		
		KetQua kq1 = new KetQua();kq1.setDiemtp(dd1);kq1.setDiem(5.5);
		KetQua kq2 = new KetQua();kq2.setDiemtp(dd2);kq2.setDiem(5.5);
		KetQua kq3 = new KetQua();kq3.setDiemtp(dd3);kq3.setDiem(5.5);
		KetQua kq4 = new KetQua();kq4.setDiemtp(dd4);kq4.setDiem(5.5);
		
		mon.getDsdiemtp().add(kq4);mon.getDsdiemtp().add(kq1);mon.getDsdiemtp().add(kq2);mon.getDsdiemtp().add(kq3);
		
		tkDAO.calculateScore(mon); 
		
		String diemTBc = "C";
		Double diemTB4 = 2.0;
		Double diemTB10 = 5.5;
		String ketqua = "Đạt";
		assertEquals(diemTBc , mon.getDiemTBc()); // Check điểm TB chữ
		assertEquals(diemTB4 , mon.getDiemTB4()); // Check điểm TB 4
		assertEquals(diemTB10 , mon.getDiemTB10()); // Check điểm TB 10
		assertEquals(ketqua , mon.getKetqua()); // Check kết quả
	}
	
	@Test
	public void testCalculateScoreException18() throws Exception { // Check D+ 1
		DangkiMon mon = new DangkiMon();
		
		DaudiemMon dd1 = new DaudiemMon();dd1.setHeso(0.1);
		DaudiemMon dd2 = new DaudiemMon();dd2.setHeso(0.2);
		DaudiemMon dd3 = new DaudiemMon();dd3.setHeso(0.1);
		DaudiemMon dd4 = new DaudiemMon();dd4.setHeso(0.6);
		
		KetQua kq1 = new KetQua();kq1.setDiemtp(dd1);kq1.setDiem(5.2);
		KetQua kq2 = new KetQua();kq2.setDiemtp(dd2);kq2.setDiem(5.2);
		KetQua kq3 = new KetQua();kq3.setDiemtp(dd3);kq3.setDiem(5.2);
		KetQua kq4 = new KetQua();kq4.setDiemtp(dd4);kq4.setDiem(5.2);
		
		mon.getDsdiemtp().add(kq4);mon.getDsdiemtp().add(kq1);mon.getDsdiemtp().add(kq2);mon.getDsdiemtp().add(kq3);
		
		tkDAO.calculateScore(mon); 
		
		String diemTBc = "D+";
		Double diemTB4 = 1.5;
		Double diemTB10 = 5.2;
		String ketqua = "Đạt";
		assertEquals(diemTBc , mon.getDiemTBc()); // Check điểm TB chữ
		assertEquals(diemTB4 , mon.getDiemTB4()); // Check điểm TB 4
		assertEquals(diemTB10 , mon.getDiemTB10()); // Check điểm TB 10
		assertEquals(ketqua , mon.getKetqua()); // Check kết quả
	}
	
	@Test
	public void testCalculateScoreException19() throws Exception { // Check D+ 2
		DangkiMon mon = new DangkiMon();
		
		DaudiemMon dd1 = new DaudiemMon();dd1.setHeso(0.1);
		DaudiemMon dd2 = new DaudiemMon();dd2.setHeso(0.2);
		DaudiemMon dd3 = new DaudiemMon();dd3.setHeso(0.1);
		DaudiemMon dd4 = new DaudiemMon();dd4.setHeso(0.6);
		
		KetQua kq1 = new KetQua();kq1.setDiemtp(dd1);kq1.setDiem(5.0);
		KetQua kq2 = new KetQua();kq2.setDiemtp(dd2);kq2.setDiem(5.0);
		KetQua kq3 = new KetQua();kq3.setDiemtp(dd3);kq3.setDiem(5.0);
		KetQua kq4 = new KetQua();kq4.setDiemtp(dd4);kq4.setDiem(5.0);
		
		mon.getDsdiemtp().add(kq4);mon.getDsdiemtp().add(kq1);mon.getDsdiemtp().add(kq2);mon.getDsdiemtp().add(kq3);
		
		tkDAO.calculateScore(mon); 
		
		String diemTBc = "D+";
		Double diemTB4 = 1.5;
		Double diemTB10 = 5.0;
		String ketqua = "Đạt";
		assertEquals(diemTBc , mon.getDiemTBc()); // Check điểm TB chữ
		assertEquals(diemTB4 , mon.getDiemTB4()); // Check điểm TB 4
		assertEquals(diemTB10 , mon.getDiemTB10()); // Check điểm TB 10
		assertEquals(ketqua , mon.getKetqua()); // Check kết quả
	}
	
	@Test
	public void testCalculateScoreException20() throws Exception { // Check D 1
		DangkiMon mon = new DangkiMon();
		
		DaudiemMon dd1 = new DaudiemMon();dd1.setHeso(0.1);
		DaudiemMon dd2 = new DaudiemMon();dd2.setHeso(0.2);
		DaudiemMon dd3 = new DaudiemMon();dd3.setHeso(0.1);
		DaudiemMon dd4 = new DaudiemMon();dd4.setHeso(0.6);
		
		KetQua kq1 = new KetQua();kq1.setDiemtp(dd1);kq1.setDiem(10.0); //1
		KetQua kq2 = new KetQua();kq2.setDiemtp(dd2);kq2.setDiem(5.0); // 1
		KetQua kq3 = new KetQua();kq3.setDiemtp(dd3);kq3.setDiem(2.0); // 0.2
		KetQua kq4 = new KetQua();kq4.setDiemtp(dd4);kq4.setDiem(3.0); // 1.8 
		
		mon.getDsdiemtp().add(kq4);mon.getDsdiemtp().add(kq1);mon.getDsdiemtp().add(kq2);mon.getDsdiemtp().add(kq3);
		
		tkDAO.calculateScore(mon); 
		
		String diemTBc = "D";
		Double diemTB4 = 1.0;
		Double diemTB10 = 4.0;
		String ketqua = "Đạt";
		assertEquals(diemTBc , mon.getDiemTBc()); // Check điểm TB chữ
		assertEquals(diemTB4 , mon.getDiemTB4()); // Check điểm TB 4
		assertEquals(diemTB10 , mon.getDiemTB10()); // Check điểm TB 10
		assertEquals(ketqua , mon.getKetqua()); // Check kết quả
	}
	
	@Test
	public void testCalculateScoreException21() throws Exception { // Check D 2
		DangkiMon mon = new DangkiMon();
		
		DaudiemMon dd1 = new DaudiemMon();dd1.setHeso(0.1);
		DaudiemMon dd2 = new DaudiemMon();dd2.setHeso(0.2);
		DaudiemMon dd3 = new DaudiemMon();dd3.setHeso(0.1);
		DaudiemMon dd4 = new DaudiemMon();dd4.setHeso(0.6);
		
		KetQua kq1 = new KetQua();kq1.setDiemtp(dd1);kq1.setDiem(10.0); //1
		KetQua kq2 = new KetQua();kq2.setDiemtp(dd2);kq2.setDiem(5.0); // 1
		KetQua kq3 = new KetQua();kq3.setDiemtp(dd3);kq3.setDiem(7.0); // 0.7
		KetQua kq4 = new KetQua();kq4.setDiemtp(dd4);kq4.setDiem(3.0); // 1.8 
		
		mon.getDsdiemtp().add(kq4);mon.getDsdiemtp().add(kq1);mon.getDsdiemtp().add(kq2);mon.getDsdiemtp().add(kq3);
		
		tkDAO.calculateScore(mon); 
		
		String diemTBc = "D";
		Double diemTB4 = 1.0;
		Double diemTB10 = 4.5;
		String ketqua = "Đạt";
		assertEquals(diemTBc , mon.getDiemTBc()); // Check điểm TB chữ
		assertEquals(diemTB4 , mon.getDiemTB4()); // Check điểm TB 4
		assertEquals(diemTB10 , mon.getDiemTB10()); // Check điểm TB 10
		assertEquals(ketqua , mon.getKetqua()); // Check kết quả
	}
	
	@Test
	public void testCalculateScoreException22() throws Exception { // Check F bằng thực lực :))))
		DangkiMon mon = new DangkiMon();
		
		DaudiemMon dd1 = new DaudiemMon();dd1.setHeso(0.1);
		DaudiemMon dd2 = new DaudiemMon();dd2.setHeso(0.2);
		DaudiemMon dd3 = new DaudiemMon();dd3.setHeso(0.1);
		DaudiemMon dd4 = new DaudiemMon();dd4.setHeso(0.6);
		
		KetQua kq1 = new KetQua();kq1.setDiemtp(dd1);kq1.setDiem(6.0); //0.6
		KetQua kq2 = new KetQua();kq2.setDiemtp(dd2);kq2.setDiem(5.0); // 1
		KetQua kq3 = new KetQua();kq3.setDiemtp(dd3);kq3.setDiem(3.0); // 0.3
		KetQua kq4 = new KetQua();kq4.setDiemtp(dd4);kq4.setDiem(3.0); // 1.8 
		
		mon.getDsdiemtp().add(kq4);mon.getDsdiemtp().add(kq1);mon.getDsdiemtp().add(kq2);mon.getDsdiemtp().add(kq3);
		
		tkDAO.calculateScore(mon); 
		
		String diemTBc = "F";
		Double diemTB4 = 0.0;
		Double diemTB10 = 3.7;
		String ketqua = "Không đạt";
		assertEquals(diemTBc , mon.getDiemTBc()); // Check điểm TB chữ
		assertEquals(diemTB4 , mon.getDiemTB4()); // Check điểm TB 4
		assertEquals(diemTB10 , mon.getDiemTB10()); // Check điểm TB 10
		assertEquals(ketqua , mon.getKetqua()); // Check kết quả
	}
	
	@Test
	public void testCalculateScoreException23() throws Exception { // Test GPA 1 môn tạch 1 môn cải thiện
		TKSinhvien sv = new TKSinhvien();
		ArrayList<DangkiMon> list = new ArrayList<DangkiMon>();
		MonhocKihoc monhockihoc1 = new MonhocKihoc();
		monhockihoc1.setMonhoc(new MonHoc(1, "Đại số", 3));
		KiHoc kh1 = new KiHoc();
		kh1.setId(1);
		monhockihoc1.setKihoc(kh1);
		DangkiMon dk1 = new DangkiMon();
		dk1.setMonhoc(monhockihoc1);
		dk1.setDiemTB4(0.0);
		
		MonhocKihoc monhockihoc2 = new MonhocKihoc();
		monhockihoc2.setMonhoc(new MonHoc(1, "Đại số", 3));
		KiHoc kh2 = new KiHoc();
		kh2.setId(2);
		monhockihoc2.setKihoc(kh2);
		DangkiMon dk2 = new DangkiMon();
		dk2.setMonhoc(monhockihoc2);
		dk1.setDiemTB4(4.0);
		
		list.add(dk1); list.add(dk2);
		sv.setDsDangkiMon(list);
		tkDAO.calculateGPA(sv);
		assertEquals(4.0, sv.getDiemTB());
		assertEquals(3, sv.getSoTC());
		assertEquals(0, sv.getSoTCno());
	}
	
	@Test
	public void testCalculateScoreException24() throws Exception { // Test GPA cả 2 đều tạch
		TKSinhvien sv = new TKSinhvien();
		ArrayList<DangkiMon> list = new ArrayList<DangkiMon>();
		MonhocKihoc monhockihoc1 = new MonhocKihoc();
		monhockihoc1.setMonhoc(new MonHoc(1, "Đại số", 3));
		KiHoc kh1 = new KiHoc();
		kh1.setId(1);
		monhockihoc1.setKihoc(kh1);
		DangkiMon dk1 = new DangkiMon();
		dk1.setMonhoc(monhockihoc1);
		dk1.setDiemTB4(0.0);
		
		MonhocKihoc monhockihoc2 = new MonhocKihoc();
		monhockihoc2.setMonhoc(new MonHoc(1, "Đại số", 3));
		KiHoc kh2 = new KiHoc();
		kh2.setId(2);
		monhockihoc2.setKihoc(kh2);
		DangkiMon dk2 = new DangkiMon();
		dk2.setMonhoc(monhockihoc2);
		dk2.setDiemTB4(0.0);
		
		list.add(dk1); list.add(dk2);
		sv.setDsDangkiMon(list);
		tkDAO.calculateGPA(sv);
		assertEquals(0.0, sv.getDiemTB());
		assertEquals(0, sv.getSoTC());
		assertEquals(3, sv.getSoTCno());
	}
	
	@Test
	public void testCalculateScoreException25() throws Exception { // Test GPA cả 2 đều quạ với điểm trước lớn hơn điểm sau
		TKSinhvien sv = new TKSinhvien();
		ArrayList<DangkiMon> list = new ArrayList<DangkiMon>();
		MonhocKihoc monhockihoc1 = new MonhocKihoc();
		monhockihoc1.setMonhoc(new MonHoc(1, "Đại số", 3));
		KiHoc kh1 = new KiHoc();
		kh1.setId(1);
		monhockihoc1.setKihoc(kh1);
		DangkiMon dk1 = new DangkiMon();
		dk1.setMonhoc(monhockihoc1);
		dk1.setDiemTB4(3.0);
		
		MonhocKihoc monhockihoc2 = new MonhocKihoc();
		monhockihoc2.setMonhoc(new MonHoc(1, "Đại số", 3));
		KiHoc kh2 = new KiHoc();
		kh2.setId(2);
		monhockihoc2.setKihoc(kh2);
		DangkiMon dk2 = new DangkiMon();
		dk2.setMonhoc(monhockihoc2);
		dk2.setDiemTB4(2.0);
		
		list.add(dk1); list.add(dk2);
		sv.setDsDangkiMon(list);
		tkDAO.calculateGPA(sv);
		assertEquals(3.0, sv.getDiemTB());
		assertEquals(3, sv.getSoTC());
		assertEquals(0, sv.getSoTCno());
	}
	
	@Test
	public void testCalculateScoreException26() throws Exception { // Test GPA cả 2 đều quạ với điểm đầu sau hơn điểm trước
		TKSinhvien sv = new TKSinhvien();
		ArrayList<DangkiMon> list = new ArrayList<DangkiMon>();
		MonhocKihoc monhockihoc1 = new MonhocKihoc();
		monhockihoc1.setMonhoc(new MonHoc(1, "Đại số", 3));
		KiHoc kh1 = new KiHoc();
		kh1.setId(1);
		monhockihoc1.setKihoc(kh1);
		DangkiMon dk1 = new DangkiMon();
		dk1.setMonhoc(monhockihoc1);
		dk1.setDiemTB4(2.0);
		
		MonhocKihoc monhockihoc2 = new MonhocKihoc();
		monhockihoc2.setMonhoc(new MonHoc(1, "Đại số", 3));
		KiHoc kh2 = new KiHoc();
		kh2.setId(2);
		monhockihoc2.setKihoc(kh2);
		DangkiMon dk2 = new DangkiMon();
		dk2.setMonhoc(monhockihoc2);
		dk2.setDiemTB4(3.0);
		
		list.add(dk1); list.add(dk2);
		sv.setDsDangkiMon(list);
		tkDAO.calculateGPA(sv);
		assertEquals(3.0, sv.getDiemTB());
		assertEquals(3, sv.getSoTC());
		assertEquals(0, sv.getSoTCno());
	}
	
	@Test
	public void testCalculateScoreException27() throws Exception { // Trong học kì có không có môn nào tạch
		TKKihocSv kh = new TKKihocSv();
		ArrayList<DangkiMon> list = new ArrayList<DangkiMon>();
		MonhocKihoc monhockihoc1 = new MonhocKihoc();
		monhockihoc1.setMonhoc(new MonHoc(1, "Đại số", 3));
		KiHoc kh1 = new KiHoc();
		kh1.setId(1);
		monhockihoc1.setKihoc(kh1);
		DangkiMon dk1 = new DangkiMon();
		dk1.setMonhoc(monhockihoc1);
		dk1.setDiemTB4(2.0);
		
		MonhocKihoc monhockihoc2 = new MonhocKihoc();
		monhockihoc2.setMonhoc(new MonHoc(2, "Giải tích", 3));
		KiHoc kh2 = new KiHoc();
		kh2.setId(2);
		monhockihoc2.setKihoc(kh2);
		DangkiMon dk2 = new DangkiMon();
		dk2.setMonhoc(monhockihoc2);
		dk2.setDiemTB4(3.0);
		
		list.add(dk1); list.add(dk2);
		kh.setDsmon(list);
		tkDAO.calculateSemesterAverage(kh);
		assertEquals(2.5, kh.getDiemTB());
		assertEquals(6, kh.getSoTc());
		assertEquals(0, kh.getSoTcno());
	}
	@Test
	public void testCalculateScoreException28() throws Exception { // Trong học kì có môn tạch
		TKKihocSv kh = new TKKihocSv();
		ArrayList<DangkiMon> list = new ArrayList<DangkiMon>();
		MonhocKihoc monhockihoc1 = new MonhocKihoc();
		monhockihoc1.setMonhoc(new MonHoc(1, "Đại số", 3));
		KiHoc kh1 = new KiHoc();
		kh1.setId(1);
		monhockihoc1.setKihoc(kh1);
		DangkiMon dk1 = new DangkiMon();
		dk1.setMonhoc(monhockihoc1);
		dk1.setDiemTB4(2.0);
		
		MonhocKihoc monhockihoc2 = new MonhocKihoc();
		monhockihoc2.setMonhoc(new MonHoc(2, "Giải tích", 3));
		KiHoc kh2 = new KiHoc();
		kh2.setId(2);
		monhockihoc2.setKihoc(kh2);
		DangkiMon dk2 = new DangkiMon();
		dk2.setMonhoc(monhockihoc2);
		dk2.setDiemTB4(0.0);
		
		list.add(dk1); list.add(dk2);
		kh.setDsmon(list);
		tkDAO.calculateSemesterAverage(kh);
		assertEquals(1.0, kh.getDiemTB());
		assertEquals(3, kh.getSoTc());
		assertEquals(3, kh.getSoTcno());
	}
}


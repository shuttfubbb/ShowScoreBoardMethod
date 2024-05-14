package com.example.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.example.model.TKSinhvien;
import com.example.model.DangkiMon;
import com.example.model.DauDiem;
import com.example.model.DaudiemMon;
import com.example.model.KetQua;
import com.example.model.KiHoc;
import com.example.model.MonHoc;
import com.example.model.MonhocKihoc;
import com.example.model.NamHoc;
import com.example.model.TKKihocSv;


public class TKSinhvienDAO extends DAO{
	
	public TKSinhvienDAO() {
		super();
	}
	
	public void calculateScore(DangkiMon mon) {
		// Nếu điểm thành phần không đạt điều kiện thi
		if(mon.getDsdiemtp().get(1).getDiem() < 5 || mon.getDsdiemtp().get(2).getDiem()  <= 0 || mon.getDsdiemtp().get(3).getDiem()  <= 0) {
    		mon.setDiemTB10(0.0);
        	mon.setDiemTB4(0.0);
    		mon.setDiemTBc("F");
    		mon.setKetqua("Không đạt");
    	}
    	else {
    		// Tinh diem he 10
        	for(int j=0; j<mon.getDsdiemtp().size(); ++j) {
        		KetQua kq = mon.getDsdiemtp().get(j);
        		mon.setDiemTB10(mon.getDiemTB10() + kq.getDiem() * kq.getDiemtp().getHeso());
        	}
        	mon.setDiemTB10(Math.round(mon.getDiemTB10() * 10.0) / 10.0);
        	mon.setKetqua("Đạt");
        	// Chuyen diem he 4
        	if(mon.getDiemTB10() >= 9.0) {
        		mon.setDiemTB4(4.0);
        		mon.setDiemTBc("A+");
        	}
        	else if(mon.getDiemTB10() >= 8.5 ) {
        		mon.setDiemTB4(3.7);
        		mon.setDiemTBc("A");
        	}
        	else if(mon.getDiemTB10() >= 8.0) {
        		mon.setDiemTB4(3.5);
        		mon.setDiemTBc("B+");
        	}
        	else if(mon.getDiemTB10() >= 7.0) {
        		mon.setDiemTB4(3.0);
        		mon.setDiemTBc("B");
        	}
        	else if(mon.getDiemTB10() >= 6.5) {
        		mon.setDiemTB4(2.5);
        		mon.setDiemTBc("C+");
        	}
			else if(mon.getDiemTB10() >= 5.5) {
				mon.setDiemTB4(2.0);
        		mon.setDiemTBc("C");		
        	}
			else if(mon.getDiemTB10() >= 5.0) {
				mon.setDiemTB4(1.5);
        		mon.setDiemTBc("D+");	
			}
            else if(mon.getDiemTB10() >= 4.0) {
            	mon.setDiemTB4(1.0);
        		mon.setDiemTBc("D");
        	}
            else {
            	mon.setDiemTB4(0.0);
        		mon.setDiemTBc("F");
        		mon.setKetqua("Không đạt");
            }
    	}
	}
	
	public void calculateSemesterAverage(TKKihocSv kihoc) {
		Double sum = 0.0;
		for(int j=0; j<kihoc.getDsmon().size(); ++j) {
    		DangkiMon mon = kihoc.getDsmon().get(j);
    		sum += mon.getDiemTB4() * mon.getMonhoc().getMonhoc().getSoTC();
    		if(mon.getDiemTB4() != 0) {
    			kihoc.setSoTc(kihoc.getSoTc() + mon.getMonhoc().getMonhoc().getSoTC());
    		}
    		else {
    			kihoc.setSoTcno(kihoc.getSoTcno() + mon.getMonhoc().getMonhoc().getSoTC());
    		}
    	}
    	kihoc.setDiemTB(Math.round(sum / (kihoc.getSoTc() + kihoc.getSoTcno()) * 100.0) / 100.0);
	}
	
	public void calculateGPA(TKSinhvien sv) {
        Double tongdiemtmp = 0.0;
		Map<Integer, DangkiMon> myMap = new HashMap<>();
        for(int i=0; i<sv.getDsDangkiMon().size(); ++i) {
        	DangkiMon mon = sv.getDsDangkiMon().get(i);
        	// Xử lý học cải thiện
        	if(myMap.containsKey(mon.getMonhoc().getMonhoc().getId())) {
        		DangkiMon mon1 = myMap.get(mon.getMonhoc().getMonhoc().getId());
        		if(mon.getDiemTB4() > mon1.getDiemTB4()) {
        			myMap.put(mon.getMonhoc().getMonhoc().getId(), mon);
        		}
        	}
        	else {
        		myMap.put(mon.getMonhoc().getMonhoc().getId(), mon);
        	}
        }
        // Tính tổng điểm
        for(Map.Entry<Integer, DangkiMon> x : myMap.entrySet()) {
        	tongdiemtmp += x.getValue().getDiemTB4() * x.getValue().getMonhoc().getMonhoc().getSoTC();
        	if(x.getValue().getDiemTB4() != 0) {
        		sv.setSoTC(sv.getSoTC() + x.getValue().getMonhoc().getMonhoc().getSoTC());
        	}
        	else {
        		sv.setSoTCno(sv.getSoTCno() + x.getValue().getMonhoc().getMonhoc().getSoTC());
        	}
        }
        sv.setDiemTB(Math.round(tongdiemtmp / (sv.getSoTC() + sv.getSoTCno()) * 100.0) / 100.0);
	}
	
	public boolean getScoreboard(TKSinhvien sv) {
        String sql = "SELECT * FROM (SELECT * FROM tester.dangkimon "
        		+ "WHERE idSinhvien = ? ) AS a1 "
        		+ "INNER JOIN tester.ketqua AS a2 ON a1.idKetqua = a2.id "
        		+ "INNER JOIN tester.daudiemmon AS a3 ON a2.idDaudiemmon = a3.id "
        		+ "INNER JOIN tester.monhockihoc AS a4 ON a1.idMonhockihoc = a4.id "
        		+ "INNER JOIN tester.monhoc AS a6 ON a4.idMonhoc = a6.id "
        		+ "INNER JOIN tester.kihoc AS a7 ON a4.idKihoc = a7.id "
        		+ "INNER JOIN tester.namhoc AS a8 ON a7.idNamhoc = a8.id "
        		+ "INNER JOIN tester.daudiem AS a9 ON a3.idDaudiem = a9.id; ";
        try{
        	PreparedStatement  ps = con.prepareStatement(sql);
        	ps.setInt(1, sv.getId());
            ResultSet rs = ps.executeQuery();
            Integer idtmp = 0;
            while(rs.next()){
            	NamHoc nh = new NamHoc();
            	nh.setId(rs.getInt(22));
            	nh.setTen(rs.getString(23));
            	
            	KiHoc kh = new KiHoc();
            	kh.setId(rs.getInt(17));
                kh.setTen(rs.getString(18));
                kh.setNamhoc(nh);
                kh.setDanghoc(rs.getInt(20) > 0);
                kh.setDangdk(rs.getInt(21) > 0);
                
                MonHoc mn = new MonHoc();
                mn.setId(rs.getInt(14));
                mn.setTen(rs.getString(15));
                mn.setSoTC(rs.getInt(16));
                
                DauDiem dd = new DauDiem();
                dd.setId(rs.getInt(24));
                dd.setTen(rs.getString(25));

                Integer idcheck = rs.getInt(11);
                if(idcheck != idtmp) {
                	idtmp = idcheck;
                	MonhocKihoc mhkh = new MonhocKihoc();
                    mhkh.setId(rs.getInt(11));
                    mhkh.setMonhoc(mn);
                    mhkh.setKihoc(kh);
                    
                    DaudiemMon ddm = new DaudiemMon();
                    ddm.setId(rs.getInt(8));
                    ddm.setDaudiem(dd);
                    ddm.setHeso(rs.getDouble(10));
                    
                    KetQua kq = new KetQua();
                    kq.setId(rs.getInt(5));
                    kq.setDiemtp(ddm);
                    kq.setDiem(rs.getDouble(7));
                    
                    DangkiMon dkm = new DangkiMon();
                    dkm.setId(rs.getInt(1));
                    dkm.setSv(sv);
                    dkm.setMonhoc(mhkh);
                    if(dd.getTen().equals("Thi")) {
                    	dkm.setDiemThi(kq.getDiem());
                    }
                    dkm.getDsdiemtp().add(kq);
                    
                    sv.getDsDangkiMon().add(dkm);
                }
                else {
                	DaudiemMon ddm = new DaudiemMon();
                    ddm.setId(rs.getInt(8));
                    ddm.setDaudiem(dd);
                    ddm.setHeso(rs.getDouble(10));
                    
                    KetQua kq = new KetQua();
                    kq.setId(rs.getInt(5));
                    kq.setDiemtp(ddm);
                    kq.setDiem(rs.getDouble(7));
                    
                    sv.getDsDangkiMon().get(sv.getDsDangkiMon().size()-1).getDsdiemtp().add(kq);
                }
            }
            // Tính điểm từng môn
            for(int i=0; i<sv.getDsDangkiMon().size(); ++i) {
            	DangkiMon mon = sv.getDsDangkiMon().get(i);
            	calculateScore(mon);
            }
            // Xử lý kì học
            for(int i=0; i<sv.getDsDangkiMon().size(); ++i) {
            	DangkiMon mon = sv.getDsDangkiMon().get(i);
            	ArrayList<TKKihocSv> dsKihoc = sv.getDsTKKihocSv();
            	Integer dd = 0;
            	// Thêm môn vào danh sách môn trong 1 kì học đã tồn tại
            	for(int j=0; j<dsKihoc.size(); ++j) {
            		TKKihocSv kihoc = dsKihoc.get(j);
            		if(mon.getMonhoc().getKihoc().getId() == kihoc.getId()) {
            			kihoc.getDsmon().add(mon);
            			// Đánh dấu là có tồn tại kì học
            			dd = 1;
            			break;
            		}
            	}
            	if(dd == 0) { // Nếu kì học chưa tồn tại => Thêm 1 kì học mới 
            		TKKihocSv kihoc = new TKKihocSv();
            		dsKihoc.add(kihoc);
            			
            		kihoc.setId(mon.getMonhoc().getKihoc().getId());
            		kihoc.setTen(mon.getMonhoc().getKihoc().getTen());
            		kihoc.setNamhoc(mon.getMonhoc().getKihoc().getNamhoc());
            		kihoc.setDangdk(mon.getMonhoc().getKihoc().isDangdk());
            		kihoc.setDanghoc(mon.getMonhoc().getKihoc().isDanghoc());
            		kihoc.getDsmon().add(mon);
            	}
            }
            
            calculateGPA(sv);
            
            // Tính điểm TB từng kỳ học
            for(int i=0; i<sv.getDsTKKihocSv().size(); ++i) {
            	TKKihocSv kihoc = sv.getDsTKKihocSv().get(i);
            	calculateSemesterAverage(kihoc);
            }
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
	}
}

package com.example.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.example.model.SinhVien;

public class SinhVienDAO extends DAO{
	public SinhVienDAO() {
		super();
	}
	
	private boolean isNotValid(SinhVien ad) {
		return ad.getUsername().contains("true") || ad.getUsername().contains("=")|| ad.getPassword().contains("true") || ad.getPassword().contains("=");
	}
	
	public boolean checkLogin(SinhVien sv) {
        if(isNotValid(sv)) return false;
        String sql = "SELECT * FROM sinhvien WHERE username = ? AND password = ?";
        try{
        	PreparedStatement  ps = con.prepareStatement(sql);
        	ps.setString(1, sv.getUsername());
        	ps.setString(2, sv.getPassword());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
            	sv.setId(rs.getInt("id"));
            	sv.setMasv(rs.getString("masv"));
            	sv.setTen(rs.getString("ten"));
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
	}
}

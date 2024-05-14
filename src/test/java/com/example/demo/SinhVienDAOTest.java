package com.example.demo;

import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;

import com.example.controller.SinhVienDAO;
import com.example.model.SinhVien;

public class SinhVienDAOTest {
	SinhVienDAO svDAO;
	public SinhVienDAOTest(){
		svDAO = new SinhVienDAO();
	}
	
	@Test
	public void testCheckLoginException1() throws Exception { // Check login đúng
		SinhVien sv = new SinhVien();
		
		boolean actual = svDAO.checkLogin(sv);
		assertTrue(actual);
	}
	
	@Test
	public void testCheckLoginException2() throws Exception { // Check user có chứa true
		SinhVien sv = new SinhVien();
		sv.setUsername("true");
		sv.setPassword("123");
		
		boolean actual = svDAO.checkLogin(sv);
		assertFalse(actual);
	}
	
	@Test
	public void testCheckLoginException3() throws Exception { // Check sai tk mk
		SinhVien sv = new SinhVien();
		sv.setUsername("xxxxxxxxx");
		sv.setPassword("12345");
		
		boolean actual = svDAO.checkLogin(sv);
		assertFalse(actual);
	}
}


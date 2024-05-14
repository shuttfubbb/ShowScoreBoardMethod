package com.example.controller;

import java.time.Year;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.SinhVien;

import jakarta.servlet.http.HttpSession;

@Controller	
public class LoginController {
	@GetMapping("/login")
	public String getLoginPage(Model model, HttpSession session){
		return "login";
	}
	@PostMapping("/login")
	public String login(Model model, HttpSession session, @RequestParam("username") String username, @RequestParam("password") String password) {
		SinhVien sv = new SinhVien();
		SinhVienDAO svDAO = new SinhVienDAO();
		sv.setUsername(username);
		sv.setPassword(password);
		if(svDAO.checkLogin(sv)) {
			session.setAttribute("sv", sv);
			return "redirect:/home";
		}
		return "redirect:/login";
	}
	
	@GetMapping("/home")
	public String getAdminPage(Model model, HttpSession session){
		return "home";
	}
}

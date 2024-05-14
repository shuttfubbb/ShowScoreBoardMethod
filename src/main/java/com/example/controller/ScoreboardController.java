package com.example.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.SinhVien;
import com.example.model.TKSinhvien;

import jakarta.servlet.http.HttpSession;

@Controller
public class ScoreboardController {
	@GetMapping("/scoreboard")
	public String getShowScorePage(Model model, HttpSession session) {
		TKSinhvienDAO dao = new TKSinhvienDAO();
		SinhVien sv = (SinhVien)session.getAttribute("sv");
		TKSinhvien tksv = new TKSinhvien();
		tksv.setId(sv.getId());
		tksv.setMasv(sv.getMasv());
		tksv.setTen(sv.getTen());
		tksv.setUsername(sv.getUsername());
		tksv.setPassword(sv.getPassword());
		dao.getScoreboard(tksv);
		session.setAttribute("tksv", tksv);
		return "scoreboard";
	}
}

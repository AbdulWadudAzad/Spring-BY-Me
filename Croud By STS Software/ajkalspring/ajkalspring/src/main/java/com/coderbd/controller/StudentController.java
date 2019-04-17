package com.coderbd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.coderbd.repository.StudentRepo;



@Controller
public class StudentController {
	
	@Autowired
	private StudentRepo repo;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	

	
	
}

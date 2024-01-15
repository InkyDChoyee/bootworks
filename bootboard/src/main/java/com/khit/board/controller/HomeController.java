package com.khit.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

//	@GetMapping("/")
//	public String index() {
//		return "index"; //index.html
//	}
	// thymeleaf에서는 index 매핑을 굳이 해주지 않아도 된다
	
	@GetMapping("/main")
	public String main() {
		return "main";
	}
	
}

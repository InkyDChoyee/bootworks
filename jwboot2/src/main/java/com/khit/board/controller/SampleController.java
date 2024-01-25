package com.khit.board.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController   // 데이터 전달이 역할인 어노테이션임
public class SampleController {
	
	@GetMapping("/sample")  // file 체계가 아닌 rest 체계
	public String test() {
		return "안녕하세요";    // main -> main.html
	}
	
	// GET - select
	@GetMapping("/khit")
	public String httpGet() {
		return "GET 요청 처리";
	}

	// POST - insert
	@PostMapping("/khit")
	public String httpPost() {
		return "POST 요청 처리";
	}
	
	// PUT - update
	@PutMapping("/khit")
	public String httpPut() {
		return "PUT 요청 처리";
	}
	
	// DELETE - delete
	@DeleteMapping("/khit")
	public String httpDelete() {
		return "DELETE 요청 처리";
	}
	
	
}

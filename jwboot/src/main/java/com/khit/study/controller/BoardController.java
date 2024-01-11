package com.khit.study.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// 문자열을 반환하는 어노테이션
@RestController // => method에게 씀
// = @ResponseBody, @ResponseEntity와 비슷함 => class에게 씀
public class BoardController {
	
	@GetMapping("/greeting")
	public String sayHello(String name) {
		
		return "greeting!" + name;   // 문자열
	}
}

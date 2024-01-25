package com.khit.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller   // 데이터 전달이 역할인 어노테이션임
public class Sample2Controller {
	// 문자를 전달
	@GetMapping("/sample2")  // file 체계가 아닌 rest 체계
	// @ResponseBody   // = @ResController와 같은 효과
	public @ResponseBody String test2() {
		return "안녕히가세요";    // main -> main.html
	}
	
	
}

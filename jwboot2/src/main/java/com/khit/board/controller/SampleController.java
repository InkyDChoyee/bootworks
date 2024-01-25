package com.khit.board.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.khit.board.entity.Users;

import lombok.Builder;

@Builder
@RestController   // 데이터 전달이 역할인 어노테이션임
public class SampleController {
	
	@GetMapping("/sample")  // file 체계가 아닌 rest 체계
	public String test() {
		return "안녕하세요";    // main -> main.html
	}
	
	// GET - select
	// 객체를 반환하면 - json 형태로 전달됨
	@GetMapping("/khit")
	public Users httpGet() {
		// user 1명을 생성한 후 데이터 검색(보기)
		Users user = Users.builder().id(1)
								    .username("이순신")
								    .password("pw1234")
								    .email("ship12@naver.com")
								    .build();
		return user;
	}

	// POST - insert
	// 전달받은 데이터가 json 형태({key:value})일 때 @RequestBody 사용
	@PostMapping("/khit")
	public String httpPost(@RequestBody Users users) {
		return "POST 요청 처리" + users.toString();
	}
	
	// PUT - update
	@PutMapping("/khit")
	public String httpPut(@RequestBody Users users) {
		return "PUT 요청 처리" + users.toString();
	}
	
	// DELETE - delete
	// @PathVariable은 경로 변수를 전달 받음
	@DeleteMapping("/khit/{id}")
	public String httpDelete(@PathVariable Integer id) {
		return "DELETE 요청 처리" + id;
	}
	
	
}

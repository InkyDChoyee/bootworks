package com.khit.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khit.board.entity.User;
import com.khit.board.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller    // @RestController = @Controller + @ResponseBody + @RequestBody
public class UserController {
	private final UserService service;
	
	// 회원가입
	// 포스트맨에 json형태의 자료를 입력 받아서 DB에 저장
	@PostMapping("/user")
	@ResponseBody
	public String insertUser(@RequestBody User user) {   // @RequsetBody = 전체 유저
		service.save(user);
		return "회원 가입 성공";
	}
	
	// 회원 목록 보기
	@GetMapping("/user/list")
	@ResponseBody
	public List<User> getList(){
		List<User> userList = service.findAll();
		return userList;
	}
	
	// 회원 상세 보기
	@GetMapping("/user/{id}")
	@ResponseBody
	public User getUser(@PathVariable Integer id) {   // @PathVariable = 특정 유저
		// 검색된 회원이 없을 경우 예외 반환
		return service.findById(id);
	}
	
	// 회원 수정
	@PutMapping("/user")
	@ResponseBody
	public String updateUser(@RequestBody User user) {
		service.update(user);
		return "회원 수정 성공!";
	}
	
	// 회원 삭제
	@DeleteMapping("/user/{id}")
	public @ResponseBody String deleteUser(@PathVariable Integer id) {
		service.deleteById(id);
		return "회원 정보 삭제";
	}
	
	
}

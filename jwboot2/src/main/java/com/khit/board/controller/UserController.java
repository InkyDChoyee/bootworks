package com.khit.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khit.board.entity.User;
import com.khit.board.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {
	private final UserService service;
	
	// 회원가입
	// 포스트맨에 json형태의 자료를 입력 받아서 DB에 저장
	@PostMapping("/user")
	@ResponseBody
	public String insertUser(@RequestBody User user) {
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
	public User getUser(@PathVariable Integer id) {
		User user = service.findById(id);
		return user;
	}
	
	
}

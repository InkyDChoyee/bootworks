package com.khit.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.khit.board.entity.Member;
import com.khit.board.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MemberController {
	private final MemberService memberService;

	@GetMapping("/member/login")
	private String loginForm() {
		return "/member/login";
	}
	
	@PostMapping("/member/login")
	private String login(@ModelAttribute Member member, HttpSession session) {
		Member loginMember = memberService.login(member);
		// MemberService = 아이디 조회 / MemberController = 비밀번호 조회
		if(loginMember != null && loginMember.getPassword().equals(member.getPassword())) {
			// 아이디 비밀번호 이리치해서 로그인 되면 세션 발급
			session.setAttribute("sessionMemberId", loginMember.getMemberId());
			return "main";
		}else {
			return "/member/login";
		}
	}
	
	@GetMapping("/main")
	public String main() {
		return "main";
	}
	
	
}

package com.khit.board.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.khit.board.config.SecurityUser;
import com.khit.board.dto.MemberDTO;
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
	
//	@PostMapping("/member/login")
//	private String login(@ModelAttribute Member member, HttpSession session) {
//		Member loginMember = memberService.login(member);
//		// MemberService = 아이디 조회 / MemberController = 비밀번호 조회
//		if(loginMember != null && loginMember.getPassword().equals(member.getPassword())) {
//			// 아이디 비밀번호 이리치해서 로그인 되면 세션 발급
//			session.setAttribute("sessionMemberId", loginMember.getMemberId());
//			return "main";
//		}else {
//			return "/member/login";
//		}
//	}
	
	// 메인 페이지
	@GetMapping("/main")
	public String main() {
		return "main";
	}
	
	// 로그아웃
	// 회원 로그아웃
	@GetMapping("/member/logout")
	public String logout() {
		return "redirect:/";
	}
	
	// 회원 가입 페이지
	@GetMapping("/member/join")
	public String joinForm() {
		return "/member/join";
	}
	
	// 회원 가입 처리
	@PostMapping("/member/join")
	public String join(@ModelAttribute MemberDTO memberDTO) {
		memberService.save(memberDTO);
		return "redirect:/member/login";
	}
	
	// 회원 목록
	@GetMapping("/member/list")
	public String getList(Model model) {
		// DB에서 꺼내서 MemberDTO로 반환
		List<MemberDTO> memberDTOList = memberService.findAll();
		model.addAttribute("memberList", memberDTOList);
		return "/member/list";   // list.html
	}
	
	// 회원 상세 보기
	@GetMapping("member/{id}")
	public String getMember(@PathVariable("id") Integer id, Model model) {
		Member member = memberService.findById(id);
		model.addAttribute("member", member);
		return "/member/detail";
	} 
	
	// 회원 삭제
	@GetMapping("member/delete/{id}")
	public String deleteMember(@PathVariable("id") Integer id) {
		memberService.deleteById(id);
		return "redirect:/member/list";
	}
	
	// 회원 수정
	// @AuthenticationPrincipal = 회원을 인가하는 클래스
	@GetMapping("/member/update")
	public String updateForm(@AuthenticationPrincipal SecurityUser principal, Model model) {
		
		Member member = memberService.findByMemberId(principal);
		model.addAttribute("member", member);
		return "/member/update";
	}
	
	// 회원 수정 처리
	@PostMapping("/member/update")
	public String update(@ModelAttribute Member member) {
		memberService.update(member);
		return "redirect:/member/" + member.getId();
	}
}

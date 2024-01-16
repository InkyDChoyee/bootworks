package com.khit.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khit.board.dto.MemberDTO;
import com.khit.board.entity.Member;
import com.khit.board.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller   
public class MemberController {

	private final MemberService memberService;
	
	// 회원 가입
	@GetMapping("/member/join")
	public String joinForm() {
		return "/member/join";  // = join.html
	}
	
	// 회원 가입 처리
	@PostMapping("/member/join")
	public String join(@ModelAttribute MemberDTO memberDTO) {
		System.out.println("memberDTO: " + memberDTO);
		memberService.save(memberDTO);
		return "redirect:/member/login"; // = login.html
	}
	
	// 회원 목록
	@GetMapping("member/list")
	public String getList(Model model) {
		// DB에서 꺼내서 MemberDTO로 반환
		List<MemberDTO> memberDTOList = memberService.findAll();
		model.addAttribute("memberList", memberDTOList);
		return "/member/list";   // list.html
	}
	
	// 회원 상세보기
	@GetMapping("member/{id}")
	public String getMember(@PathVariable("id") Long id, Model model) {
		MemberDTO memberDTO = memberService.findById(id);
		model.addAttribute("member", memberDTO);
		return "/member/detail";
	}
	
	// 회원 삭제
	@GetMapping("/member/delete/{id}")
	public String deleteMember(@PathVariable("id") Long id) {
		memberService.deleteById(id);
		return "redirect:/member/list";
	}
	
	// 회원 로그인
	@GetMapping("/member/login")
	public String loginForm() {
		return "/member/login";
	}
	
	// 회원 로그인 처리
	@PostMapping("/member/login")
	public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session, Model model) {
		MemberDTO loginMember = memberService.login(memberDTO);
		// 로그인한 결과(객체가 있으면 로그인처리, 없으면 로그인폼)
		if(loginMember != null) {
			// 세션 발급 = 로그인
			session.setAttribute("sessionEmail", loginMember.getMemberEmail());
			session.setAttribute("sessionName", loginMember.getMemberName());
			return "main";
		}else {
			String error = "아이디나 비밀번호를 확인해 주세요";
			model.addAttribute("error", error);
			return "/member/login";
		}
	}
	
	// 회원 로그아웃
	@GetMapping("/member/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	// 회원 수정
	@GetMapping("/member/update")
	public String updateForm(HttpSession session, Model model) {
		String email = (String)session.getAttribute("sessionEmail");
		MemberDTO memberDTO = memberService.findByEmail(email);
		model.addAttribute("member", memberDTO);
		return "/member/update";
	}
	
	// 회원 수정 처리
	@PostMapping("/member/update")
	public String update(MemberDTO memberDTO) {
		memberService.update(memberDTO);
		return "redirect:/member/" + memberDTO.getId();
	}
	
	@PostMapping("/member/check-email")
	public @ResponseBody String checkEmail(@RequestParam("memberEmail") String memberEmail) {
		String resultText = memberService.checkEmail(memberEmail);
		return resultText;
	}
	
}

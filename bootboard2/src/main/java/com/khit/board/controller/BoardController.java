package com.khit.board.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.khit.board.config.SecurityUser;
import com.khit.board.dto.BoardDTO;
import com.khit.board.entity.Board;
import com.khit.board.service.BoardService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/board")
@Controller
public class BoardController {
	
	private final BoardService boardService;
	
	@GetMapping("/list")
	public String getList(Model model) {
		List<BoardDTO> boardDTOList = boardService.findAll();
		model.addAttribute("boardList", boardDTOList);
		return "/board/list";  // list.html
	}
	
	// 게시글 상세 보기
	@GetMapping("/{id}")
	public String getBoard(@PathVariable Integer id, Model model) {  // @PathVariable = API 주소 방식
		BoardDTO boardDTO = boardService.findById(id);
		model.addAttribute("board", boardDTO);
		return "/board/detail";   // detail.html
	}
	
	@GetMapping("/write")
	public String writeForm() {
		return "/board/write";
	}
	
	@PostMapping("/write")
	public String write(@ModelAttribute Board board,
			@AuthenticationPrincipal SecurityUser principal) {
		board.setMember(principal.getMember());
		boardService.save(board);
		return "redirect:/board/list";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		boardService.deleteById(id);
		return "redirect:/board/list";
	}
	
	// 게시글 수정 페이지 요청
	@GetMapping("/update/{id}")
	public String updateFrom(@PathVariable Integer id, Model model) {
		BoardDTO boardDTO = boardService.findById(id);
		model.addAttribute("board", boardDTO);
		return "/board/update";
	}
	
	// 게시글 수정 처리
	@PostMapping("/update/{id}")
	public String update(@ModelAttribute BoardDTO boardDTO,
			@AuthenticationPrincipal SecurityUser principal) {
		boardDTO.setMember(principal.getMember());
		boardService.update(boardDTO);
		return "redirect:/board/" + boardDTO.getId();
	}
}

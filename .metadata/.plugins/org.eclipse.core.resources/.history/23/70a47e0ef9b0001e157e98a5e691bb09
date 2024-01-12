package com.khit.study.controller;

//import java.util.ArrayList;
//import java.util.Date;
import java.util.List;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khit.study.entity.BoardVO;
import com.khit.study.service.BoardRESTService;

import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;

//@NoArgsConstructor  기본 생성자까지 사용하면 충돌이 일어남
@AllArgsConstructor
// 문자열을 반환하는 어노테이션
@RestController // => method에게 씀
// = @ResponseBody, @ResponseEntity와 비슷함 => class에게 씀
public class RESTController {
	
//	@Autowired  @AllArgsConstructor 대신 사용 가능
	
	private BoardRESTService boardService;
	
	@GetMapping("/greeting")
	public String sayHello(String name) {
		
		return "greeting!" + name;   // 문자열
	}
	
	// 객체 데이터를 브라우저에 보내줌
	@GetMapping("/board/detail")
	public BoardVO getBoard() {
		BoardVO board = boardService.getBoard();
		return board;
	}
	
	@GetMapping("/board/list")
	public List<BoardVO> getBoardList(){ 
		List<BoardVO> boardList = boardService.getBoardList();
		return boardList;
	}

}

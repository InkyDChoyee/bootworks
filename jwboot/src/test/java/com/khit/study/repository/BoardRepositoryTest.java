package com.khit.study.repository;

import java.sql.Timestamp;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.khit.study.entity.Board;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class BoardRepositoryTest {

	@Autowired
	private BoardRepository boardRepository;

	// 게시글 생성
	@Test
	public void insertBoard() {
		/*
		 * Board board = new Board(); board.setTitle("가입 인사"); board.setWriter("김신입");
		 * board.setContent("Hello~"); board.setCreatedDate(new
		 * Timestamp(System.currentTimeMillis()));
		 */

		// builder 사용 추가
		
//		  Board board = Board.builder() .title("test") .writer("tester")
//		  .content("teste_message") .createdDate(new Timestamp(System.currentTimeMillis()))
//		  .build();
		 

		// db에 저장
//		boardRepository.save(board);
	}

	// 목록 보기
	@Test
	public void getBoardList() {
		List<Board> boardList = boardRepository.findAll();

		// boardList 출력
		
//		for(Board board : boardList) { log.info(board.toString()); }
		 

		// 람다식 사용
		boardList.forEach(board -> log.info(board.toString()));
	}

	// 한건 보기
	@Test
	public void getBoard() {
		// findById()함수 사용 => get()사용
		/*
		 * Board board = boardRepository.findById(2).get(); log.info(board.toString());
		 */
	}

	// 수정
	@Test
	public void updateBoard() {
		// 수정하려는 게시글 가져오기
		/*
		 * Board board = boardRepository.findById(1).get();
		 * 
		 * // 수정한 게시글 저장하기 board.setTitle("update_title");
		 * board.setContent("update_content");
		 * 
		 * boardRepository.save(board);
		 */
	}
	
	// 삭제
	@Test
	public void deleteBoard() {
//		boardRepository.deleteById(3);
	}

}
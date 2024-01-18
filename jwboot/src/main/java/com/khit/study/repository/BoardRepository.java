package com.khit.study.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.khit.study.entity.Board;

// JpaRepository를 상속해야 함
public interface BoardRepository extends JpaRepository<Board, Integer>{
	// 기본 CRUD 내장O
	
	// 쿼리 메서드 실습
	// 제목으로 검색
	List<Board> findByTitle(String searchKeyword);
	
	// 제목이 포함된 검색
//	List<Board> findByTitleContaining(String searchKeyword);
	
	// 제목 또는 내용이 포함된 검색
	List<Board> findByTitleContainingOrContentContaining(String title, String content);
	
	// 제목에 특정 단어가 포함된 글 목록을 내림차순으로 조회
	List<Board> findByTitleContainingOrderByIdDesc(String keyword);
	
	// 제목에 특정 단어가 포함된 글 목록을 페이지 처리하여 조회
	//List<Board> findByTitleContaining(String keyword, Pageable pageable);
	
	// 페이지 처리
	Page<Board> findByTitleContaining(String keyword, Pageable pageable);
	
	
}

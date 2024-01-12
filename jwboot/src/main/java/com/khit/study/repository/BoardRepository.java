package com.khit.study.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khit.study.entity.Board;

// JpaRepository를 상속해야 함
public interface BoardRepository extends JpaRepository<Board, Integer>{
	// 기본 CRUD 내장O
	
	// 쿼리 메서드 실습
	// 제목으로 검색
	List<Board> findByTitle(String searchKeyword);
	
	// 제목이 포함된 검색
	List<Board> findByTitleContaining(String searchKeyword);
	
	// 제목 또는 내용이 포함된 검색
	List<Board> findByTitleContainingOrContentContaining(String title, String content);
	
	
	
}

package com.khit.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.khit.board.entity.Board;
import com.khit.board.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service 
public class BoardService {
 
	private final BoardRepository boardRepository;
	
	public List<Board> findAll() {
		return boardRepository.findAll();
	}

	public Board findById(Integer id) {
		return boardRepository.findById(id).get();
	}

}
package com.khit.board.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.khit.board.dto.BoardDTO;
import com.khit.board.entity.Board;
import com.khit.board.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service 
public class BoardService {
 
	private final BoardRepository boardRepository;
	
	public List<BoardDTO> findAll() {
		List<Board> boardList = boardRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
		List<BoardDTO> boardDTOList = new ArrayList<>();
		
		for(Board board : boardList) {
			BoardDTO boardDTO = BoardDTO.toSaveDTO(board);
			boardDTOList.add(boardDTO);
		}
		
		return boardDTOList;
	}

	public BoardDTO findById(Integer id) {
		Optional<Board> findBoard = boardRepository.findById(id);
		BoardDTO boardDTO = BoardDTO.toSaveDTO(findBoard.get());
		return boardDTO;
	}

	public void save(Board board) {
		boardRepository.save(board);
	}

	public void deleteById(Integer id) {
		boardRepository.deleteById(id);
	}

	public void update(BoardDTO boardDTO) {
		Board board = Board.toUpdateEntity(boardDTO);
		boardRepository.save(board);
	}

}

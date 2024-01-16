package com.khit.board.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.khit.board.dto.BoardDTO;
import com.khit.board.entity.Board;
import com.khit.board.exception.BootBoardException;
import com.khit.board.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {
	
	private final BoardRepository boardRepository;

	public void save(BoardDTO boardDTO) {
		//dto -> entity 변환 메서드 필요
		Board board = Board.toSaveEntity(boardDTO);
		// entity를 db에 저장
		boardRepository.save(board);
	}

	public List<BoardDTO> findAll() {
		// db에서 entity 꺼내오기
		List<Board> boardList = boardRepository.findAll();
		// List를 담을 빈 배열 생성
		List<BoardDTO> boardDTOList = new ArrayList<>();
		
		for(Board board : boardList) {
			// entity -> dto 변환 메서드 필요
			BoardDTO boardDTO = BoardDTO.toSaveDTO(board);
			boardDTOList.add(boardDTO);
		}
		return boardDTOList;
	}

	public BoardDTO findById(Long id) {
		// id가 없을 때를 위한 Optional
		Optional<Board> findBoard = boardRepository.findById(id);
		if(findBoard.isPresent()) {   // 검색한 게시글이 있으면 dto를 컨트롤러로 반환함
			BoardDTO boardDTO = BoardDTO.toSaveDTO(findBoard.get());
			return boardDTO;
		}else {   // 게시글이 없으면 에러 처리
			throw new BootBoardException("게시글을 찾을 수 없습니다");
		}
	}
}

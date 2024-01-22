package com.khit.board.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.khit.board.dto.BoardDTO;
import com.khit.board.entity.Board;
import com.khit.board.exception.BootBoardException;
import com.khit.board.repository.BoardRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardService {
	
	private final BoardRepository boardRepository;

	public void save(BoardDTO boardDTO, MultipartFile boardFile) throws Exception, IOException {
		// 1. 파일 저장 -> 서버
		if(!boardFile.isEmpty()) {  // 전달 된 파일이 있으면
			// 저장 경로 (1. server에 만드는 방법, 2. 하드디스크에 만드는 방법)
			String filepath = "C:\\bootworks\\bootboard\\src\\main\\resources\\static\\upload\\";
			UUID uuid = UUID.randomUUID();  // 무작위 아이디 생성 = 중복파일의 이름 방지
			String filename = uuid + "_" + boardFile.getOriginalFilename();  // 원본 파일
			
			// File 클래스 객체 생성
			File savedFile = new File(filepath, filename);   // upload 폴더에 저장
			boardFile.transferTo(savedFile);
		
			// 2. 파일 이름 저장 -> DB
			boardDTO.setFilename(filename);
			boardDTO.setFilepath("/upload/" + filename); // 파일 경로 설정
		}
		
		
		
		
		//dto -> entity 변환 메서드 필요
		Board board = Board.toSaveEntity(boardDTO);
		// entity를 db에 저장
		boardRepository.save(board);
	}

	public List<BoardDTO> findAll() {
		// db에서 entity 꺼내오기
		/* List<Board> boardList = boardRepository.findAll(); */
		// 내림차순 정렬 = Sort.by(정렬방식, 해당필드)
		List<Board> boardList = boardRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
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

	@Transactional  // 컨트롤러 작업(메서드)이 2개 이상이면 사용함
	public void updateHits(Long id) {
		// updateHits() => boardRepository에 메서드 생성
		boardRepository.updateHits(id);
	}

	public void deleteById(Long id) {
		boardRepository.deleteById(id);
	}

	public void update(BoardDTO boardDTO, MultipartFile boardFile) throws Exception, IOException {
		// save() = 삽입(id가 없는 경우), 수정(id가 있는 경우)로 나뉨
		Board board = null;
		// 1. 파일 저장 -> 서버
		if(!boardFile.isEmpty()) {  // 전달 된 파일이 있으면
			// 저장 경로 (1. server에 만드는 방법, 2. 하드디스크에 만드는 방법)
			String filepath = "C:\\bootworks\\bootboard\\src\\main\\resources\\static\\upload\\";
			UUID uuid = UUID.randomUUID();  // 무작위 아이디 생성 = 중복파일의 이름 방지
			String filename = uuid + "_" + boardFile.getOriginalFilename();  // 원본 파일
			
			// File 클래스 객체 생성
			File savedFile = new File(filepath, filename);   // upload 폴더에 저장
			boardFile.transferTo(savedFile);
		
			// 2. 파일 이름 저장 -> DB
			boardDTO.setFilename(filename);
			boardDTO.setFilepath("/upload/" + filename); // 파일 경로 설정
		}else {
			// 수정할 파일이 없으면 게시글 번호 경로만 보여줌
			boardDTO.setFilepath(findById(boardDTO.getId()).getFilepath());
		}
		// dto->entity
		board = Board.toUpdateEntity(boardDTO);
		boardRepository.save(board);
	}

	public Page<BoardDTO> findListAll(Pageable pageable) {
		int page = pageable.getPageNumber() - 1;  // db가 1 작음
		int pageSize = 10;
		
		pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "id");
		
		Page<Board> boardList = boardRepository.findAll(pageable);

		log.info("" + boardList.isFirst());  // 속성으로 나가면 .first로 작동함
		log.info("" + boardList.isLast());  // 속성으로 나가면 .last로 작동함
		log.info("" + boardList.getNumber());  // 현재 페이지
		
		// 생성자 방식으로 boardDTOList를 만들어 가져오기
		Page<BoardDTO> boardDTOList = boardList.map(board -> new BoardDTO(
															board.getId(),
															board.getBoardTitle(),
															board.getBoardWriter(),
															board.getBoardContent(),
															board.getBoardHits(),
															board.getFilename(),
															board.getFilepath(),
															board.getCreatedDate(),
															board.getUpdatedDate()));

		return boardDTOList;
	}

	//public Page<BoardDTO> findListAll(Pageable pageable)에서 String keyword가 추가 된 것
	public Page<BoardDTO> findByBoardTitleContaining(String keyword, Pageable pageable) {
		int page = pageable.getPageNumber() - 1;  // db가 1 작음
		int pageSize = 10;
		
		pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "id");
		
		Page<Board> boardList = boardRepository.findByBoardTitleContaining(keyword,pageable);
		
		log.info("" + boardList.isFirst());  // 속성으로 나가면 .first로 작동함
		log.info("" + boardList.isLast());  // 속성으로 나가면 .last로 작동함
		log.info("" + boardList.getNumber());  // 현재 페이지
		
		// 생성자 방식으로 boardDTOList를 만들어 가져오기
		Page<BoardDTO> boardDTOList = boardList.map(board -> new BoardDTO(
				board.getId(),
				board.getBoardTitle(),
				board.getBoardWriter(),
				board.getBoardContent(),
				board.getBoardHits(),
				board.getFilename(),
				board.getFilepath(),
				board.getCreatedDate(),
				board.getUpdatedDate()));
		
		return boardDTOList;
	}

	public Page<BoardDTO> findByBoardContentContaining(String keyword, Pageable pageable) {
		int page = pageable.getPageNumber() - 1;  // db가 1 작음
		int pageSize = 10;
		
		pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "id");
		
		Page<Board> boardList = boardRepository.findByBoardContentContaining(keyword,pageable);
		
		log.info("" + boardList.isFirst());  // 속성으로 나가면 .first로 작동함
		log.info("" + boardList.isLast());  // 속성으로 나가면 .last로 작동함
		log.info("" + boardList.getNumber());  // 현재 페이지
		
		// 생성자 방식으로 boardDTOList를 만들어 가져오기
		Page<BoardDTO> boardDTOList = boardList.map(board -> new BoardDTO(
				board.getId(),
				board.getBoardTitle(),
				board.getBoardWriter(),
				board.getBoardContent(),
				board.getBoardHits(),
				board.getFilename(),
				board.getFilepath(),
				board.getCreatedDate(),
				board.getUpdatedDate()));
		
		return boardDTOList;
	}
}

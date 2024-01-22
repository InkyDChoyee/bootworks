package com.khit.board.entity;

import com.khit.board.dto.BoardDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="tbl_board")
@Entity   // 테이블이 생성되는 class
public class Board extends BaseEntity{
	// 필드
	@Id  // = PK
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String boardTitle;
	@Column(length=30, nullable=false)
	private String boardWriter;
//	@Column(nullable=false)
//	private String boardEmail;
	@Column(nullable=false)
	private String boardContent;
	@Column
	private Integer boardHits;
	
	// write.html에서 name값과 다른 이름으로 만들 것
	// MultipartFile과 String 의 type이 다르기 때문
	@Column  
	private String filename;  // String과 객체 차이 때문에 넘겨받는 객체와 칼럼 이름을 다르게 해준다
	@Column
	private String filepath;
	
	// dto->entity 정적 메서드 정의
	public static Board toSaveEntity(BoardDTO boardDTO) {
		Board board = Board.builder().boardTitle(boardDTO.getBoardTitle())
				                     .boardWriter(boardDTO.getBoardWriter())
				                     .boardContent(boardDTO.getBoardContent())
				                     .filename(boardDTO.getFilename())
				                     .filepath(boardDTO.getFilepath())
				                     .boardHits(0)
				                     .build();
		return board;
	}
	
//	// dto->entity 정적 메서드 정의 
//	public static Board toUpdateNoFile(BoardDTO boardDTO) { // 첨부파일이 없는 경우
//		Board board = Board.builder().id(Long.valueOf(boardDTO.getId()))
//									 .boardTitle(boardDTO.getBoardTitle())
//									 .boardWriter(boardDTO.getBoardWriter())
//									 .boardContent(boardDTO.getBoardContent())
//									 .boardHits(boardDTO.getBoardHits())
//									 .build();
//		return board;
//	}

	// dto->entity 정적 메서드 정의
	public static Board toUpdateEntity(BoardDTO boardDTO) {  // 첨부파일이 있는 경우
		Board board = Board.builder().id(Long.valueOf(boardDTO.getId()))
				.boardTitle(boardDTO.getBoardTitle())
				.boardWriter(boardDTO.getBoardWriter())
				.boardContent(boardDTO.getBoardContent())
				.filename(boardDTO.getFilename())
				.filepath(boardDTO.getFilepath())
				.boardHits(boardDTO.getBoardHits())
				.build();
		return board;
	}
}

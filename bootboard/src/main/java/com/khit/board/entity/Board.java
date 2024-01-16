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
	@Column(nullable=false)
	private String boardContent;
	@Column
	private Integer boardHits;
	
	// dto->entity 정적 메서드 정의
	public static Board toSaveEntity(BoardDTO boardDTO) {
		Board board = Board.builder().boardTitle(boardDTO.getBoardTitle())
				                     .boardWriter(boardDTO.getBoardWriter())
				                     .boardContent(boardDTO.getBoardContent())
				                     .boardHits(0)
				                     .build();
		return board;
	}
}

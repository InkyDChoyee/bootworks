package com.khit.board.entity;

import java.util.List;

import com.khit.board.dto.BoardDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude="member")
@Setter 
@Getter
@Entity
@Table(name="t_board")
@Builder
public class Board extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;   // 게시글 번호
	
	@Column(nullable = false) 
	private String title;
	
	@Column(length = 2000, nullable = false)
	private String content;
	
	// Board 엔티티와 연관관계 매핑
	// 다대일 매핑 (fetch = 조회할 때 -> EAGER = 전체조회를 함 / LAZY = 특정한 조회만 함)
	// JoinColumn - 외래키 설정
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;
	
	// 1쪽이 board가 주인이 아님
	@OneToMany(mappedBy="board", cascade=CascadeType.ALL)  // 게시글 삭제하면 댓글도 삭제
	@OrderBy("id desc")
	private List<Reply> replyList;
	
	public static Board toSaveEntity(BoardDTO boardDTO) {
		Board board = Board.builder()
				                     .title(boardDTO.getTitle())
				                     .content(boardDTO.getContent())
				                     .member(boardDTO.getMember())
				                     .build();
		return board;
	}

	public static Board toUpdateEntity(BoardDTO boardDTO) {
		Board board = Board.builder().id(boardDTO.getId())
				.title(boardDTO.getTitle())
				.content(boardDTO.getContent())
				.member(boardDTO.getMember())
				.build();
		return board;
	}
	
	
}

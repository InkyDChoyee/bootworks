package com.khit.board.dto;

import java.sql.Timestamp;
import java.util.List;

import com.khit.board.entity.Board;
import com.khit.board.entity.Member;
import com.khit.board.entity.Reply;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BoardDTO {
	
	private Integer id;
	
	private String title;
	
	private String content;
	
	private Member member;
	
	private Timestamp createdDate;
	private Timestamp updatedDate;
	
	private List<Reply> replyList;
	
	public static BoardDTO toSaveDTO(Board board) {
		BoardDTO boardDTO = BoardDTO.builder().id(board.getId())
											  .title(board.getTitle())
											  .content(board.getContent())
											  .member(board.getMember())
											  .createdDate(board.getCreatedDate())
											  .updatedDate(board.getUpdatedDate())
											  .replyList(board.getReplyList())
											  .build();
		return boardDTO;
	}
	
}

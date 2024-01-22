package com.khit.board.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter 
@Getter
@Entity
@Table(name="t_board")
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;   // 게시글 번호
	
	@Column(nullable = false) 
	private String title;
	
	@Column(length = 2000, nullable = false)
	private String content;
	
	@CreationTimestamp 
	private Timestamp createdDate;
	
	// Board 엔티티와 연관관계 매핑
	// 다대일 매핑 (fetch = 조회할 때 -> EAGER = 전체조회를 함 / LAZY = 특정한 조회만 함)
	// JoinColumn - 외래키 설정
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "member_id")
	private Member member;
	
}

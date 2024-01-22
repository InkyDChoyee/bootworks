package com.khit.board.unittest;

import java.sql.Timestamp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.khit.board.entity.Board;
import com.khit.board.entity.Member;
import com.khit.board.repository.BoardRepository;
import com.khit.board.repository.MemberRepository;

@SpringBootTest
public class RelationMappingTest {
	// sql 기본 제공 - MemberRepositoy - save(), findById() ...
	
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private BoardRepository boardRepository;

//	@Test
//	public void testInsertData() {
//		Member member1 = new Member();    
//		member1.setMemberId("member1");
//		member1.setPassword("password1");
//		member1.setName("name1");
//		member1.setRole("USER");          // 일반 회원   
//		
//		memberRepository.save(member1);
//
//		Member member2 = new Member();
//		member2.setMemberId("member2");
//		member2.setPassword("password2");
//		member2.setName("name2");
//		member2.setRole("ADMIN");         // 관리자
//		
//		memberRepository.save(member2);
//		
//		// 회원이 등록한 글
//		for(int i = 1; i<=3; i++) {
//			Board board = new Board();
//			board.setTitle("member1이 등록한 글제목 " + i);
//			board.setContent("member1이 등록한 글내용 " + i);
//			board.setMember(member1);
//			board.setCreatedDate(new Timestamp(System.currentTimeMillis()));
//			
//			boardRepository.save(board);
//		}
//	
//		// 관리자가 등록한 글
//		for(int i = 1; i<=3; i++) {
//			Board board = new Board();
//			board.setTitle("member2가 등록한 글제목 " + i);
//			board.setContent("member2가 등록한 글내용 " + i);
//			board.setMember(member1);
//			board.setCreatedDate(new Timestamp(System.currentTimeMillis()));
//			
//			boardRepository.save(board);
//		}
//	}

	
}	


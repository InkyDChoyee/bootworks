package com.khit.board.unittest;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.khit.board.entity.Board;
import com.khit.board.entity.Member;
import com.khit.board.repository.BoardRepository;
import com.khit.board.repository.MemberRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
//			board.setMember(member2);
//			board.setCreatedDate(new Timestamp(System.currentTimeMillis()));
//			
//			boardRepository.save(board);
//		}
//	}

//	@Test
//	public void testSelectAll() {
		// 회원 목록
//		List<Member> memberList = memberRepository.findAll();
//		for(Member member : memberList) {
//			log.info(member.toString());
//		}
		
		// 게시글 목록
//		List<Board> boardList = boardRepository.findAll();
//		for(Board board : boardList) {
//			log.info(board.toString());
//		}t
		// 람다식으로 출력
//		boardList.forEach(board -> log.info(board.toString()));	
//	}
	
	// 게시글 상세 조회
//	@Test
//	public void testSelectOne() {
//		// 4번 게시글 조회 - findById(4)
//		Board board = boardRepository.findById(4).get();
//		log.info(board.getId() + "번 게시글 정보");
//		log.info("제목: "+board.getTitle());
//		log.info("내용: "+board.getContent());
//		log.info("작성자: "+board.getMember().getName());
//		log.info("권한: "+board.getMember().getRole());
//		log.info("게시일: "+board.getCreatedDate());
//	}
	
	// 특정 회원이 게시한 글 조회
//	@Test
//	public void testSelect() {
//		// 뽀로로가 쓴 게시글 조회
//		Optional<Member> member = memberRepository.findByMemberId("member1");
//		log.info(member.get().getName()+"가 작성한 게시글 목록");
//		List<Board> boardList = member.get().getBoardList();
//		for(Board board : boardList) {
//			log.info(board.toString());
//		}
//	}
	
	// 특정 회원 삭제
//	@Test
//	public void testDelete() {
//		memberRepository.deleteById(2);
//		
//	}
	
}	


package com.khit.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khit.board.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer>{

	// select * from member where member_id = ?;
	Member findByMemberId(String memberId);
	
}

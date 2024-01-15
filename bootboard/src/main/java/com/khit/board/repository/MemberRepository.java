package com.khit.board.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khit.board.entity.Member;

// JPARepository를 상속받아야 함
public interface MemberRepository extends JpaRepository<Member, Long>{
	// 제공된 메서드 - save(), findAll(), findById(), deleteById()
	// 쿼리 메서드(메서드 이름이 쿼리를 나타냄) - Optional(null 체크 class)
	// = JPA 문법
	Optional<Member> findByMemberEmail(String memberEmail);
}

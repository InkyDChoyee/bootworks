package com.khit.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khit.board.entity.Member;

// JPARepository를 상속받아야 함
public interface MemberRepository extends JpaRepository<Member, Long>{

}

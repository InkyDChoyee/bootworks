package com.khit.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.khit.board.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{
	// 기본 제공 메서드 = save(), findAll(), findById(), deleteById()
	@Modifying  // 어떤 수정이나 변경이 일어났을 때 사용하는 에너테이션이다
	@Query(value="update Board b set b.boardHits=b.boardHits+1 where b.id=:id")
	public void updateHits(Long id);
}

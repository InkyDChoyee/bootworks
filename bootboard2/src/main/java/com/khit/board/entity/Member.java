package com.khit.board.entity;

import java.util.ArrayList;
import java.util.List;

import com.khit.board.dto.MemberDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude="boardList")  // 순환참조 방지
@Setter
@Getter
@Entity
@Table(name="t_member")
@Builder
public class Member extends BaseEntity{
	@Id    // 필수 입력 = PK임
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;       // 회원번호
	
	@Column(name = "member_id", unique = true)
	private String memberId;  // 아이디
	
	@Column(nullable = false)
	private String password;  // 비밀번호
	
	@Column(nullable = false, length = 30)
	private String name;
	
	//@Column
	@Enumerated(EnumType.STRING)
	private Role role;      // 권한 -> 일반사용자/관리자

	// Board와 관계 매핑
	// 주인 설정 = 다쪽(board)이 주인
	// cascade: 참조된 객체가 삭제되면 참조하는 객체도 삭제됨
	@OneToMany(mappedBy="member", cascade = CascadeType.ALL)
	private List<Board> boardList = new ArrayList<>();
	
	@OneToMany(mappedBy="member", cascade = CascadeType.ALL)
	private List<Reply> replyList;
	
	

	// dto(view에 온 입력값) => entity(db에 저장_
	// 회원 가입, 회원 수정
	public static Member toSaveEntity(MemberDTO memberDTO) {
		Member member = Member.builder()  // id가 없음 자동
										.memberId(memberDTO.getMemberId())
										.password(memberDTO.getPassword())
										.name(memberDTO.getName())
										.role(memberDTO.getRole())
										.build();
		return member;
	}
	
	public static Member toUpdateEntity(MemberDTO memberDTO) {
		Member member = Member.builder()
				.id(memberDTO.getId())
				.memberId(memberDTO.getMemberId())
				.password(memberDTO.getPassword())
				.name(memberDTO.getName())
				.role(memberDTO.getRole())
				.build();
		return member;
	}

}

package com.khit.board.dto;

import com.khit.board.entity.Member;
import com.khit.board.entity.Role;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberDTO {
	
	private Integer id;
	private String memberId;
	private String password;
	private String name;
	private Role role;
	
	// entity를 dto로 변환 = entity를 파라미터로 받아서 dto로 변환
	// entity(model<db>에 저장된것) -> dto(view로 보기)로 변환
	// 목록보기, 상세보기 에서 사용
	public static MemberDTO toSaveDTO(Member member) {
		MemberDTO memberDTO = MemberDTO.builder().id(member.getId())
												 .memberId(member.getMemberId())
												 .password(member.getPassword())
												 .name(member.getName())
												 .role(member.getRole())
												 .build();
		return memberDTO;
	}
	
	
}

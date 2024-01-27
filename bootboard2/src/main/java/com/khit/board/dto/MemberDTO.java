package com.khit.board.dto;

import java.sql.Timestamp;

import com.khit.board.entity.Member;
import com.khit.board.entity.Role;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberDTO {
	
	private Integer id;
	
	@Size(min=4, max=20)
	@NotEmpty(message = "사용자 아이디는 필수 항목입니다")
	private String memberId;
	
	@NotEmpty(message = "사용자 비밀번호는 필수 항목입니다")
	private String password;

	@NotEmpty(message = "사용자 이름은 필수 항목입니다")
	private String name;
	
	private Role role;
	
	private Timestamp createdDate;
	private Timestamp updatedDate;
	
	// entity를 dto로 변환 = entity를 파라미터로 받아서 dto로 변환
	// entity(model<db>에 저장된것) -> dto(view로 보기)로 변환
	// 목록보기, 상세보기 에서 사용
	public static MemberDTO toSaveDTO(Member member) {
		MemberDTO memberDTO = MemberDTO.builder().id(member.getId())
												 .memberId(member.getMemberId())
												 .password(member.getPassword())
												 .name(member.getName())
												 .role(member.getRole())
												 .createdDate(member.getCreatedDate())
												 .updatedDate(member.getUpdatedDate())
												 .build();
		return memberDTO;
	}
	
}

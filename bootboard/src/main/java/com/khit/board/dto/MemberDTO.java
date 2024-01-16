package com.khit.board.dto;

import com.khit.board.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MemberDTO {
	private Long id;
	private String memberEmail;
	private String memberPassword;
	private String memberName;
	private int memberAge;
	
	// entity -> dto 변환 메서드
	// entity를 매개로 받아서 dto에 저장
	public static MemberDTO toSaveDTO(Member member) {
//		MemberDTO memberDTO = new MemberDTO();
//		memberDTO.setId(member.getId());
//		memberDTO.setMemberEmail(member.getMemberEmail());
//		memberDTO.setMemberPassword(member.getMemberPassword());
//		memberDTO.setMemberName(member.getMemberName());
//		memberDTO.setMemberAge(member.getMemberAge());
		
		// builder 방식
		MemberDTO memberDTO = MemberDTO.builder().id(member.getId())
				                                .memberEmail(member.getMemberEmail())
				                                .memberPassword(member.getMemberPassword())
				                                .memberName(member.getMemberName())
				                                .memberAge(member.getMemberAge())
				                                .build();
		
		return memberDTO;
	}
	
}

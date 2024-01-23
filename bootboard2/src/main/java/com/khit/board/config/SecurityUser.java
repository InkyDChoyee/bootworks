package com.khit.board.config;


import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.khit.board.entity.Member;


public class SecurityUser extends User{
	private static final long serialVersionUID = 1L;

	private Member member;
	// 3가지 파라미터 = username, password, authorities(role)
	public SecurityUser(Member member) {
		// username -> memberId로 바꿈
		// 암호화 안된 상태는 "{noob}" + member.getPassword()을 사용함
		super(member.getMemberId(), member.getPassword(), 
			  // 권한 설정
			  AuthorityUtils.createAuthorityList(member.getRole().toString()));
		this.member = member;
	}
	
	public Member getMember() {
		return member;
	}
	
}

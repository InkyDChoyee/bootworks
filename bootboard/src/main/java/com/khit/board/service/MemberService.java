package com.khit.board.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.khit.board.dto.MemberDTO;
import com.khit.board.entity.Member;
import com.khit.board.exception.BootBoardException;
import com.khit.board.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

// @AllArgsConstructor
@RequiredArgsConstructor  // 생성자 주입 방식 - final을 꼭 붙임
@Service
public class MemberService {

	private final MemberRepository memberRepository;

	public void save(MemberDTO memberDTO) {
		// db 안으로 저장 = entity를 저장해야 함
		// dto를 entity로 변환해야 함 = 변환 메서드 필요
		Member member = Member.toSaveEntity(memberDTO);
		
		memberRepository.save(member);
	}

	public List<MemberDTO> findAll() {
		// db에서 MemberEntity를 
		List<Member> memberList = memberRepository.findAll();
		// 변환 메서드 필요
		// member를 담을 빈 dto 리스트를 생성
		List<MemberDTO> memberDTOList = new ArrayList<>();
		
		for(Member member : memberList) {
			MemberDTO memberDTO = MemberDTO.toSaveDTO(member);
			memberDTOList.add(memberDTO);
		}
		// 꺼내와서 controller에 DTO로 보냄
		
		
		return memberDTOList;
	}

	public MemberDTO findById(Long id) {
		// db에서 member 1개 꺼내오기
//		Member member = memberRepository.findById(id).get();
		// id가 없을 때 오류 처리 - "url을 찾을 수 없습니다"
		Optional<Member> member = memberRepository.findById(id);
		if(member.isPresent()) {
			// entity -> dto 변환
//			MemberDTO memberDTO = MemberDTO.toSaveDTO(member);
			MemberDTO memberDTO = MemberDTO.toSaveDTO(member.get());
			return memberDTO;
		}else {
			throw new BootBoardException("찾는 데이터가 없습니다");
		}
	}

	public void deleteById(Long id) {
		memberRepository.deleteById(id);
	}

	public MemberDTO login(MemberDTO memberDTO) {  // 외부에서 요청한 DTO
		// 1. email로 회원 조회(이메일과 비밀번호를 비교)
		Optional<Member> memberEmail = // memberEmail을 포함한 member 객체를 의미
		memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
		if(memberEmail.isPresent()) {
			// 조회 결과 있음 - 1건 가져옴
			Member member = memberEmail.get();
			// 이메일을 가져와서 비밀번호 일치 여부 확인
			if(member.getMemberPassword().equals(memberDTO.getMemberPassword())) {
				// entity -> dto로 변환
				// 바꿔서 보낼 dto
				MemberDTO dto = MemberDTO.toSaveDTO(member);
				return dto;
			}else {
				return null;
			}
		}else {
			return null;
		}
		
	}

	public MemberDTO findByEmail(String email) {
		// db에서 이메일로 검색한 회원 객체 가져오고
		Member member = memberRepository.findByMemberEmail(email).get();
		// 회원 객체를(entity)를 dto로 변환
		MemberDTO memberDTO = MemberDTO.toSaveDTO(member);
		return memberDTO;
	}

	public void update(MemberDTO memberDTO) {
		// save가 가입, 수정 되는데 가입할 때는 id가 없고, 수정할때는 id가 있음
		Member member = Member.toUpdateEntity(memberDTO);
		// id가 있는 엔티티의 메서드 필요함
		memberRepository.save(member);
	}

	public String checkEmail(String memberEmail) {
		// db에 있는 email 조회 -> 있으면 "OK", 없으면 "NO" 문자열 반환
		Optional<Member> findMember = memberRepository.findByMemberEmail(memberEmail);
		// findMember = email이 들어있는 객체
		if(findMember.isEmpty()) { // 찾는 email이 없으면 가입 가능
			return "OK";
		}else {  // 찾는 email이 있으면 가입 불가능
			return "NO";
		}
	}
}

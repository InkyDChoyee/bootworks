package com.khit.board.service;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.stereotype.Service;

import com.khit.board.entity.User;
import com.khit.board.exception.CustomException;
import com.khit.board.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	private final UserRepository repository;

	public void save(User user) {
		repository.save(user);
	}

	public List<User> findAll() {
		return repository.findAll();
	}

	public User findById(Integer id) {
		// 검색된 회원이 없는 경우는 람다식으로 예외 처리
		User getUser = repository.findById(id).orElseThrow(()->{
			return new CustomException(id+"번 회원이 없습니다");
		});
		return getUser;
		
//		User getUser = repository.findById(id).get().orElseThrow(new Supplier<CustomException>() {
//
//			@Override
//			public CustomException get() {
//				return new CustomException(id+"번 회원이 없습니다");
//			}
//			
//		});
//		
//		return getUser;
	}
}

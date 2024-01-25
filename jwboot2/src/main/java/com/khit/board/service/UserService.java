package com.khit.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.khit.board.entity.User;
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
		return repository.findById(id).get();
	}
}

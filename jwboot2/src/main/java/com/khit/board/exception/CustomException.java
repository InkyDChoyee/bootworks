package com.khit.board.exception;

// 사용자 정의 exception 예외 처리 클래스
public class CustomException extends RuntimeException{

	public CustomException(String message) {
		super(message);
	}
}

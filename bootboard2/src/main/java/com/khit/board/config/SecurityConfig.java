package com.khit.board.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private CustomUserDetailsService customService;
	
	// @Bean은 프로젝트에서 관리가 안되는 클래스를 빈으로 사용할 때 필요함
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// 인증 설정 -> 권한 설정
		http.userDetailsService(customService);
		
		http.authorizeHttpRequests(authorize -> authorize
														// 로그인이 필요없음
														.requestMatchers("/", "/css/**", "/img/**", "/member/**", "/auth/main").permitAll()
														// 로그인이 필요함
														.anyRequest().authenticated()
								   ).formLogin(form -> form.loginPage("/member/login"));
		return http.build();
	}
	
	// 암호화 설정
	// PasswordEncoder를 상속받은 BCryptPasswordEncoder를 반환
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
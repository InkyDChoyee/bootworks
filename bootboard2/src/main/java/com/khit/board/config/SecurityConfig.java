package com.khit.board.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration        // 환경 설정에 사용하는 어노테이션
@EnableWebSecurity    // security를 사용할 수 있게 해주는 어노테이션
public class SecurityConfig {
	
	@Autowired
	private CustomUserDetailsService customService;
	
	// @Bean은 프로젝트에서 관리가 안되는 클래스를 빈으로 사용할 때 필요함
	@Bean  // spring이 관리하는 bean이 아닌 추가하는 Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// 인증 설정 -> 권한 설정
		http.userDetailsService(customService);
		
		http.authorizeHttpRequests(authorize -> authorize
														.requestMatchers("/", "/css/**", "/img/**", "/js/**", "/board/**").permitAll()
														.requestMatchers("/board/write").authenticated()
														.requestMatchers("/member/list").hasAnyAuthority("ADMIN")  // 위치 중요
														.requestMatchers("/member/**").permitAll()
														.anyRequest().authenticated()
								   ).formLogin(form -> form.loginPage("/member/login")
								                           .defaultSuccessUrl("/")
								                           .permitAll()
								   );
		
		// 접근 권한 페이지 
		http.exceptionHandling().accessDeniedPage("/auth/accessDenied");
		
		
		http.logout().logoutUrl("/member/logout")
					 .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
					 .invalidateHttpSession(true)
		             .logoutSuccessUrl("/");
		
		return http.build();
	}
	
	// 암호화 설정
	// PasswordEncoder를 상속받은 BCryptPasswordEncoder를 반환
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}

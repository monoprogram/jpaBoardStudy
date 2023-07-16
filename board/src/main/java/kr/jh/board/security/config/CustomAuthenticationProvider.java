package kr.jh.board.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import kr.jh.board.security.domain.User;
import kr.jh.board.security.service.CustomUserDetailsService;

public class CustomAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		String userId = (String) authentication.getPrincipal();
		String password = (String) authentication.getCredentials();
		
		User user = null;
		user  = (User) customUserDetailsService.loadUserByUsername(userId);
		
		if(user == null) {
			throw new BadCredentialsException("일치하는 사용자가 없습니다.");
		}
		
		// 입력된 패스워드가 User 정보가 저장된 패스워드를 비교
		if(!passwordEncoder.matches(password , user.getPassword())) {
			throw new BadCredentialsException("아이디와 비밀번호를 확인하여 주시기 바랍니다.");
		}
		
		return new UsernamePasswordAuthenticationToken(authentication, authentication, user.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	
}

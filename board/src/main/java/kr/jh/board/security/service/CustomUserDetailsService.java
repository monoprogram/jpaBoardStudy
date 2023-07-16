package kr.jh.board.security.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import kr.jh.board.security.domain.User;
import kr.jh.board.security.repository.UserRepository;

public class CustomUserDetailsService implements UserDetailsService {

	private static final Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = null;
		
		try {
			user = userRepository.findByUserId(username);
			if(user == null) {
				throw new UsernameNotFoundException(username);
			}
			
		} catch(Exception e) {
			log.error("사용자 조회 중 오류 발생 : {}" , e.getMessage());
		}
		
		return user;
	}

	
}

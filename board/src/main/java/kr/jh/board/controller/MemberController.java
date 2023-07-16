package kr.jh.board.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.jh.board.security.domain.User;
import kr.jh.board.security.domain.UserRole;
import kr.jh.board.security.repository.UserRepository;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private UserRepository userRepository;
	
	
	
	@GetMapping("/signUp")
	public String signUp() {
		return "signUp";
	}
	
	@PostMapping("/join")
	public String join(User user) {
		UserRole mRole = new UserRole();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setUserPw(passwordEncoder.encode(user.getUserPw()));
		mRole.setRoleName("USER");
		user.setRoles(Arrays.asList(mRole));
		userRepository.save(user);
		
		return "redriect:/";
	}
	
	
}

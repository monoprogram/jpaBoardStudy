package kr.jh.board.security.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{
		
	@Override
	public void onAuthenticationSuccess(HttpServletRequest req , HttpServletResponse res , Authentication authentication) throws ServletException , IOException {
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		HttpSession session = req.getSession();
		
		List<String> roleNames = new ArrayList<>();
		authentication.getAuthorities().forEach( auth -> roleNames.add(auth.getAuthority()));
		
		if(roleNames.contains("ROLE_ADMIN")) {
			res.sendRedirect("/admin");
			return;
		}
		
		if(roleNames.contains("ROLE_USER")) {
			System.out.println(authentication.getName());
			session.setAttribute("mUser", authentication.getName());
			res.sendRedirect("/main");
			return;
		}
		
		res.sendRedirect("/main");
		
		
	}
	
}

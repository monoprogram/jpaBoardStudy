package kr.jh.board.security.filter;

import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req , HttpServletResponse res) throws AuthenticationException {
		
		if(!req.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + req.getMethod());
		}
		
		String username = obtainUsername(req);
		String password = obtainPassword(req);
		
		username = (username != null) ? username.trim() : "";
		password = (password != null) ? password : "";
		
		UsernamePasswordAuthenticationToken autheReq = UsernamePasswordAuthenticationToken.unauthenticated(username , password);
		setDetails(req, autheReq);
		return this.getAuthenticationManager().authenticate(autheReq);
	}
	
	@Nullable
	protected String obtainUsername(HttpServletRequest req) {
		return req.getParameter("userId");
	}
	
	@Nullable
	protected String obtainPassword(HttpServletRequest req) {
		return req.getParameter("userPw");
	}
	
	
}

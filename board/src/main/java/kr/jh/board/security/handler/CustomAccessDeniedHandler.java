package kr.jh.board.security.handler;

import java.io.IOException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	private String errorPage;
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String denied_url = errorPage + "?exception=" + accessDeniedException.getMessage();
		response.sendRedirect(denied_url);
	}
	
	public void setErrorPage(String errorPage) {
		this.errorPage = errorPage;
	}

	
}

package kr.jh.board.security.handler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.catalina.util.URLEncoder;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler{

	@Override
	public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse res,
			AuthenticationException exception) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String error_msg;
		
		if( exception instanceof BadCredentialsException) {
			error_msg = exception.getMessage();
		}
		else if(exception instanceof InternalAuthenticationServiceException) {
			error_msg = "내부적으로 발생한 시스템 문제로 인핸 요청을 처리할 수 없습니다. 관리자에게 문의하여 주시기 바랍니다.";
		}
		else if(exception instanceof AuthenticationCredentialsNotFoundException) {
			error_msg = "인증 요청이 거부되었습니다. 관리자에게 문의하여 주시기 바랍니다.";
		}
		else {
			error_msg = "알 수 없는 이유로 로그인에 실패하였습니다. 관리자에게 문의하여 주시기 바랍니다.";
		}
		
		setDefaultFailureUrl("/login?error=true&exception=" + URLEncoder.DEFAULT.encode(error_msg, StandardCharsets.UTF_8));
		super.onAuthenticationFailure(req, res, exception);
	}

	
}

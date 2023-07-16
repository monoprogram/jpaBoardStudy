package kr.jh.board.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import kr.jh.board.security.filter.CustomAuthenticationFilter;
import kr.jh.board.security.handler.CustomLoginFailureHandler;
import kr.jh.board.security.handler.CustomLoginSuccessHandler;
import kr.jh.board.security.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

	private static final String[] WHITE_PAGE = {
			"/js/**" , "/css/**" , "/img/**" 
			, "/component/**" , "/common/**"
			,"/test/**" , "/layout/**" , "/home/**" , "/member/signUp" , "/member/join"
			,"/**"
			// /** > 미설정 시 login error param url로 이동하지 못하고 login 페이지로 redirect 처리됨
	};
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.
			csrf(csrf -> csrf. csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
			.authorizeHttpRequests( (authorize) -> authorize
					.requestMatchers("/board/** , /dashboard/**").hasRole("USER")
					.requestMatchers("/picture" , "/visit" , "/community").authenticated()
					.requestMatchers(WHITE_PAGE).permitAll().anyRequest().authenticated()
					
			)
			.formLogin( login -> login
					.loginPage("/login")
					.loginProcessingUrl("/loginProcess")
					.usernameParameter("userId")
					.passwordParameter("userPw")
					.successHandler(customLoginSuccessHandler())
					.failureHandler(customLoginFailureHandler())
					.permitAll()
					
			)
			.rememberMe(rememberme -> rememberme
					.rememberMeServices(persistentTokenBasedRememberMeServices()))
			.logout(logout -> logout
						.logoutUrl("/logout")
						.logoutSuccessUrl("/login")
						.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
						.deleteCookies("remember-me")
						.invalidateHttpSession(true)
						.clearAuthentication(true));
					
			http.addFilterBefore(customAuthenticationFilter(http), UsernamePasswordAuthenticationFilter.class);
		
			return http.build();
	}

	@Bean
	public RememberMeServices persistentTokenBasedRememberMeServices() {
		PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices
			= new PersistentTokenBasedRememberMeServices("userSecretKey", customUserDetailsService(), persistendTokenRepository());
		persistentTokenBasedRememberMeServices.setAlwaysRemember(false);
		persistentTokenBasedRememberMeServices.setTokenValiditySeconds(60 * 60 * 24 * 7);
		persistentTokenBasedRememberMeServices.setParameter("remember-me");
		persistentTokenBasedRememberMeServices.setCookieName("remember-me");
		return persistentTokenBasedRememberMeServices;
		
	}
	
	@Bean
	public PersistendTokenRepositoryImpl persistendTokenRepository() {
		PersistendTokenRepositoryImpl persistendTokenRepositoryImpl = new PersistendTokenRepositoryImpl();
		return persistendTokenRepositoryImpl;
	}
	
	@Bean
	public CustomLoginSuccessHandler customLoginSuccessHandler() {
		return new CustomLoginSuccessHandler();
	}
	
	@Bean
	public CustomLoginFailureHandler customLoginFailureHandler() {
		return new CustomLoginFailureHandler();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public CustomUserDetailsService customUserDetailsService() {
		return new CustomUserDetailsService();
	}
	
	@Bean
	public CustomAuthenticationProvider customAuthenticationProvider() {
		return new CustomAuthenticationProvider();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.authenticationProvider(customAuthenticationProvider());
		return authenticationManagerBuilder.build();
	}
	
	/**
	 * 커스텀 필터 로그인 성공 , 실패 핸들러 연결 , 커스텀 프로바이더 연결 
	 * @param http
	 * @return
	 * @throws Exception
	 */
	@Bean
	public CustomAuthenticationFilter customAuthenticationFilter(HttpSecurity http) throws Exception {
		CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter();
		customAuthenticationFilter.setAuthenticationManager(authenticationManager(http));
		customAuthenticationFilter.setAuthenticationSuccessHandler(customLoginSuccessHandler());
		customAuthenticationFilter.setAuthenticationFailureHandler(customLoginFailureHandler());
		customAuthenticationFilter.setRememberMeServices(persistentTokenBasedRememberMeServices());
		return customAuthenticationFilter;
	}
	
}

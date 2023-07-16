package kr.jh.board.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

@Configuration
public class ViewConfig {

	@Autowired
	private ApplicationContext applicationContext;
	
	/**
	 * jsp viewResolver
	 * spring boot는 jsp는 권장하지 않음
	 * jsp 쓰려면 
	 * 1) src/main/webapp/WEB-INF/ ~~~ 이런 구조로 폴더 => webapps 가 아님 webapp 임 
	 * 2) 임베디드 톰캣 , jstl 디펜던시 추가
	 * 3) 패키징 타입이 war 여야함
	 * @return
	 */
	/*
	@Bean
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		// 우선순위 : jsp 1순위
		viewResolver.setOrder(1);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setViewNames("jsp/*");					// jsp 사용 시 특정 폴더 입력 시 참조하게끔
		return viewResolver;
	}
	*/
	
	@Bean
	public SpringResourceTemplateResolver templateResolver() {
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		templateResolver.setApplicationContext(applicationContext);		// spring ApplicationContext 
		templateResolver.setOrder(1);
		templateResolver.setPrefix("classpath:templates/");			// HTML 파일 위치
		//templateResolver.setPrefix("/WEB-INF/views/");					// HTML 파일 위치
		templateResolver.setSuffix(".html");							// HTML 확장자
		templateResolver.setTemplateMode(TemplateMode.HTML);						// HTML5 X
		templateResolver.setCacheable(false);							// false 새로고침만으로 확인 가능 , 운영 시 true
		return templateResolver; 
	}
	
	
	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine engine = new SpringTemplateEngine();
		engine.setEnableSpringELCompiler(true);							// spring EL 사용여부
		engine.setTemplateResolver(templateResolver());
		return engine;
	}
	
	@Bean
	public ThymeleafViewResolver viewResolver() {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setCharacterEncoding("UTF-8");
		viewResolver.setTemplateEngine(templateEngine());
		return viewResolver;
	}
	
	
	
	
}

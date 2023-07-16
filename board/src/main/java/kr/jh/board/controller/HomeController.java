package kr.jh.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;


@Controller
public class HomeController {

	@GetMapping("/")
	public String main() {
		return "login";
	}
	
	@GetMapping("/login")
	public String login(
			@RequestParam(required = false, value = "error") String error,
			@RequestParam(required = false, value = "exception") String errorMsg , Model model , HttpSession session) {
		
			if(session.getAttribute("mUser") != null) {
				return "redirect:/main";
			}
		
			if(error != null) {
				model.addAttribute("error", error);
				model.addAttribute("errorMsg", errorMsg);
			}
		return "login";
	}
	
	@GetMapping("/home")
	public String home() {
		
		return "index";
	}
	
	@GetMapping("/main")
	public String dashboard() {
		return "home";
	}
	
}

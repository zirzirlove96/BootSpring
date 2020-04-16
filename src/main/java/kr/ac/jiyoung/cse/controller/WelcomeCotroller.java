package kr.ac.jiyoung.cse.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeCotroller {
	
	@GetMapping("/hello")
	public String welcome(String name, int age, Model model) {//url queryString으로 온 값을 받아온다.
		System.out.println(name+" "+age);
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		return "welcome";
	}

}

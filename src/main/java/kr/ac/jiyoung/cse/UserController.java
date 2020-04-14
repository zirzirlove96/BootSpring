package kr.ac.jiyoung.cse;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import kr.ac.jiyoung.model.User;

@Controller
public class UserController {
	
	@PostMapping("/create")
	public String create(User user,Model model) {
		model.addAttribute("id", user.getId());
		model.addAttribute("password", user.getPassword());
		model.addAttribute("name", user.getName());
		model.addAttribute("email", user.getEmail());
		System.out.println(user);
		return "index";
	}

}

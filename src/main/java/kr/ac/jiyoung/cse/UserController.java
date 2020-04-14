package kr.ac.jiyoung.cse;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.ac.jiyoung.model.User;

@Controller
public class UserController {
	
	private List<User> users = new ArrayList<User>();
	
	@PostMapping("/create")
	public String create(User user,Model model) {
		model.addAttribute("id", user.getId());
		model.addAttribute("password", user.getPassword());
		model.addAttribute("name", user.getName());
		model.addAttribute("email", user.getEmail());
		System.out.println(user);
		users.add(user);
		return "redirect:/list";//redirect는 /list를 수행시키는 역할을 한다.
	}
	
	@GetMapping("/list")
	public String getList(Model model) {
		model.addAttribute("users", users);//List에 저장된 값들을 
		//list.html로 전달해 준다.
		return "list";
	}

}

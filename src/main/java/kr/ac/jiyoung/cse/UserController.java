package kr.ac.jiyoung.cse;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController {
	
	@Autowired(required=true)
	private UserRepository userRepository;//DAO를 담당하는 repository를 가져와 쓴다.
	
	@PostMapping("/create")
	public String create(User user,Model model) {
		model.addAttribute("id", user.getId());
		model.addAttribute("password", user.getPassword());
		model.addAttribute("name", user.getName());
		model.addAttribute("email", user.getEmail());
		System.out.println(user);
		//users.add(user);
		userRepository.save(user);//Jparepository에 있는 save()메소드를 사용해 준다.
		return "redirect:/list";//redirect는 /list를 수행시키는 역할을 한다.
	}
	
	@GetMapping("/list")
	public String getList(Model model) {
		model.addAttribute("users", userRepository.findAll());//JpaRepository를 이용하여 
		//users에 정보를 담아 list.html로 보낸다.
		return "list";
	}

}

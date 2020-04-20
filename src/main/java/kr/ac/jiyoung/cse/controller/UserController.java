package kr.ac.jiyoung.cse.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.jiyoung.cse.model.User;
import kr.ac.jiyoung.cse.model.UserRepository;


@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired(required=true)
	private UserRepository userRepository;//DAO를 담당하는 repository를 가져와 쓴다.
	
	@GetMapping("/index")
	public String indexpage() {
		return "/user/index";
	}
	
	@GetMapping("/form")
	public String form() {
		return "/user/form";
	}
	
	@GetMapping("/loginForm")//login.html로 가는 메소드
	public String login() {
		return "/user/login";
	}
	
	@PostMapping("/login")
	public String loginForm(String userId, String password, HttpSession session) {
		User user = userRepository.findByUserId(userId);//데이터베이스에 userId로 사용자를 찾을 수 있는 객체를 생성
		
		if(user == null) {
			return "redirect:/users/loginForm";
		}//user객체에 userId가 없는 경우.
		
		if(!password.equals(user.getPassword())) {
			return "redirect:/users/loginForm";
		}//password가 같지 않는경우.
		
		System.out.println(user);
		session.setAttribute("user", user);//로그인 한 정보를 user라는 이름에 저장하자.
		
		return "redirect:/users/index";
	}
	
	@PostMapping("")//POST일때 /user url과 GET일 때 의 url은 다른 메소드를 가리키기 때문에
	//같은 url을 사용해도 된다.
	public String create(User user,Model model) {
		model.addAttribute("id", user.getId());
		model.addAttribute("password", user.getPassword());
		model.addAttribute("name", user.getName());
		model.addAttribute("email", user.getEmail());
		System.out.println(user);
		//users.add(user);
		userRepository.save(user);//Jparepository에 있는 save()메소드를 사용해 준다.
		return "redirect:/users";//redirect는 /list를 수행시키는 역할을 한다.
	}
	
	@GetMapping("")
	public String getList(Model model) {
		model.addAttribute("users", userRepository.findAll());//JpaRepository를 이용하여 
		//users에 정보를 담아 list.html로 보낸다.
		return "/user/list";
	}
	
	@GetMapping("/{id}/form")//list.html에서 보낸 id값의 사용자 정보를 나타내는 페이지로 이동하게 해준다.
	public String updateForm(@PathVariable Long id, Model model) {
		//PathVaraible은 url에 나타낸 값을 가져와 준다.
		model.addAttribute("user", userRepository.findById(id).get());
		//form.html로 user라는 객체에 id값을 보내준다.
		//Repository는 JPA를 선언했을 때 내부 함수로 있는 findById를 사용
		return "/user/updateform";
	}
	
	@PutMapping("/{id}")
	public String updateUser(@PathVariable Long id, User newUser) {//update정보는 newUser에
		User user = userRepository.findById(id).get();//데이터베이스에 있는 유저를 가져오기.
		user.update(newUser);//User 객체에 update 메소드를 생성한다.
		userRepository.save(user);
		return "redirect:/users";
	}

}

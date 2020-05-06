package kr.ac.jiyoung.cse.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.jiyoung.cse.model.Question;
import kr.ac.jiyoung.cse.model.QuestionRepository;
import kr.ac.jiyoung.cse.model.User;

@Controller
@RequestMapping("/questions")
public class QuestionController {

	@Autowired
	private QuestionRepository questionRepository;
	// @Autowired를 사용하는 이유는 null point가 아닌 repository를 가리키기 위해

	@GetMapping("/form")
	public String form(HttpSession session) {

		if (!HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/users/loginForm";
		}

		return "/qna/form";
	}

	@SuppressWarnings("unused")
	@PostMapping("")
	public String questionPost(String title, String contents, HttpSession session) {

		if (!HttpSessionUtils.isLoginUser(session)) {

			return "redirect:/users/loginForm";
		}

		User user = HttpSessionUtils.getUserFromSession(session);// 글쓴이 즉 로그인한 유저를 가져온다.
		Question newQuestion = new Question(user, title, contents);
		// 생성자에 값을 것을 Question에 보낸다.

		questionRepository.save(newQuestion);

		return "redirect:/users/index";// 성공했을 때 메인화면으로 가게 한다.
	}

	@GetMapping("/{id}")
	public String showQuestions(@PathVariable Long id, Model model) {
		Question question = questionRepository.findById(id).get();
		// 원하는 글을 가져오게 한다.

		model.addAttribute("question", question);

		return "/qna/show";
	}

	@GetMapping("/{id}/form")
	public String updateQuestions(@PathVariable Long id, Model model, HttpSession session) {
		
		if(!HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/users/loginForm";
		}//로그인이 되어 있지 않은 경우 로그인 페이지로 넘어간다.
		
		//로그인은 되어 있지만 글을 쓴 이와 로그인한 정보가 다를 경우
		User sessionedUser = HttpSessionUtils.getUserFromSession(session);
		Question questionLogin = questionRepository.findById(id).get();
		if(!questionLogin.isSameUser(sessionedUser)) {
			return "redirect:/users/loginForm";
		}
				
		Question question = questionRepository.findById(id).get();
		model.addAttribute("question", question);
		
		return "/qna/updateQuestion";
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable Long id, String title, String contents, HttpSession session) {
		
		if(!HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/users/loginForm";
		}
		
		User loginUser = HttpSessionUtils.getUserFromSession(session);
		Question questionLogin = questionRepository.findById(id).get();
		if(!questionLogin.isSameUser(loginUser)) {
			return "redirect:/users/loginForm";
		}
		
		Question question = questionRepository.findById(id).get();
		question.update(title, contents);//객체에 수정한 내용을 저장
		questionRepository.save(question);//데이터베이스에 수정된 객체를 저장
		
		return String.format("redirect:/questions/%d", id);
		//String.format은 지정된 위치에 값을 대입해서 문자열을 만들어준다.
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id, HttpSession session) {
		
		if(!HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/users/loginForm";
		}
		
		User loginUser = HttpSessionUtils.getUserFromSession(session);
		Question questionLogin = questionRepository.findById(id).get();
		if(!questionLogin.isSameUser(loginUser)) {
			return "redirect:/users/loginForm";
		}
		
		questionRepository.deleteById(id);
		return "redirect:/users/index";
	}


}

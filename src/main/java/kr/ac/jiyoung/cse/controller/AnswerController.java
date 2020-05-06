package kr.ac.jiyoung.cse.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.jiyoung.cse.model.Answer;
import kr.ac.jiyoung.cse.model.AnswerRepository;
import kr.ac.jiyoung.cse.model.Question;
import kr.ac.jiyoung.cse.model.QuestionRepository;
import kr.ac.jiyoung.cse.model.User;

@Controller
@RequestMapping("/questions/{questionId}/answer")
public class AnswerController {
	
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private AnswerRepository answerRepository;
	
	@PostMapping("")
	public String create(@PathVariable Long questionId, String contents, HttpSession session) {
		if(!HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/users/loginForm";
		}
		User loginuser = HttpSessionUtils.getUserFromSession(session);
		//Answer의 생성자의 question 매개변수를 채우기 위해 가져온다.
		//질문 데이터의 id값을 객체에 넣어서 준다.
		Question question = questionRepository.findById(questionId).get();
		Answer answer = new Answer(loginuser, question, contents);
		answerRepository.save(answer);//데이터베이스에 데이터 저장
		
		//답글이 달렸는지 show.html로 이동
		return String.format("redirect:/questions/%d", questionId);
	}

}

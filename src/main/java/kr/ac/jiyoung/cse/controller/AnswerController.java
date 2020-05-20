package kr.ac.jiyoung.cse.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.jiyoung.cse.model.Answer;
import kr.ac.jiyoung.cse.model.AnswerRepository;
import kr.ac.jiyoung.cse.model.Question;
import kr.ac.jiyoung.cse.model.QuestionRepository;
import kr.ac.jiyoung.cse.model.User;

@RestController
@RequestMapping("/api/questions/{questionId}/answer")
public class AnswerController {
	
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private AnswerRepository answerRepository;
	
	@PostMapping("")
	public Answer create(@PathVariable Long questionId, String contents, HttpSession session) {
		if(!HttpSessionUtils.isLoginUser(session)) {
			return null;
		}
		User loginuser = HttpSessionUtils.getUserFromSession(session);
		Question question = questionRepository.findById(questionId).get();
		Answer answer = new Answer(loginuser, question, contents);
		return answerRepository.save(answer);//데이터베이스에 저장된 값을 리턴
		
	}

}

package kr.ac.jiyoung.cse.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Question extends AbstractEntity{

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
	@JsonProperty
	private User writer;
	// 원래의 String writer에서 User객체를 사용하여 객체간의 관계를 나타내 보자.

	@OneToMany(mappedBy = "question")
	@OrderBy("id DESC")
	private List<Answer> answersList;

	@JsonProperty
	private String title;

	@JsonProperty
	private String contents;
	
	@JsonProperty
	private Integer counfOfAnswer = 0;
	
	public Question() {}

	public Question(User writer, String title, String contents) {
		this.writer = writer;
		this.title = title;
		this.contents = contents;
	}


	/*public int getWriterSize() {

		return answersList.size();
	}*/

	public void update(String title, String contents) {
		// TODO Auto-generated method stub
		this.title = title;
		this.contents = contents;
	}

	public boolean isSameUser(User LoginUser) {
		// TODO Auto-generated method stub

		return this.writer.equals(LoginUser);
	}

	public void addAnswer() {
		// TODO Auto-generated method stub
		this.counfOfAnswer+=1;
	}
	
	public void deleteAnswer() {
		this.counfOfAnswer-=1;
	}

}

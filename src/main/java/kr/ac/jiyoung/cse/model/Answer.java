package kr.ac.jiyoung.cse.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
public class Answer extends AbstractEntity{

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name="fk_answer_writer"))
	@JsonProperty
	private User writer;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name="fk_answer_to_question"))
	@JsonProperty
	private Question question;
	
	@Lob //텍스트의 길이를 255자를 넘겨서 설정
	@JsonProperty
	private String contents;
	
	
	public Answer() {}
	
	public Answer(User writer, Question question, String contents) {
		this.writer = writer;
		this.question = question;
		this.contents = contents;
	}
	

	public boolean isSameWriter(User loginuser) {
		
		return loginuser.equals(this.writer);
	}//글 쓴이와 로그인 한 사람이 동일한지 확인해 준다.

}

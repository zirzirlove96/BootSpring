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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Question {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name="fk_question_writer"))
	private User writer;
	//원래의 String writer에서 User객체를 사용하여 객체간의 관계를 나타내 보자.
	
	@OneToMany(mappedBy="question")
	@OrderBy("id ASC")
	private List<Answer> answersList;
	
	private String title;
	
	private String contents;

	private LocalDateTime createDate;
	
	public Question(User writer, String title, String contents) {
		this.writer=writer;
		this.title = title;
		this.contents = contents;
		this.createDate = LocalDateTime.now();//데이터베이스에 시간을 넣어주기 위해 
	}
	
	//index.jsp에서 시간을 나타내기 위해 사용하는 메서드
	public String getFormattedCreateDate() {
		if(createDate == null ) {
			return "";
		}
		
		return createDate.format(DateTimeFormatter.ofPattern("yyy.MM.dd HH:mm:ss"));
	}
	
	public int getWriterSize() {
		
		return answersList.size();
	}

	public void update(String title, String contents) {
		// TODO Auto-generated method stub
		this.title = title;
		this.contents = contents;
	}

	public boolean isSameUser(User LoginUser) {
		// TODO Auto-generated method stub
		
		return this.writer.equals(LoginUser);
	}

	
}

package kr.ac.jiyoung.cse.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity//데이터베이스와 연결되는 것을 인식해준다
public class User {
	
	@Id//primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)//Auto_increment
	private Long id;
	
	@Column(nullable=false, length=20, unique=true)//userId에 null값은 들어 가지 못하고 길이는 20이하여야 한다.
	private String userId;
	private String password;
	private String name;
	private String email;
	
	public void update(User newUser) {
		this.password = newUser.password;
		this.name = newUser.name;
		this.email = newUser.email;
	}//수정한 정보가 User객체에 들어가게 된다.
	
	public boolean mathPassword(String newPassword) {
		if(newPassword==null) {
			return false;
		}
		return newPassword.equals(password);
	}
	
	public boolean mathId(Long newId) {
		if(newId==null) {
			return false;
		}
		return newId.equals(id);
	}
	
	

}

package kr.ac.jiyoung.cse;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	
	@Column(nullable=false, length=20)//userId에 null값은 들어 가지 못하고 길이는 20이하여야 한다.
	private String userId;
	private String password;
	private String name;
	private String email;

}

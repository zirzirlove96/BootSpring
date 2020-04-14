package kr.ac.jiyoung.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter//UserController에서 user의 데이터 값들을 get해야 하므로
@ToString
public class User {
	
	private String id;
	private String password;
	private String name;
	private String email;

}//데이터를 담는 클래스
//데이터를 담기 위해서는 Set메소드가 필요로 하기때문에 추가해준다.

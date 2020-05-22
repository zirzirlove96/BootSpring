package kr.ac.jiyoung.cse.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.ToString;

@Entity // 데이터베이스와 연결되는 것을 인식해준다
@ToString
public class User extends AbstractEntity {

	@Column(nullable = false, length = 20, unique = true) // userId에 null값은 들어 가지 못하고 길이는 20이하여야 한다.
	@JsonProperty
	private String userId;

	@JsonIgnore
	private String password;

	@JsonProperty
	private String name;

	@JsonProperty
	private String email;

	public User() {
	}

	public void update(User newUser) {
		this.password = newUser.password;
		this.name = newUser.name;
		this.email = newUser.email;
	}// 수정한 정보가 User객체에 들어가게 된다.

	public boolean mathPassword(String newPassword) {
		if (newPassword == null) {
			return false;
		}
		return newPassword.equals(password);
	}

	public boolean mathId(Long newId) {
		if (newId == null) {
			return false;
		}
		return newId.equals(getId());
	}
	
	public String getUserId() {
		return userId;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}

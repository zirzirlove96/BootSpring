package kr.ac.jiyoung.cse.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity//데이터베이스와 연결되는 것을 인식해준다
public class User {
	
	

	@Id//primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)//Auto_increment
	@JsonProperty
	private Long id;
	
	@Column(nullable=false, length=20, unique=true)//userId에 null값은 들어 가지 못하고 길이는 20이하여야 한다.
	@JsonProperty
	private String userId;

	@JsonIgnore
	private String password;
	
	@JsonProperty
	private String name;
	
	@JsonProperty
	private String email;
	
	public User() {}
	
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
	
	public Long getId() {
		return id;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	

}

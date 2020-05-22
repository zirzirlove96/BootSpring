package kr.ac.jiyoung.cse.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonProperty;

@MappedSuperclass//상속 관계 매핑 부모 클래스와 자식 클래스 모두 데이터베이스 테이블과 매핑을 하기 위해 사용
@EntityListeners(AuditingEntityListener.class)
//createDate, modifiedDate의 에노테이션에 의해 바뀐 시간을 자동으로 입력해 준다.
public class AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty
	private Long id;


	@CreatedDate
	private LocalDateTime createDate;
	
	@LastModifiedDate //자동으로 시간을 업데이트 해준다.
	private LocalDateTime modifiedDate;
	
	//equals, getId, getFormatted 등 모델 객체에 들어가는 공통적인 부분
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
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
		AbstractEntity other = (AbstractEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	// index.jsp에서 시간을 나타내기 위해 사용하는 메서드
	public String getFormattedCreateDate() {
		if (createDate == null) {
			return "";
		}

		return createDate.format(DateTimeFormatter.ofPattern("yyy.MM.dd HH:mm:ss"));
	}
	

	@Override
	public String toString() {
		return "AbstractEntity [id=" + id + ", createDate=" + createDate + ", modifiedDate=" + modifiedDate + "]";
	}

}

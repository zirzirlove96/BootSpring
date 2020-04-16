package kr.ac.jiyoung.cse.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
	//JpaRepository를 연결하고 User 테이블과 id값을 연결함으로써 SQL문을 던져 CRUD를 할 수 있다.
	//데이터베이스에 데이터를 추가, 삭제, 수정 기능을 할 수 있다.
	//기본적으로 save,delete를 JpaRepository가 가지고 있다.

}
